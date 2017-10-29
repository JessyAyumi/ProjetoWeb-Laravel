
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.time.Clock.system;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controlador
 */
@WebServlet(urlPatterns = {"/controlador"})
public class controlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();
        if ((session.getAttribute("logado")) == null) {
            response.sendRedirect("login");
        }

        writer.println(" <!DOCTYPE html>  ");
        writer.println("  <html> ");
        writer.println("  <head> ");
        writer.println("     <link href=\"https://fonts.googleapis.com/css?family=Miriam+Libre\" rel=\"stylesheet\">  ");
        writer.println("     <link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:200\" rel=\"stylesheet\">");
        writer.println("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">   ");
        writer.println("    <link rel=\"stylesheet\" type = \"text/css\" href=\"style.css\"> ");
        writer.println("</head>   ");
        writer.println("<body>   ");
        writer.println("    <div class=\"nav\">   ");
        writer.println("        <img src=\"img\\laravel.png\" class=\"logo\"><span id=\"laravel\" style=\"color:#e74430; font-size:18px;line-height: 80px;font-family:'Miriam Libre', sans-serif;width:62.5px;height:80px; padding-right:15px; \">Laravel</span>   ");
        writer.println("        <div class=\"div-middle-menu\">   ");
        writer.println("            <img src=\"img\\search.png\" class=\"search-img\" />   ");
        writer.println("            <input type=\"text\" class=\"search-input\" placeholder=\"S E A R C H\" />   ");
        writer.println("        </div>   ");
        writer.println("        <div class=\"div-hamburguer \">   ");
        writer.println("            <a href=\"#\" class=\"hamburguer\">☰</a>   ");
        writer.println("        </div>   ");
        writer.println("        <div class=\"div-right-menu div-right-align\" style=\"width: 745px;\">   ");
        writer.println("            <a>Login</a>   ");
        writer.println("            <a>Cadastrar</a>   ");
        writer.println("            <a>News</a>   ");
        writer.println("            <a>Partners</a>   ");
        writer.println("            <a href=\"logout\" style=\"text-decoration: none\">Sair</a>   ");
        writer.println("            <div class=\"dropdown\">   ");
        writer.println("                <span>EcoSystem</span><span class=\"fa fa-caret-down\" style=\"padding-left: 7%;font-size: 12px\"></span>   ");
        writer.println("                <div class=\"dropdown-content\">   ");
        writer.println("                    <p>Documentation</p>   ");
        writer.println("                    <p>News</p>   ");
        writer.println("                    <p>Spark</p>   ");
        writer.println("                    <hr/>   ");
        writer.println("                    <p>Laracon EU</p>   ");
        writer.println("                    <p>Laracon US</p>   ");
        writer.println("                    <p>Laracon Online</p>   ");
        writer.println("                    <hr/>   ");
        writer.println("                    <p>Forums</p>   ");
        writer.println("                    <p>GitHub</p>   ");
        writer.println("                    <p>Jobs</p>   ");
        writer.println("                    <p>Podcast</p>   ");
        writer.println("                    <p>Slack</p>   ");
        writer.println("                    <p>Twitter</p>   ");
        writer.println("                </div>   ");
        writer.println("            </div>   ");
        writer.println("        </div>   ");
        writer.println("    </div>   ");
        writer.println("    <div style=\"padding:50px;\">   ");
        writer.println("        <h1 style=\"font-family: 'Source Sans Pro', sans-serif;text-align: center; font-size: 48px;font-weight: 200;color:#525252\">Love beautiful code? We do too.</h1>   ");
        writer.println("        <h3 class=\"subtitulo\">The PHP Framework For Web Artisans</h3>   ");
        writer.println("    </div>   ");
        writer.println("    <div class=\"macbook\" align=\"center\">   ");
        writer.println("        <svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:a=\"http://ns.adobe.com/AdobeSVGViewerExtensions/3.0/\"\n"
                + "            x=\"0px\" y=\"0px\" width=\"955px\" height=\"425px\" viewBox=\"0 0 694.1 384\" enable-background=\"new 0 0 694.1 384\" xml:space=\"preserve\">   ");
        writer.println("            <defs>   ");
        writer.println("            </defs>   ");
        writer.println("            <g id=\"macbook\" transform=\"translate(1.000000, 1.000000)\" sketch:type=\"MSLayerGroup\">   ");
        writer.println("                <path id=\"body-top\" sketch:type=\"MSShapeGroup\" fill=\"#FEFEFE\" stroke=\"#8492A5\" stroke-width=\"2\" d=\"M594,0H98\n"
                + "                    C84.5,0,73,11.1,73,24.8V351h546V24.8C619,11.1,607.5,0,594,0z\"></path>   ");
        writer.println("                <circle id=\"webcam\" sketch:type=\"MSShapeGroup\" fill=\"none\" stroke=\"#8492A5\" stroke-width=\"2\" cx=\"347\" cy=\"19\" r=\"4\">\n"
                + "                </circle>   ");
        writer.println("                <g id=\"body-bottom-group\" transform=\"translate(0.000000, 351.000000)\" sketch:type=\"MSShapeGroup\">   ");
        writer.println("                    <path id=\"body-bottom\" fill=\"#FDFDFD\" stroke=\"#8492A5\" stroke-width=\"2\" d=\"M640.8,31H51.3C20.6,31,0,20.5,0,16V2.4C0,1.1,1.3,0,3,0\n"
                + "                        h686.1c1.7,0,3,1.1,3,2.4v14.1C692.1,20.1,676.1,31,640.8,31z\"></path>   ");
        writer.println("                    <path id=\"bottom-seam\" fill=\"none\" stroke=\"#8492A5\" stroke-linecap=\"square\" d=\"M0.5,14.5h689.7\"></path>   ");
        writer.println("                </g>   ");
        writer.println("                <rect id=\"screen\" x=\"95\" y=\"39\" sketch:type=\"MSShapeGroup\" fill=\"#FFFFFF\" stroke=\"#8492A5\" width=\"501.1\" height=\"292\">   ");
        writer.println("                </rect>   ");
        writer.println("                <path id=\"touchpad\" sketch:type=\"MSShapeGroup\" fill=\"#FFFFFF\" stroke=\"#8492A5\" d=\"M421,352v3.1c0,2.2-4.3,2.9-7.6,2.9H278.8\n"
                + "                    c-3.5,0-7.8-0.7-7.8-2.9V352\"></path>   ");
        writer.println("            </g>   ");
        writer.println("        </svg>   ");
        writer.println("<div class=\"quadrado\">   ");
        writer.println("  Suas Postagens ");
      
        try {
            Connection con;
            ConectDB db = new ConectDB();
            con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT descricao FROM conteudo WHERE id_usuario= " + session.getAttribute("id"));
            ResultSet rs = ps.executeQuery();
            System.out.println(session.getAttribute("id"));
            while (rs.next()) {
                writer.println("<div class=\"postagens\">   ");
                writer.println(rs.getString("descricao"));
                writer.println("</div>");
                
            }

        } catch (Exception e) {
            System.out.println(e);

        }

        writer.println(" </div>   ");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");

    }

}
