package Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String parameter=request.getParameter("button");

        if(parameter==null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }else if(parameter.equalsIgnoreCase("registrazione")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.html");
            dispatcher.forward(request, response);
        }else if(parameter.equalsIgnoreCase("login")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
    }
}