package controller;

import database.CourseDAO;
import database.ManageCourseDAO;
import database.TakePartInCourseDAO;
import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Course", urlPatterns = { "/course" })
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("show-list")){
            String url = "/course-list.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }
        else if(action.equals("add-course")){
            Add(request, response);
        }
        else if(action.equals("edit-course")){
            Edit(request, response);
        }
        else if(action.equals("delete-course")){
            Delete(request, response);
        }
        else if(action.equals("find-course")){
            Find(request, response);
        }
        else if(action.equals("show-student-list")){
            ShowAction(request, response);
        }
        else if(action.equals("add-student-into-course")){
            AddStudentIntoCourse(request, response);
        }
        else if(action.equals("delete-student-from-course")){
            DeleteStudentFromCourse(request, response);
        }
        else if(action.equals("edit-score")){
            EditScore(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        byte[] bytes = request.getParameter("courseName").getBytes(StandardCharsets.ISO_8859_1);
        String name = new String(bytes, StandardCharsets.UTF_8);
        String id = request.getParameter("courseID");
        bytes = request.getParameter("courseLecture").getBytes(StandardCharsets.ISO_8859_1);
        String lecturer = new String(bytes, StandardCharsets.UTF_8);
        String year = request.getParameter("courseYear");
        int semester = Integer.parseInt(request.getParameter("courseSemester"));
        bytes = request.getParameter("courseNotes").getBytes(StandardCharsets.ISO_8859_1);
        String notes = new String(bytes, StandardCharsets.UTF_8);
        int credit = Integer.parseInt(request.getParameter("courseCredit"));

        Course course = new Course(id, name, lecturer, year, semester, notes, credit);
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.insert(course);

        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();
        ManageCourse manageCourse = new ManageCourse(username, id, lecturer, year, semester);
        ManageCourseDAO manageCourseDAO = new ManageCourseDAO();
        manageCourseDAO.insert(manageCourse);

        String url = "/course-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String lecture = request.getParameter("lecture");
        String year = request.getParameter("year");
        int semester = Integer.parseInt(request.getParameter("semester"));

        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();

        ManageCourseDAO manageCourseDAO = new ManageCourseDAO();
        manageCourseDAO.delete(username, id, lecture, year, semester);

        TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
        takePartInCourseDAO.deleteCourseOfTakePartIn(id, lecture, year, semester);

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.delete(id, lecture, year, semester);

        String url = "/course-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        byte[] bytes = request.getParameter("name-edit").getBytes(StandardCharsets.ISO_8859_1);
        String name = new String(bytes, StandardCharsets.UTF_8);
        String id = request.getParameter("id-edit");
        bytes = request.getParameter("lecture-edit").getBytes(StandardCharsets.ISO_8859_1);
        String lecturer = new String(bytes, StandardCharsets.UTF_8);
        String year = request.getParameter("year-edit");
        int semester = Integer.parseInt(request.getParameter("semester-edit"));
        bytes = request.getParameter("notes-edit").getBytes(StandardCharsets.ISO_8859_1);
        String notes = new String(bytes, StandardCharsets.UTF_8);
        int credit = Integer.parseInt(request.getParameter("credit-edit"));

        Course course = new Course(id, name, lecturer, year, semester, notes, credit);
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.update(course);

        String url = "/course-list.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void Find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        byte[] bytes = request.getParameter("nameCourse").getBytes(StandardCharsets.ISO_8859_1);
        String nameCourse = new String(bytes, StandardCharsets.UTF_8);

        request.setAttribute("nameCourse", nameCourse);

        String url = "/find-course.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void ShowAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String lecture = request.getParameter("lecture");
        String year = request.getParameter("year");
        int semester = Integer.parseInt(request.getParameter("semester"));

        request.setAttribute("id", id);
        request.setAttribute("lecture", lecture);
        request.setAttribute("year", year);
        request.setAttribute("semester", semester);

        String url = "/show-student-list-of-course.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void AddStudentIntoCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String lecture = request.getParameter("lecture");
        String year = request.getParameter("year");
        int semester = Integer.parseInt(request.getParameter("semester"));
        String selectedID = request.getParameter("selected-id");
        float score = Float.parseFloat(request.getParameter("score"));

        TakePartInCourse takePartInCourse = new TakePartInCourse(selectedID, id, lecture, year, semester, score);
        TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
        takePartInCourseDAO.insert(takePartInCourse);

        request.setAttribute("id", id);
        request.setAttribute("lecture", lecture);
        request.setAttribute("year", year);
        request.setAttribute("semester", semester);

        String url = "/show-student-list-of-course.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void DeleteStudentFromCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String lecture = request.getParameter("lecture");
        String year = request.getParameter("year");
        int semester = Integer.parseInt(request.getParameter("semester"));
        String idStudent = request.getParameter("studentId");

        TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
        takePartInCourseDAO.delete(idStudent, id, lecture, year, semester);

        request.setAttribute("id", id);
        request.setAttribute("lecture", lecture);
        request.setAttribute("year", year);
        request.setAttribute("semester", semester);

        String url = "/show-student-list-of-course.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void EditScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String lecture = request.getParameter("lecture");
        String year = request.getParameter("year");
        int semester = Integer.parseInt(request.getParameter("semester"));
        float score = Float.parseFloat(request.getParameter("score"));
        String idStu = request.getParameter("id-edit");
        TakePartInCourse takePartInCourse = new TakePartInCourse(idStu, id, lecture, year, semester, score);

        TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
        takePartInCourseDAO.update(takePartInCourse);

        request.setAttribute("id", id);
        request.setAttribute("lecture", lecture);
        request.setAttribute("year", year);
        request.setAttribute("semester", semester);

        String url = "/show-student-list-of-course.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
