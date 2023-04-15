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

@WebServlet(name = "AccountController", urlPatterns = { "/account" })
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("sign-in")){
            SignIn(request, response);
        }
        else if(action.equals("sign-up")){
            SignUp(request, response);
        }
        else if(action.equals("change-password")){
            ChangePassword(request, response);
        }
        else if(action.equals("sign-out")){
            SignOut(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected void SignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            url = "/index.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
    public void SignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        String url = "";
        String error = "";

        if (username.equals("") || password.equals("") || repeatPassword.equals("")) {
            error = "Vui lòng điền đẩy đủ thông tin!";
            request.setAttribute("error", error);
            url = "/signup.jsp";
        } else if (!password.equals(repeatPassword)) {
            error = "Mật khẩu nhập lại không khớp!";
            request.setAttribute("error", error);
            url = "/signup.jsp";
        } else {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(EncodePass.encode(password));
            AccountDAO accountDAO = new AccountDAO();
            if (accountDAO.checkAccountExist(account)) {
                error = "Tài khoản đã tồn tại, vui lòng chọn tên tài khoản khác!";
                request.setAttribute("error", error);
                url = "/signup.jsp";
            } else {
                accountDAO.insert(account);
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                url = "/homepage.jsp";
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public void ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String repeatPassword = request.getParameter("repeat-new-password");

        String url = "";
        String error = "";

        if (oldPassword.equals("") || newPassword.equals("") || repeatPassword.equals("")) {
            error = "PVui lòng điền đẩy đủ thông tin!";
            request.setAttribute("error", error);
            url = "/changePassword.jsp";
        } else if (!newPassword.equals(repeatPassword)) {
            error = "Mật khẩu nhập lại không khớp!";
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
                error = "Mật khẩu cũ không chính xác!";
                request.setAttribute("error", error);
                url = "/changePassword.jsp";
            }
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public void SignOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        response.sendRedirect(url+"/index.jsp");
    }
}