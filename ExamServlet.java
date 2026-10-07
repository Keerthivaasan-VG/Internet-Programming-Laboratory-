import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");
        int score = 0;

        if ("Paris".equals(q1)) {
            score++;
        }
        if ("4".equals(q2)) {
            score++;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Exam Results</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h2>Exam Result Summary</h2>");
        out.println("<p><strong>Candidate Name:</strong> " + username + "</p>");
        out.println("<hr style=\"border:0; border-top:1px solid #e2e8f0; margin: 15px 0;\">");
        out.println("<p><strong>Question 1 (Capital of France):</strong> " + (q1 != null ? q1 : "Not Answered") + "</p>");
        out.println("<p><strong>Question 2 (2 + 2):</strong> " + (q2 != null ? q2 : "Not Answered") + "</p>");
        out.println("<h3 style=\"color: #2b6cb0; margin-top: 20px;\">Final Score: " + score + " / 2</h3>");
        out.println("<br><a href=\"index.html\" style=\"display:inline-block; padding: 8px 16px; background-color:#3182ce; color:white; text-decoration:none; border-radius:4px;\">Take Exam Again</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
