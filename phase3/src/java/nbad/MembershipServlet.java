/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbad;

import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.UserTable;
import static java.lang.System.out;

/**
 *
 * @author John Won
 */
@WebServlet(name = "MembershipServlet", urlPatterns = {"/membership"})
public class MembershipServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "login";  // default action
        }
        if (action.equals("signup")) {
            response.sendRedirect("signup.jsp");  // default action
        }
        if (action.equals("login")) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
        if (action.equals("logout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("username");
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action == null) {
            action = "signup";  // default action
        }
        if (action.equals("signup")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = new User(firstName, lastName, email, password);
            UserTable.addRecord(user);

            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (action.equals("login")) {
            //get email and password input
            String username = request.getParameter("email");
            String password = request.getParameter("password");

            //get user from user table.
            User user = UserTable.getUser(username);
            //if username and password is correct shoot print out
            if (username.equals(user.getEmail()) && password.equals(user.getPassword())) {

                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
            }//if username is incorrect shoot print out
            if (username == null || password == null
                    || !username.equals(user.getEmail())
                    || !password.equals(user.getPassword())) {
                out.println("Invalid email or password");
                String alert = "<script> alert('Invalid username or password');</script>";
                session.setAttribute("alert", alert);
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
        UserTable.getUsersMap();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
