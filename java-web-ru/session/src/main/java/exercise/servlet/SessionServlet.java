package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.util.Map;

import static exercise.App.getUsers;
import exercise.Users;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
                 throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                               HttpServletResponse response)
                 throws IOException, ServletException {

        // BEGIN
        String standartPassword = "password";
        String email = request.getParameter("email");
        String userPassword = request.getParameter("password");

        Map<String, String> user = users.findByEmail(email);
        if (user != null && standartPassword.equals(userPassword)) {
            request.getSession().setAttribute("userId", user.get("id"));
            request.getSession().setAttribute("flash", "Вы успешно вошли");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("flash", "Неверные логин или пароль");
            request.getSession().setAttribute("email", email);
            response.setStatus(422);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        }
        // END
    }

    private void logout(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        request.getSession().removeAttribute("userId");
        request.getSession().setAttribute("flash", "Вы успешно вышли");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(request, response);
        // END
    }
}
