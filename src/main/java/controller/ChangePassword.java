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
@WebServlet(name = "ChangePassword", urlPatterns = { "/change-password" })
public class ChangePassword extends HttpServlet {
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
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String repeatPassword = request.getParameter("repeat-new-password");

        String url = "";
        String error = "";

        if (oldPassword.equals("") || newPassword.equals("") || repeatPassword.equals("")) {
            error = "Please fill in all the fields!";
            request.setAttribute("error", error);
            url = "/changePassword.jsp";
        } else if (!newPassword.equals(repeatPassword)) {
            error = "Password does not match!";
            request.setAttribute("error", error);
            url = "/changePassword.jsp";
        } else {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account.getPassword().equals(EncodePass.encode(oldPassword))) {
                account.setPassword(EncodePass.encode(newPassword));
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.updatePassword(account);
                url = "/homepage.jsp";
            } else {
                error = "Old password is incorrect!";
                request.setAttribute("error", error);
                url = "/changePassword.jsp";
            }
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}