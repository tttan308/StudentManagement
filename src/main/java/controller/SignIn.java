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
@WebServlet(name = "SignIn", urlPatterns = { "/sign-in" })
public class SignIn extends HttpServlet {
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

        password = EncodePass.encode(password);


        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);


        AccountDAO accountDAO = new AccountDAO();
        Account acc = accountDAO.selectByUsernameAndPassword(account);

        String url = "";
        if(acc != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", acc);
            url = "/homepage.jsp";
        } else {
            request.setAttribute("error", "Username or password is incorrect");
            url = "/index.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}