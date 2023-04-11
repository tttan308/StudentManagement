<%@ page import="model.Account" %>
<%@ page import="model.ManageCourse" %>
<%@ page import="database.ManageCourseDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Trang chủ</title>
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
</head>
<body>
<%
  Account account = (Account) request.getSession().getAttribute("account");
  String username = account.getUsername();

  ManageCourseDAO manageCourseDAO = new ManageCourseDAO();
  ArrayList<Course> courses = manageCourseDAO.getCourseOfAccount(username);
  int i = 1;
%>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="sidebar.jsp" />
    <div class="col-10 bg-light text-dark vh-100">
      <div class="d-flex flex-column align-items-center justify-content-start h-100">
        <!--Find course-->
        <div class="p-3">
          <form action="course?action=find-course" method="post">
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="Nhập tên môn học" name="nameCourse">
              <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Tìm kiếm</button>
            </div>
          </form>
        </div>
        <div class="d-flex flex-column align-items-center justify-content-center">
          <div class = "p-3">
            <h1 class="text-center">Danh sách môn học</h1>
            <table class="table table-striped table-bordered table-hover" id = "myTable">
              <thead class="thead-dark">
              <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã môn học</th>
                <th scope="col" onclick="sort(true, 1)" id = "sortName">Tên môn học</th>
                <th scope="col">Giảng viên</th>
                <th scope="col">Năm học</th>
                <th scope="col">Học kỳ</th>
                <th scope="col">Notes</th>
                <th scope="col">Số tín chỉ</th>
                <th scope="col">Thao tác</th>
              </tr>
              </thead>
              <tbody>
              <%
                for (Course course : courses) {
              %>
                <tr>
                    <th scope="row"><%= i++ %></th>
                    <td><%= course.getClassID() %></td>
                    <td><%= course.getName() %></td>
                    <td><%= course.getLecture() %></td>
                    <td><%= course.getYear() %></td>
                    <td><%= course.getSemester() %></td>
                    <td><%= course.getNotes() %></td>
                    <td><%= course.getCredits() %></td>
                    <td>
                      <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editCourseModal" onclick="fillEditModal('<%=course.getClassID()%>, <%=course.getLecture()%>, <%=course.getYear()%>, <%=course.getSemester()%>')">Sửa</button>
                      <a href="course?action=delete-course&id=<%=course.getClassID()%>&lecture=<%=course.getLecture()%>&year=<%=course.getYear()%>&semester=<%=course.getSemester()%>" class="btn btn-danger">Xóa</a>
                      <button class = "btn btn-success" data-bs-toggle="modal" data-bs-target="#showCourseListcourseModal" onclick="">Xem danh sách học sinh</button>
                    </td>
              <%
                }
              %>
              <tr>
                <th scope="row"><%= i++ %></th>
                <td colspan="9" class = "text-center">
                  <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCourseModal">
                    Thêm khóa học
                  </button>
                </td>
              <tr>
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

<!-- Modal -->
<div class="modal fade" id="addCourseModal" tabindex="-1" aria-labelledby="addCourseModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form action = "course?action=add-course" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addcourseModalLabel">Thêm môn học</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="courseName" class="form-label">Tên môn học</label>
              <input type="text" class="form-control" id="courseName" name = "courseName">
            </div>
            <div class="mb-3">
              <label for="ID" class="form-label">Mã môn học</label>
              <input type="text" class="form-control" id="ID" name = "courseID" required>
            </div>
            <div class = "mb-3">
              <label for="courseLecture" class="form-label">Giảng viên</label>
              <input type="text" class="form-control" id="courseLecture" name = "courseLecture" required>
            </div>
            <div class = "mb-3">
              <label for="courseYear" class="form-label">Năm học</label>
              <input type="text" class="form-control" id="courseYear" name = "courseYear" required>
            </div>
            <div class = "mb-3">
              <label for="courseSemester" class="form-label">Học kỳ</label>
              <input type="text" class="form-control" id="courseSemester" name = "courseSemester" required>
            </div>
            <div class="mb-3">
              <label for="courseNotes" class="form-label">Ghi chú</label>
              <textarea class="form-control" id="courseNotes" name = "courseNotes"></textarea>
            </div>
            <div class = "mb-3">
              <label for="courseCredit" class="form-label">Số tín chỉ</label>
              <input type="number" min = "1" max = "20" step="1.0" class="form-control" id="courseCredit" name = "courseCredit">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary">Thêm</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body>
</html>
