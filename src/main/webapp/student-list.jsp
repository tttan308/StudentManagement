<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="database.ManageStudentDAO" %>
<%@ page import="model.Account" %>
<%@ page import="database.TakePartInCourseDAO" %>
<%@ page import="model.TakePartInCourse" %>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

        th {
            position: relative;
            cursor: pointer;
        }

        th::before {
            right: 8px;
            border-width: 5px 5px 0 5px;
            border-color: #000 transparent transparent transparent;
        }

        th::after {
            right: 2px;
            border-width: 0 5px 5px 5px;
            border-color: transparent transparent #000 transparent;
        }

        th.desc::before {
            border-width: 0 5px 5px 5px;
            border-color: transparent transparent #000 transparent;
        }

        th.asc::after {
            border-width: 5px 5px 0 5px;
            border-color: #000 transparent transparent transparent;
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
          <!--Find student-->
            <div class="p-3">
                <form action="student?action=find-student" method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Nhập tên sinh viên" name="studentName">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Tìm kiếm</button>
                    </div>
                </form>
            </div>
            <div class="d-flex flex-column align-items-center justify-content-center">
                <div class = "p-3">
                <h1 class="text-center">Danh sách sinh viên</h1>
                <table class="table table-striped table-bordered table-hover" id = "myTable">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã sinh viên</th>
                        <th scope="col" onclick="sort(true, 1)" id = "sortName">Họ tên</th>
                        <th scope="col" onclick="sort(true, 2)" id = "sortGrade">Khóa</th>
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
                    <tr id="student-row-<%= student.getId() %>">
                        <th scope="row"><%= i++ %></th>
                        <td data-info="student-id"><%= student.getId() %></td>
                        <td data-info="student-name"><%= student.getName() %></td>
                        <td data-info="student-grade"><%= student.getGrade() %></td>
                        <td data-info="student-birthday"><%= student.getBirthday() %></td>
                        <td data-info="student-address"><%= student.getAddress() %></td>
                        <td data-info="student-notes"><%= student.getNotes() %></td>
                        <td>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editStudentModal" onclick="fillEditModal('<%= student.getId() %>')">Sửa</button>
                            <a href="student?action=delete-student&id=<%=student.getId() %>" class="btn btn-danger">Xóa</a>
                            <button class = "btn btn-success" data-bs-toggle="modal" data-bs-target="#showCourseListStudentModal" onclick="getID('<%= student.getId() %>')">Xem danh sách khóa học</button>
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

<!-- Modal Add Student-->
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
                        <input type="text" class="form-control" id="ID" name = "studentID" required>
                    </div>
                    <div class = "mb-3">
                        <label for="studentGrade" class="form-label">Khóa học</label>
                        <input type="text" class="form-control" id="studentGrade" name = "studentGrade">
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

<!-- Modal Edit Student-->
<div class="modal fade" id="editStudentModal" tabindex="-1" aria-labelledby="editStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="student?action=edit-student" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editStudentModalLabel">Chỉnh sửa thông tin sinh viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                    <div class="modal-body">
                        <form>
                            <input type="hidden" name="id-edit" id="editId">
                            <div class="form-group">
                                <label for="editName">Họ tên</label>
                                <input type="text" class="form-control" id="editName" name="name-edit" required>
                            </div>
                            <div class="form-group">
                                <label for="editGrade">Khóa học</label>
                                <input type="text" class="form-control" id="editGrade" name="grade-edit" required>
                            </div>
                            <div class="form-group">
                                <label for="editBirthday">Ngày sinh</label>
                                <input type="date" class="form-control" id="editBirthday" name="birthday-edit" required>
                            </div>
                            <div class="form-group">
                                <label for="editAddress">Địa chỉ</label>
                                <input type="text" class="form-control" id="editAddress" name="address-edit" required>
                            </div>
                            <div class="form-group">
                                <label for="editNotes">Notes</label>
                                <input type="text" class="form-control" id="editNotes" name="notes-edit">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
            </div>
        </form>
    </div>
</div>

<!-- Modal Show Course List-->
<div class="modal fade" id="showCourseListStudentModal" tabindex="-1" aria-labelledby="showCourseListStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="student?action=show-course-list-student" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="showCourseListStudentModalLabel">Chọn năm cần xem</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" name="show-course-list-id" id="show-course-list-id">
                        <%
                            TakePartInCourseDAO tpi = new TakePartInCourseDAO();
                            ArrayList<String> years = tpi.getAllYear();
                        %>
                        <div class="form-group">
                            <label for="selectYear">Chọn năm</label>
                            <select class="form-control" id="selectYear" name="selected-year">
                                <% for (String year : years) { %>
                                <option value="<%= year %>"><%= year %></option>
                                <% } %>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Xem danh sách khóa học</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    function fillEditModal(studentId) {
        idStu = "student-row-" + studentId;
        const studentRow = document.getElementById(idStu);
        const id = studentRow.querySelector('[data-info="student-id"]').textContent;
        const name = studentRow.querySelector('[data-info="student-name"]').textContent;
        const grade = studentRow.querySelector('[data-info="student-grade"]').textContent;
        const birthday = studentRow.querySelector('[data-info="student-birthday"]').textContent;
        const address = studentRow.querySelector('[data-info="student-address"]').textContent;
        const notes = studentRow.querySelector('[data-info="student-notes"]').textContent;

        const editIdInput = document.querySelector('#editId');
        const editNameInput = document.querySelector('#editName');
        const editGradeInput = document.querySelector('#editGrade');
        const editBirthdayInput = document.querySelector('#editBirthday');
        const editAddressInput = document.querySelector('#editAddress');
        const editNotesInput = document.querySelector('#editNotes');

        editIdInput.value = id;
        editNameInput.value = name;
        editGradeInput.value = grade;
        editBirthdayInput.value = birthday;
        editAddressInput.value = address;
        editNotesInput.value = notes;
    }

    function sort(reverse, col) {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 3); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("td")[col];
                console.log(x);
                y = rows[i + 1].getElementsByTagName("td")[col];
                //Chỉ lấy tên
                xValue = x.textContent.split(" ")[x.textContent.split(" ").length - 1];
                yValue = y.textContent.split(" ")[y.textContent.split(" ").length - 1];
                if (reverse) {
                    if (xValue < yValue) {
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (xValue > yValue) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }

        //Đổi hướng sắp xếp
        if (reverse) {
            if(col === 1 ){
                document.getElementById("sortName").setAttribute("onclick", "sort(false, " +col + ")");
            }
            else {
                document.getElementById("sortGrade").setAttribute("onclick", "sort(false, " +col + ")");
            }
        } else {
            if(col === 1 ){
                document.getElementById("sortName").setAttribute("onclick", "sort(true, " +col + ")");
            }
            else {
                document.getElementById("sortGrade").setAttribute("onclick", "sort(true, " +col + ")");
            }
        }
    }

    function getID(studentID) {
        idStu = "student-row-" + studentID;
        const studentRow = document.getElementById(idStu);
        const id = studentRow.querySelector('[data-info="student-id"]').textContent;
        const showCourseListIdInput = document.querySelector('#show-course-list-id');
        showCourseListIdInput.value = id;
        console.log(id);
    }

</script>
</body>
</html>
