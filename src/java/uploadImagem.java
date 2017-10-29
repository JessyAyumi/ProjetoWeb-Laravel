
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class uploadImagem extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private int id = 0;

    public void doGet(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = res.getWriter();

        HttpSession session = req.getSession();
        if ((session.getAttribute("logado")) == null) {
            res.sendRedirect("login");
        }

        ArrayList<String> imagens = null;
        try {
            imagens = selectImagens(session);
        } catch (SQLException ex) {
            Logger.getLogger(uploadImagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Upload de Imagem</h1>");
        writer.println("        <form action=\"upload\" method=\"POST\"");
        writer.println("                          accept-charset=\"utf-8\"");
        writer.println("                          enctype=\"multipart/form-data\">");
        writer.println("            <input type=\"file\" name=\"arquivo\" value=\"\" />");
        writer.println("            <input type=\"submit\" name=\"enviar\" value=\"submit\" />");
        writer.println("        </form>");
        writer.println("<ul>");
        for (int i = 0; i < imagens.size(); i++) {
            writer.println("    <li><img src=\"" + imagens.get(i) + "\" height=\"100\"></img></li>");
        }
        writer.println("</ul>");
        writer.println("    </body>");
        writer.println("</html>");
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        Part part = request.getPart("arquivo");
        HttpSession session = request.getSession();
        String images_path = request.getServletContext().getRealPath("/uploads");

        String nome = part.getSubmittedFileName();
        InputStream in = part.getInputStream();
        if ((part.getContentType().equals("image/png")) || (part.getContentType().equals("image/jpeg"))|| (part.getContentType().equals("image/jpg"))) {
            insereCaminho(session, images_path, nome);
            Files.copy(in, Paths.get(images_path + "/" + nome), StandardCopyOption.REPLACE_EXISTING);
        }
        part.delete();
        response.sendRedirect("upload");
    }

    public void insereCaminho(HttpSession session, String images_path, String nome) {
        try {
            ConectDB db = new ConectDB();
            Connection con = db.getConnection();
            int id_usuario = (int) (session.getAttribute("id"));
            PreparedStatement ps = con.prepareStatement("INSERT INTO conteudo(id_usuario,midia) VALUES (?,?)");
            ps.setInt(1, id_usuario);
            ps.setString(2, (images_path) + '/' + nome);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro na conexão com o banco de dados");
            e.printStackTrace();
        };
    }

    private ArrayList<String> selectImagens(HttpSession session) throws SQLException {
        ArrayList<String> imagem = new ArrayList<String>();
        ConectDB db = new ConectDB();
        Connection conn = db.getConnection();
        try {
            int id_usuario = (int) (session.getAttribute("id"));
            String sql = "select midia from conteudo where midia is not null and id_usuario = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id_usuario);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String diretorioRelativo = resultSet.getString("midia").substring(resultSet.getString("midia").indexOf("uploads"));
                imagem.add(diretorioRelativo);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();

        return imagem;
    }

}
