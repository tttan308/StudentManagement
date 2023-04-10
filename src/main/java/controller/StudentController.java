package controller;

import database.ManageStudentDAO;
import database.StudentDAO;
import model.Account;
import model.ManageStudent;
import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Student", urlPatterns = { "/student" })
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("show-list")){
            String url = "/student-list.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }
        else if(action.equals("add-student")){
            Add(request, response);
        }
        else if(action.equals("edit")){
            Edit(request, response);
        }
        else if(action.equals("delete-student")){
            Delete(request, response);
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        byte[] bytes = request.getParameter("studentName").getBytes(StandardCharsets.ISO_8859_1);
        String name = new String(bytes, StandardCharsets.UTF_8);
        String studentID = request.getParameter("studentID");
        float grade = Float.parseFloat(request.getParameter("studentGrade"));
        Date birthday = Date.valueOf(request.getParameter("studentBirthday"));
        bytes = request.getParameter("studentAddress").getBytes(StandardCharsets.ISO_8859_1);
        String address = new String(bytes, StandardCharsets.UTF_8);
        bytes = request.getParameter("studentNotes").getBytes(StandardCharsets.ISO_8859_1);
        String notes = new String(bytes, StandardCharsets.UTF_8);

        Student student = new Student(studentID, name, grade, birthday, address, notes);
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.insert(student);

        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();
        ManageStudent manageStudent = new ManageStudent(username, studentID);
        ManageStudentDAO manageStudentDAO = new ManageStudentDAO();
        manageStudentDAO.insert(manageStudent);

        String url = "/student-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String studentID = request.getParameter("id");
        ManageStudentDAO manageStudentDAO = new ManageStudentDAO();
        manageStudentDAO.delete(studentID);

        String url = "/student-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response) {
    }
}
