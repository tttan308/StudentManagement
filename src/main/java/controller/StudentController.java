package controller;

import database.ManageStudentDAO;
import database.StudentDAO;
import model.Account;
import model.ManageStudent;
import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        else if(action.equals("edit-student")){
            Edit(request, response);
        }
        else if(action.equals("delete-student")){
            Delete(request, response);
        }
        else if(action.equals("show-course-list-student")){
            ShowAction(request, response);
        }
        else if(action.equals("find-student")){
            Find(request, response);
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
        String grade = request.getParameter("studentGrade");
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

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.delete(studentID);

        String url = "/student-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        byte[] bytes = request.getParameter("name-edit").getBytes(StandardCharsets.ISO_8859_1);
        String name = new String(bytes, StandardCharsets.UTF_8);
        String grade = request.getParameter("grade-edit");
        Date birthday = Date.valueOf(request.getParameter("birthday-edit"));
        bytes = request.getParameter("address-edit").getBytes(StandardCharsets.ISO_8859_1);
        String address = new String(bytes, StandardCharsets.UTF_8);
        bytes = request.getParameter("notes-edit").getBytes(StandardCharsets.ISO_8859_1);
        String notes = new String(bytes, StandardCharsets.UTF_8);

        String id = request.getParameter("id-edit");
        Student student = new Student(id, name, grade, birthday, address, notes);
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.update(student);
        String url = "/student-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void ShowAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String studentID = request.getParameter("show-course-list-id");
        String year = request.getParameter("selected-year");

        request.setAttribute("id", studentID);
        request.setAttribute("year", year);

        String url = "/course-list-student.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        byte[] bytes = request.getParameter("studentName").getBytes(StandardCharsets.ISO_8859_1);
        String studentName = new String(bytes, StandardCharsets.UTF_8);
        request.setAttribute("studentName", studentName);
        String url = "/find-student.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
