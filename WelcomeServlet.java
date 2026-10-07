import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Welcome</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        
        if (session != null && session.getAttribute("user") != null) {
            String user = (String) session.getAttribute("user");
            out.println("<h2 style=\"color: #2b6cb0;\">Welcome, User</h2>");
            out.println("<p style=\"font-size: 18px; text-align: center;\">Hello, <strong>" + user + "</strong>!</p>");
        } else {
            out.println("<h2 style=\"color: red;\">Session Expired or Invalid</h2>");
            out.println("<p>Please log in again.</p>");
        }
        
        out.println("<br><a href=\"index.html\" style=\"display:block; text-align:center; padding: 8px 16px; background-color:#3182ce; color:white; text-decoration:none; border-radius:4px;\">Back to Login</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
