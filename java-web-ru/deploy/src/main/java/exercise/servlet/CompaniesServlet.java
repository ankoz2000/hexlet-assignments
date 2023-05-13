package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        List<String> result = new ArrayList<>();
        PrintWriter pw = new PrintWriter(response.getWriter());
        if (request.getQueryString() != null && request.getQueryString().contains("search")) {
            String toContains = request.getParameter("search");
            if (toContains != null) {
                result = companies.stream()
                        .filter(c -> c.contains(toContains))
                        .collect(Collectors.toList());
                if (result.isEmpty()) {
                    result.add("Companies not found");
                }
            }
        } else {
            result = companies;
        }
        pw.println(result.stream()
                .collect(Collectors.joining("\n")));
        // END
    }
}
