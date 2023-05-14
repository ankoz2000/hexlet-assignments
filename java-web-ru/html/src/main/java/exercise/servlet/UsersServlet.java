package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path path = Paths.get("src/main/resources/users.json");

        ObjectMapper om = new ObjectMapper();
        return om.readValue(Files.readString(path), List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map> users = getUsers();
        if (!users.isEmpty()) {
            StringBuilder result = new StringBuilder("<table>");
            String usersTable = users.stream()
                    .map(u -> "<tr>" +
                            "<td>" + u.get("id") + "</td>" +
                            "<td>" +
                            "<a href=\"/users/"
                            + u.get("id") + "\">"
                            + u.get("firstName") + " "
                            + u.get("lastName") + "</a>" +
                            "</td>" +
                            "</tr>")
                    .collect(Collectors.joining());
            result.append(usersTable);
            result.append("</table>");
            PrintWriter pw = new PrintWriter(response.getWriter());
            pw.println(result);
        }
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map> users = getUsers();
        if (!users.isEmpty()) {
            StringBuilder result = new StringBuilder();
            String userTable = users.stream()
                    .filter(u -> u.get("id").equals(id))
                    .map(u -> "<tr>" +
                            "<td>" + u.get("id") + "</td>" +
                            "<td>" + u.get("firstName") + "</td>" +
                            "<td>" + u.get("lastName") + "</td>" +
                            "<td>" + u.get("email") + "</td>" +
                            "</tr>")
                    .collect(Collectors.joining());
            if (userTable.isEmpty()) {
                result.append("Not found");
                response.sendError(404, result.toString());
            } else {
                result.append("<table>");
                result.append(userTable);
                result.append("</table>");

                PrintWriter pw = new PrintWriter(response.getWriter());
                pw.println(result);
            }
        }
        // END
    }
}
