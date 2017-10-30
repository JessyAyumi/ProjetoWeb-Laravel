/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jéssica Ayumi Uehara
 */
@WebServlet(urlPatterns = {"/busca"})
public class busca extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();

        if ((session.getAttribute("logado")) == null) {
            response.sendRedirect("login");
        }
        String palavra = request.getParameter("txtBuscar");
        ArrayList<String> conteudo = null;

        try {
            conteudo = buscaConteudo(palavra);
        } catch (SQLException ex) {
            Logger.getLogger(uploadImagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        writer.println(" <!DOCTYPE html>  ");
        writer.println("  <html> ");
        writer.println("  <head> ");
        writer.println("</head>   ");
        writer.println("<body>   ");
        writer.println("        <form action=\"busca\" method=\"get\"");
        writer.println("                          accept-charset=\"utf-8\"");
        writer.println("                          enctype=\"multipart/form-data\">");
        writer.println("            <input type=\"text\" name=\"txtBuscar\"/>");
        writer.println("            <input type=\"submit\" name=\"enviar\" value=\"Enviar\" />");
        writer.println("            <button><a href=\"login\" style=\"text-decoration:none\">Voltar</a></button>");
        writer.println("        </form>");

        for (int i = 0;
                i < conteudo.size();
                i++) {
            writer.println("<div class=\"postagens\">   ");
            writer.println(conteudo.get(i));
            writer.println("</div>");
        }
        writer.println(" </div>");
        writer.println("</body>");
        writer.println("</html>");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String palavra = (String) request.getAttribute("txtBuscar");
        System.out.println(palavra);
        try {
            buscaConteudo(palavra);

        } catch (SQLException ex) {
            Logger.getLogger(busca.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("busca");
    }
    
     private ArrayList<String> buscaConteudo(String palavra) throws SQLException {
        ArrayList<String> conteudo = new ArrayList<String>();
        ConectDB db = new ConectDB();
        Connection conn = db.getConnection();
        try {
            String sql = "select c.descricao, u.nome from conteudo c inner join usuario u on (u.id = c.id_usuario) where c.descricao like '%" + palavra + "%'";
  
            PreparedStatement st = conn.prepareStatement(sql);;
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                conteudo.add("Usuario: " + 
                        resultSet.getString("nome")+" - "+ resultSet.getString("descricao"));
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();

        return conteudo;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
