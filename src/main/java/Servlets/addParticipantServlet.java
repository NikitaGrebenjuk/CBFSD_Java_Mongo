package Servlets;

import DAO.ParticipantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addone")
public class addParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addParticipantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath()).append("<br>").append("<h3> All Partcipants </h3>");
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>My Web Application</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body>");

        resp.getWriter().append("Served at: ").append(req.getContextPath()).append("<br>").append("<h3> ADD PARTICIPANT </h3>");
        resp.setContentType("text/html");
        resp.getWriter().println("<form method='post' action='addone'>");
        resp.getWriter().println("<div class='form-group'>");
        resp.getWriter().println("<label for='name'>Name:</label>");
        resp.getWriter().println("<input type='text' class='form-control' name='name' id='name' required>");
        resp.getWriter().println("</div>");
        resp.getWriter().println("<div class='form-group'>");
        resp.getWriter().println("<label for='batchName'>BatchName:</label>");
        resp.getWriter().println("<input type='text' class='form-control' name='batchName' id='batchName' required>");
        resp.getWriter().println("</div>");
        resp.getWriter().println("<button type='submit' class='btn btn-primary'>Submit</button>");
        resp.getWriter().println("</form>");
        out.print("<br> DONE: <br>");
        out.print("<div class=\"container\" style=\"margin-top: 50px;\">\n" +
                "    <div class=\"row\" style=\"margin-top:50px;\">\n" +
                "        <div class=\"col-md-6 offset-md-3\">\n" +
                "            <div class=\"list-group\">\n" +
                "                <a href=\"/CBFSD_Java_MongoDB\" class=\"list-group-item list-group-item-action\">BACK</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>");
        out.print("</body>\n" +
                "</html>\n");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String batchName = req.getParameter("batchName");
        ParticipantService participantMongoService = new ParticipantService();
        Document participant = new Document("_id",new ObjectId()).append("name",name).append("BatchName",batchName);
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>My Web Application</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body>");
        out.print(participantMongoService.addParticipant(participant).get("_id"));
        out.print("<div class=\"container\" style=\"margin-top: 50px;\">\n" +
                "    <div class=\"row\" style=\"margin-top:50px;\">\n" +
                "        <div class=\"col-md-6 offset-md-3\">\n" +
                "            <div class=\"list-group\">\n" +
                "                <a href=\"/CBFSD_Java_MongoDB\" class=\"list-group-item list-group-item-action\">BACK</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>");
        out.print("</body>\n" +
                "</html>\n");
        out.flush();
    }
}
