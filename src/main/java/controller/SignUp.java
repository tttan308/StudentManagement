package controller;

import database.AccountDAO;
import model.Account;
import util.EncodePass;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Register
 */
@WebServlet(name = "SignUp", urlPatterns = { "/sign-up" })
public class SignUp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        String url = "";
        String error = "";

        if (username.equals("") || password.equals("") || repeatPassword.equals("")) {
            error = "Please fill in all the fields!";
            request.setAttribute("error", error);
            url = "/signup.jsp";
        } else if (!password.equals(repeatPassword)) {
            error = "Password does not match!";
            request.setAttribute("error", error);
            url = "/signup.jsp";
        } else {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(EncodePass.encode(password));
            AccountDAO accountDAO = new AccountDAO();
            if (accountDAO.checkAccountExist(account)) {
                error = "Username already exists!";
                request.setAttribute("error", error);
                url = "/signup.jsp";
            } else {

                accountDAO.insert(account);
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                url = "/body.jsp";
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}