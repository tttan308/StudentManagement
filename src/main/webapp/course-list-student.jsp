<%@ page import="database.TakePartInCourseDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Course" %>
<%@ page import="model.Account" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách khóa học của sinh viên</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%
    String id = request.getAttribute("id").toString();
    String year = request.getAttribute("year").toString();
    Account account = (Account) request.getSession().getAttribute("account");
    String username = account.getUsername();

    TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
    ArrayList<Map<Course, Float>> coursesList = new ArrayList<>();
    coursesList = takePartInCourseDAO.getAllCourseOfStudent(id, year, username);
    int i = 1;
%>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="sidebar.jsp" />
        <div class="col-10 bg-light text-dark vh-100">
            <div class="d-flex flex-column align-items-center justify-content-start h-100">
                <div class="d-flex flex-column align-items-center justify-content-center">
                    <div class = "p-3">
                        <h1 class="text-center">Danh sách khóa học và điểm của sinh viên có mã <%=id%></h1>
                        <table class="table table-striped table-bordered table-hover" id = "myTable">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Mã môn học</th>
                                <th scope="col">Tên môn học</th>
                                <th scope="col">Tên giảng viên</th>
                                <th scope="col">Năm học</th>
                                <th scope="col">Học kỳ</th>
                                <th scope="col">Ghi chú</th>
                                <th scope="col">Điểm</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Map<Course, Float> courseMap : coursesList) {
                                    Course course = courseMap.keySet().iterator().next();
                                    Float score = courseMap.get(course);
                            %>
                            <tr>
                                <th scope="row"><%= i++ %></th>
                                <td><%= course.getClassID() %></td>
                                <td><%= course.getName() %></td>
                                <td><%= course.getLecture() %></td>
                                <td><%= course.getYear() %></td>
                                <td><%= course.getSemester() %></td>
                                <td><%= course.getNotes() %></td>
                                <td><%= score %> </td>
                            </tr>
                            <%
                                }
                            %>
                            </tr>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
