/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Regina Sobral
 */
@WebServlet(urlPatterns = {"/conteudo"})
public class conteudo extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if ((session.getAttribute("logado")) == null) {              
                response.sendRedirect("login");
            }
            out.println("<!DOCTYPE HTML>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=\"utf-8\" />");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
            out.println("<Title></Title>");
            out.println("<style></style>");
            out.println("</head>");
            out.println("<body>");

            out.println("</form>");
            out.println("<div class=\"wrapper\">");
            out.println("<div class=\"conteudo\">");
            out.println("<div class=\"card\">");
            out.println("<div class=\"central\">");
            out.println("<br><br><br><br><br>");
            out.println("<div class=\"corpo\">");
            out.println("<div class=\"cards_post\">");
            out.println("<div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"cardbody\">");
            out.println("</div>");
            out.println("<form method=\"post\" action=\"conteudo\" accept-charset=\"utf-8\">");
            out.println("<textarea   id=\"msg\" name=\"msg\" required name=\"msg\";  placeholder=\" Insira um conteudo\" style=\"outline: none\" ></textarea>");
            out.println("<div style=\"margin-left: 12px; margin-right: 12px;\"><hr class=\"separador\"></hr></div>");
            out.println("<input type=\"submit\" name=\"publicar\" value=\"Publicar\" class=\"botao01\"  style=\"outline: none;\" size=\"auto\" required name=\"text\" ></input>");
            out.println("<a href=\"login\" class=\"alinhamento\">Voltar</a>");
            out.println("</form>");
            
            System.out.println(request.getParameter("text"));

            if (request.getParameter("msg") != null) {
                int id_usuario = ((int) (session.getAttribute("id")));
                String descricao = request.getParameter("msg");
                
                Connection con;
                ConectDB db = new ConectDB();
                con = db.getConnection();
                
                PreparedStatement ps = null;
                ps = con.prepareStatement(
                        "INSERT INTO CONTEUDO(id_usuario,descricao) VALUES (?,?)");
                out.println("to aqui");
                
                ps.setInt(1, id_usuario);
                ps.setString(2, descricao);
                ps.executeUpdate();
                con.close();

            }
            out.println("</div>");
             out.println("<div class=\"direita\"></div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(conteudo.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(conteudo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
