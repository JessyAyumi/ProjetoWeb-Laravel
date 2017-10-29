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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rodrigo
 */
@WebServlet(urlPatterns = {"/timeline"})
public class timeline extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet timeline</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet timeline at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            HttpSession session = request.getSession();
            if((session.getAttribute("logado"))!=null){
            //response.getWriter().println("<h1>olá pagina protegida</h1>");
            //LOGOUT
            //request.getSession().removeAttribute("logado");
            }else{
                response.sendRedirect("login");
            }
            
            out.println("<!DOCTYPE HTML>");
            out.println("<!-- Alunos: -->");
            out.println("<!-- Rodrigo Alcalá de Melo -->");
            out.println("<!-- Bruna Caroline Gonçalves Fernandes -->");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\" />");
            out.println("<Title>Google+</Title>");
            out.println("<style></style>");
            out.println("</head>");
            out.println("<body>");
          

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String data = dateFormat.format(date); 
            
            //FORM DE POSTAGEM//
            out.println("<div class=\"wrapper\">");
            out.println("<div class=\"menubar\"> ");
            out.println("<div class=\"overflowHidden\">");
            
            out.println("<form method=\"post\" action=\"buscar\" accept-charset=\"utf-8\">");
            out.println("<img src=\"logo.png\" class=\"logo\"></img><input type=\"text\" name=\"buscar\"  class=\"searchbox\"  placeholder=\"Pesquisar no Google+\" style=\"outline: none;\" aria-label=\"Pesquisar no Google+\" size=\"75\" required name=\"buscar\"><button type=\"submit\" action=\"timeline\" class=\"botao\" aria-label=\"Pesquisa Google\" required name=\"buscar\" ></button></input>");
            out.println("<img src=\"icones_lateral.png\" class=\"iconesDireita\"></img></div>");
            out.println("</form>");
            
            out.println("<div class=\"menubar2\"><img src=\"menu.png\" class=\"logo\"></img><img src=\"icone_menu2.png\" class=\"iconesDireita\"></img></div>");
            out.println("</div>");
            out.println("<div class=\"central\">");
            out.println("<br><br><br><br><br>");
            out.println("<div class=\"corpo\">");
            //INICIO POST//
            out.println("<!--- COMEÇO POST -->");
            out.println("<div class=\"cards_post\">");
            out.println("<div>");
            out.println("<div><div class=\"topo\"><img src=\"avatar2.png\" ></img></div>");
            out.println("<div class=\"topo\"><span class=\"cardDecoration\">"+ session.getAttribute("nome") +"</span> </div>");
            out.println("<br><div><span class=\"cardDecoration2\">"+ dateFormat.format(date) + "</span> ");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"cardbody\">");
            out.println("</div>");
            out.println("<form method=\"post\" action=\"timeline\" accept-charset=\"utf-8\">");
            out.println("<textarea   id=\"msg\" name=\"msg\" required name=\"msg\";  placeholder=\" Adicionar um comentário..\" style=\"outline: none\" ></textarea>");
            out.println("<div style=\"margin-left: 12px; margin-right: 12px;\"><hr class=\"separador\"></hr></div>");
            out.println("<input type=\"submit\" name=\"publicar\" value=\"Publicar\" class=\"botao01\"  style=\"outline: none;\" size=\"auto\" required name=\"msg\" ></input>");
           // out.println("<input type=\"submit\" name=\"buscar\" value=\"Pesquisar\"  class=\"botao01\" style=\"outline: none;\" size=\"auto\" required name=\"msg\" ></input>");
            out.println("</form>");
            out.println("<form method=\"post\" action=\"logout\">");
            out.println("<input type=\"submit\" class=\"botao01\" name=\"logout\" style=\"outline: none;\" size=\"auto\" value=\"Logout\"/></input> ");
            out.println("</form>");
            out.println("</div>");
            //FIM POST//
            out.println("<!-- FIM POST -->");
            

            System.out.println(request.getParameter("msg"));
            try{
                out.println("to aqio");
                Connection con;
                ConectDB db = new ConectDB();
                con = db.getConnection();
                PreparedStatement ps = null;
                ps = con.prepareStatement(
                        "INSERT INTO CONTEUDO(id_usuario,descricao) VALUES (?,'?')");
                out.println("to aqui");
                int id_usuario = ((int) (session.getAttribute("id")));
                String conteudo = request.getParameter("msg");
                ps.setInt(1, id_usuario);
                ps.setString(2, conteudo);
                ps.executeUpdate();
                con.close();
                   //response.sendRedirect("timeline");
                   
                  
                 }catch(Exception e){
                    System.out.println(e);
                 }
                
            
            
            
            //CARD//
            try{
            
                String url = "jdbc:postgresql://localhost:5432/GooglePlus";  
                   String usuario = "postgres";  
                   String senhaBanco = "root";  
                   Class.forName("org.postgresql.Driver");  
                   Connection con;  
                   con = (Connection) DriverManager.getConnection(url, usuario, senhaBanco);
                   PreparedStatement ps = con.prepareStatement("SELECT * FROM post WHERE id_usuario="+ session.getAttribute("id")+"");
                   ResultSet rs = ps.executeQuery();
            
            
                   
            while (rs.next()) {
        
  
            out.println("<div class=\"cards\">");
            //out.println("<div><span class=\"cardDecoration2\">Popular no Google+ de Moto X Style | Play Brasil</span><img src=fire.png style=\"float:right; margin-top: -10px;\"></div>");
            //out.println("<hr class=\"separador\"></hr>");
            out.println("<div>");
            out.println("<div><div class=\"topo\"><img src=\"avatar2.png\" ></img></div>");
            out.println("<div class=\"topo\"><span class=\"cardDecoration\">"+ session.getAttribute("nome") +"</span></div>");
            out.println("<br><div><span class=\"cardDecoration2\">"+ rs.getString("data") +"</span> ");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"cardbody\">");
            //out.println("<span style=\"text-align: left;\"><br>Finalmente! Wallpaper do Moto X Style haha</span>");
            out.println("<span style=\"text-align: left;\"><br>"+ rs.getString("conteudo") +"</span>");
            out.println("<span class=\"cardDecoration2\" style=\"display: block;\">Traduzir</span>");
            out.println("</div>");
            //out.println("<div><br><img src=wallpaper.png class=\"transform\"></img></div>");
            out.println("<div style=\"margin-left: 12px; margin-right: 12px;\"><hr class=\"separador\"></hr></div>");
            out.println("<div class=\"topo\"><img src=cardButton.png style=\"float:left;\"></img><input type=\"text\" class=\"email\" value=\"\"  placeholder=\" Adicionar um comentário..\" style=\"outline: none;\" aria-label=\"\" size=\"25\" ></input></div>");
            out.println("</div>");
            
            }
            
            }catch(Exception e){
                System.out.println(e);
            
            };
            
            
            out.println("<div class=\"direita\"></div>");
            //out.println("<div class=\"pe\"></div>");
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
        processRequest(request, response);
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
