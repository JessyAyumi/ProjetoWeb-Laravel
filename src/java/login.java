
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("logado") != null) {
                response.sendRedirect("controlador");
                return;
            }

            String userName = new String("");
            String password = new String("");

            try {
                out.println("<!DOCTYPE HTML>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=\"utf-8\" />");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
                out.println("<Title></Title>");
                out.println("<style></style>");
                out.println("</head>");
                out.println("<body>");

                Connection con;
                ConectDB db = new ConectDB();
                con = db.getConnection();

                String email = request.getParameter("email");
                String sql = "SELECT email, senha, nome, id FROM usuario where email = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, email);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    userName = rs.getString("email");
                    password = rs.getString("senha");
                    System.out.println(request.getParameter("senha"));
                    System.out.println(password);
                    if (userName.equals(request.getParameter("email")) && (password.equals(request.getParameter("senha")))) {
                        System.out.println("ENTROU");
                        request.getSession().setAttribute("logado", new Boolean(true));
                        request.getSession().setAttribute("nome", rs.getString("nome"));
                        request.getSession().setAttribute("id", rs.getInt("id"));
                        response.sendRedirect("controlador");
                    }
                }
                rs.close();
                st.close();
                if (email != null) {
                    out.println("<h1><br><center>Usuário ou senha incorreto</center></h1>");
                } else {
                    email = email != null ? email : "";
                }
                out.println("<div class=\"wrapper\">");
                out.println("<div class=\"conteudo\">");
                out.println("<div class=\"card\">");
                out.println("<form method=\"post\" action=\"login\" charset=\"utf-8\">");
                out.println("<input type=\"email\" id=\"email\" name=\"email\" class=\"email\" value=\"" + email + "\"  placeholder=\" Seu e-mail: exemplo@gmail.com\" style=\"outline: none;\" required name=email; size=\"25\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$\" ></input>");
                out.println("<input type=\"password\" id=\"senha\" name=\"senha\" class=\"email\" value=\"\"  placeholder=\" Insira sua senha\" required name=password style=\"outline: none;\" charset=\"utf-8\" size=\"25\" ></input>");
                out.println("<center> <input type=\"submit\" style=\"width:100%;height:auto;\" class=\"botao01\" value=\"Login\"  placeholder=\" \" style=\"outline: none;\" charset=\"utf-8\" ></input></center>");
                out.println("</form>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");

                con.close();
            } catch (Exception E) {
                out.println(E);
            }

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
