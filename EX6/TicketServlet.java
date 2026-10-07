import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String userName = request.getParameter("user_name");
        String eventName = request.getParameter("event_name");
        String numTicketsStr = request.getParameter("num_tickets");
        int numTickets = Integer.parseInt(numTicketsStr);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Booking Confirmation</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_db?useSSL=false&serverTimezone=UTC", "root", "");

            // 1. Insert ticket details into database
            String insertQuery = "INSERT INTO tickets (user_name, event_name, num_tickets) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, userName);
            ps.setString(2, eventName);
            ps.setInt(3, numTickets);
            ps.executeUpdate();
            ps.close();

            out.println("<h2 style=\"color: #2b6cb0;\">Booking Successful!</h2>");
            out.println("<p>Thank you, <strong>" + userName + "</strong>. Your tickets have been booked.</p>");

            // 2. Fetch and display updated ticket records
            out.println("<h3 style=\"margin-top:25px;\">All Booked Tickets</h3>");
            out.println("<table>");
            out.println("<tr><th>Ticket ID</th><th>User Name</th><th>Event Name</th><th>No. of Tickets</th><th>Booking Date</th></tr>");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tickets");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("ticket_id") + "</td>");
                out.println("<td>" + rs.getString("user_name") + "</td>");
                out.println("<td>" + rs.getString("event_name") + "</td>");
                out.println("<td>" + rs.getInt("num_tickets") + "</td>");
                out.println("<td>" + rs.getTimestamp("booking_date") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<p style=\"color: red;\">Error: " + e.getMessage() + "</p>");
        }

        out.println("<br><a href=\"index.html\" style=\"display:inline-block; padding: 8px 16px; background-color:#3182ce; color:white; text-decoration:none; border-radius:4px;\">Book Another Ticket</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
