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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cadastro"})
public class cadastro extends HttpServlet {

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
        
            
        out.println("        <!DOCTYPE HTML>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=\"utf-8\" />");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
        out.println("<Title>Laravel</Title>");
        out.println("<style></style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"card2\">");
      //  out.println("</div>");
        out.println("<center>");
        out.println("<div class=\"cadastro\">");
        out.println("<a>CADASTRE-SE</a>");
        out.println("</div>"); 
        out.println("<div class=\"conteudo\">");
        out.println("<div class=\"cards_cadastro\">");
        out.println("<form method=\"post\" action=\"cadastro\" accept-charset=\"utf-8\">");
        String email = request.getParameter("email");
        email = email != null ? email : "";
        out.println("<input type=\"email\" id=\"email\" class=\"email\" value=\""+email+"\"  placeholder=\" E-mail \" style=\"outline: none;\" required name=\"email\"; aria-label=\"Buscar?\" size=\"35\" charset=\"utf-8\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$\" ></input>");
        out.println("<input type=\"text\" id=\"nome\" class=\"email\" value=\"\"  placeholder=\" Nome\" style=\"outline: none;\" required name=\"nome\"; aria-label=\"Buscar?\" size=\"35\" accept-charset=\"utf-8\" ></input>");
        out.println("<input type=\"password\" id=\"password\" class=\"email\" value=\"\"  placeholder=\" Senha\" required name=\"senha\" style=\"outline: none;\" aria-label=\"Buscar\" size=\"35\" accept-charset=\"utf-8\" ></input>");     
        out.println("<input type=\"submit\" class=\"botao01\" value=\"Cadastrar\"  placeholder=\" Nome?\" style=\"outline: none;\" size=\"auto\" ></input>");
        out.println("<a href=\"login\" class=\"alinhamento\">Voltar</a>");
        out.println("</form>");
        out.println("</div>");               
        out.println("</div>");
        out.println("</div>");
        out.println("</center>");
        out.println("</div>");
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
            response.setContentType("text/html;charset=UTF-8");
            
            try{
                String nome = request.getParameter("nome"),
                       email = request.getParameter("email"),
                       senha = request.getParameter("senha");
                   String url = "jdbc:postgresql://localhost:5432/GooglePlus";  
                   String usuario = "postgres";  
                   String senhaBanco = "UEKACW425";  
  
                   Class.forName("org.postgresql.Driver");  
                   Connection con;  
                   con = (Connection) DriverManager.getConnection(url, usuario, senhaBanco);
                   
                   
                 String sql_str = "SELECT count(email) AS tupla FROM usuario WHERE email=\'"+request.getParameter("email")+"\'";
                 System.out.println(sql_str);
                 PreparedStatement s = con.prepareStatement(sql_str);
                 ResultSet rs = s.executeQuery();
                 rs.next();
                 System.out.println(rs.getInt("tupla"));
                 if(rs.getInt("tupla") == 0){
                     PreparedStatement ps = null;
                       ps = con.prepareStatement(
                           "INSERT INTO USUARIO(nome,email,senha) VALUES (?,?,?)");
                       ps.setString(1, nome);
                       ps.setString(2, email);
                       ps.setString(3, senha);
                       ps.executeUpdate();
                       con.close();
                       response.sendRedirect("login");
                 }else{
                try (PrintWriter out = response.getWriter()) {
                   
                        out.println("<h1><br><center>O E-MAIL JÁ ESTÁ CADASTRADO!</center></h1>");
                        out.println("        <!DOCTYPE HTML>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estiloGeral.css\" />");
                        out.println("<Title>Google+</Title>");
                        out.println("<style></style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class=\"wrapper\">");
                      //  out.println("<div class=\"\"><div class=\"googlelogo\"></div></div>");
                        out.println("<div class=\"corpo\">");
                        out.println("<div class=\"cabecalho\">");
                        out.println("<h1>CADASTRE-SE</h1>");
                        out.println("<center>");
                        out.println("<div class=\"conteudo\">");
                        out.println("<div class=\"cards_cadastro\">");
                        out.println("<form method=\"post\" action=\"cadastro\" accept-charset=\"utf-8\">");
                        email = email != null ? email : "";
                        out.println("<input type=\"email\" id=\"email\" class=\"email\" value=\""+email+"\"  placeholder=\" E-mail: \" style=\"outline: none;\" required name=\"email\"; aria-label=\"Buscar\" size=\"45\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$\" ></input>");
                      // WHAT? out.println("<input type=\"text\" id=\"nome\" class=\"Email\" value=\"\"  placeholder=\" nome? \" style=\"outline: none;\" required name=\"nome\"; aria-label=\"Pesquisar no Google+\" size=\"25\" accept-charset=\"utf-8\" ></input>");
                       // out.println("<input type=\"password\" id=\"password\" class=\"email\" value=\"\"  placeholder=\" Insira sua senha\" required name=\"senha\" style=\"outline: none;\" aria-label=\"Pesquisar no Google+\" size=\"25\" accept-charset=\"utf-8\" ></input>");     
                       // out.println("<input type=\"submit\" class=\"botao01\" value=\"Cadastrar\"  placeholder=\" Como se chama?\" style=\"outline: none;\" size=\"auto\" ></input>");
                       // out.println("<a href=\"login\" class=\"alinhamento\">Voltar</a>");
                        out.println("</form>");
                        out.println("</div>");
                        
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</center>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");
                     
                    
                }catch(Exception e){
 
                };  
  
             }
                 
            }catch(Exception e){ 
                     System.out.println("Erro na conexão com o banco de dados");
                e.printStackTrace();
            };
            
                      
                   
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
