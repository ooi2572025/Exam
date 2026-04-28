package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String uri = req.getRequestURI();
            String actionName =
                uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".action"));

            String className = "scoremanager.main." + actionName + "Action";

            Class<?> clazz = Class.forName(className);
            Action action = (Action) clazz.getDeclaredConstructor().newInstance();
            action.execute(req, res);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
