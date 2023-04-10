<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="database.ManageStudentDAO" %>
<%@ page import="model.Account" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <link href="./css/homepage.css" rel="stylesheet">
    <style>
        /* CSS for centering table header and border for table rows */
        .table thead th {
            text-align: center;
            vertical-align: middle !important;
        }

        .table tbody tr {
            border-bottom: 1px solid #dee2e6;
        }

        .table tbody tr:last-child {
            border-bottom: 0;
        }
    </style>
</head>
<body>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    String username = account.getUsername();
    ManageStudentDAO manageStudentDAO = new ManageStudentDAO();
    ArrayList<Student> students = manageStudentDAO.getStudentOfAccount(username);
    int i = 1;
%>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="sidebar.jsp" />
    <div class="col-10 bg-light text-dark vh-100">
      <div class="d-flex flex-column align-items-center justify-content-start h-100">
            <div class="d-flex flex-column align-items-center justify-content-center">
                <div class = "p-3">
                <h1 class="text-center">Danh sách sinh viên</h1>
                <table class="table table-striped table-bordered table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã sinh viên</th>
                        <th scope="col">Họ tên</th>
                        <th scope="col">Điểm</th>
                        <th scope="col">Ngày sinh</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Notes</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Student student : students) {
                    %>
                    <tr>
                        <th scope="row"><%= i++ %></th>
                        <td><%= student.getId() %></td>
                        <td><%= student.getName() %></td>
                        <td><%= student.getGrade() %></td>
                        <td><%= student.getBirthday() %></td>
                        <td><%= student.getAddress() %></td>
                        <td><%= student.getNotes() %></td>
                        <td>
                           <form action="student?action=edit-student&id=<%=student.getId()%>" method="get">
                               <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editStudentModal">
                                   Sửa
                               </button>
                           </form>
                            <a href="student?action=delete-student&id=<%=student.getId() %>" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <th scope="row"><%= i++ %></th>
                        <td colspan="7" class = "text-center">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addStudentModal">
                                Thêm sinh viên
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
<div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action = "student?action=add-student" method="post">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addStudentModalLabel">Thêm sinh viên</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="studentName" class="form-label">Tên sinh viên</label>
                        <input type="text" class="form-control" id="studentName" name = "studentName">
                    </div>
                    <div class="mb-3">
                        <label for="ID" class="form-label">Mã số sinh viên</label>
                        <input type="text" class="form-control" id="ID" name = "studentID">
                    </div>
                    <div class = "mb-3">
                        <label for="studentGrade" class="form-label">Điểm trung bình</label>
                        <input type="number" min="0" max="10" step="0.01" class="form-control" id="studentGrade" name = "studentGrade">
                    </div>
                    <div class = "mb-3">
                        <label for="studentBirthday" class="form-label">Ngày sinh</label>
                        <input type="date" class="form-control" id="studentBirthday" name = "studentBirthday">
                    </div>
                    <div class = "mb-3">
                        <label for="studentAddress" class="form-label">Địa chỉ</label>
                        <input type="text" class="form-control" id="studentAddress" name = "studentAddress">
                    </div>
                    <div class="mb-3">
                        <label for="studentNotes" class="form-label">Ghi chú</label>
                        <textarea class="form-control" id="studentNotes" name = "studentNotes"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
        </div>
        </form>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="edittudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action = "student?action=add-student" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addStudentModalLabel">Thêm sinh viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="studentName" class="form-label">Tên sinh viên</label>
                            <input type="text" class="form-control" id="studentName" name = "studentName">
                        </div>
                        <div class="mb-3">
                            <label for="ID" class="form-label">Mã số sinh viên</label>
                            <input type="text" class="form-control" id="ID" name = "studentID">
                        </div>
                        <div class = "mb-3">
                            <label for="studentGrade" class="form-label">Điểm trung bình</label>
                            <input type="number" min="0" max="10" step="0.01" class="form-control" id="studentGrade" name = "studentGrade">
                        </div>
                        <div class = "mb-3">
                            <label for="studentBirthday" class="form-label">Ngày sinh</label>
                            <input type="date" class="form-control" id="studentBirthday" name = "studentBirthday">
                        </div>
                        <div class = "mb-3">
                            <label for="studentAddress" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="studentAddress" name = "studentAddress">
                        </div>
                        <div class="mb-3">
                            <label for="studentNotes" class="form-label">Ghi chú</label>
                            <textarea class="form-control" id="studentNotes" name = "studentNotes"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
