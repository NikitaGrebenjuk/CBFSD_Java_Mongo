package Servlets;

import DAO.ParticipantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getall")
public class ParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>My Web Application</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body>");
        resp.getWriter().append("Served at: ").append(req.getContextPath()).append("<br>").append("<h3> ALL PARTICIPANTS </h3>");

        ParticipantService participantMongoService = new ParticipantService();
        List<Document> result = participantMongoService.getAllParticipants();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        out.append("<table class=\"table table-bordered table-striped\"> <tr> <th>Name</th> <th>BatchName</th> <th>ID</th> </tr>");
        for(Document doc: result){
            System.out.println(doc.get("name"));
            out.append("<tr>\n" +
                    "                <td>" + doc.get("name") +"</td>\n" +
                    "                <td>" + doc.get("BatchName") +"</td>\n" +
                    "                <td>" + doc.get("_id") +"</td>\n" +
                    "              </tr>\n");
        }
        out.append("</table>");

        System.out.println("DONE: participantServlet doGet");

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
    /**
        String jsonResponse = null;
        PrintWriter out = resp.getWriter();
        ParticipantService participantMongoService = new ParticipantService();
        jsonResponse = new Gson().toJson(participantMongoService.getAllParticipants());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonResponse);
        out.flush();
     **/
    }
}
