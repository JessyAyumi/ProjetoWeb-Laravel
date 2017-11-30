
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/listaTudo"})
public class listaTudo extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = res.getWriter();
        try {
            ConectDB db = new ConectDB();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM conteudo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                writer.println("<li>" + rs.getString("descricao") + "</li>");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
