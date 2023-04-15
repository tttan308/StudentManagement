<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="database.TakePartInCourseDAO" %>
<%@ page import="java.util.Map" %>
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
  </style>
</head>
<body>
<%
    String id = (String) request.getAttribute("id");
    String lecture = (String) request.getAttribute("lecture");
    String year = (String) request.getAttribute("year");
    int semester = (int) request.getAttribute("semester");

    ArrayList<Map<Student, Float>> students = new ArrayList<>();
    TakePartInCourseDAO takePartInCourseDAO = new TakePartInCourseDAO();
    students = takePartInCourseDAO.getAllStudentOfCourse(id, lecture, year, semester);
    int i = 1;
%>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="sidebar.jsp" />
    <div class="col-10 bg-light text-dark vh-100">
      <div class="d-flex flex-column align-items-center justify-content-start h-100">
        <div class="d-flex flex-column align-items-center justify-content-center">
          <div class = "p-3">
            <h1 class="text-center">Danh sách sinh viên thuộc môn <%=id%></h1>
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
                <th scope="col">Điểm</th>
                <th scope="col">Thao tác</th>
              </tr>
              </thead>
              <tbody>
              <%
                for (Map<Student, Float> student : students) {
                  Student s = student.keySet().iterator().next();
                  Float grade = student.get(s);
              %>
                <tr>
                  <th scope="row"><%= i++ %></th>
                  <td><%= s.getId() %></td>
                  <td><%= s.getName() %></td>
                  <td><%= s.getGrade() %></td>
                  <td><%= s.getBirthday() %></td>
                  <td><%= s.getAddress() %></td>
                  <td><%= s.getNotes() %></td>
                  <td><%= grade %></td>
                  <td>
                    <a href="course?action=delete-student-from-course&id=<%=id%>&lecture=<%=lecture%>&year=<%=year%>&semester=<%=semester%>&studentId=<%=s.getId()%>">
                      <button type="button" class="btn btn-danger">Xóa</button>
                    </a>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editScoreModal" onclick="repID('<%=s.getId()%>')">Sửa điểm</button>
                  </td>
                </tr>
              <%
                }
              %>
              <tr>
                <th scope="row"><%= i++ %></th>
                <td colspan="8" class = "text-center">
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
    <form action = "course?action=add-student-into-course&id=<%=id%>&lecture=<%=lecture%>&year=<%=year%>&semester=<%=semester%>" method="post">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="showCourseListStudentModalLabel">Chọn sinh viên cần thêm</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form>
                <%
                  TakePartInCourseDAO tpi = new TakePartInCourseDAO();
                  ArrayList<String> res = tpi.getAllStudentExcept(id, lecture, year, semester);
                %>
                <div class="form-group">
                  <label for="selectYear">Chọn mã sinh viên cần thêm</label>
                  <select class="form-control" id="selectYear" name="selected-id">
                    <% for (String _id : res) { %>
                    <option value="<%= _id%>"><%=_id %></option>
                    <% } %>
                  </select>
                </div>
              </form>
              <div class="form-group">
                <label for="addScore">Nhập điểm (nếu chưa có điểm nhập 0.0)</label>
                <input type="number" min = "0.0" max = "10.0" step = "0.1" class="form-control" id="addScore" name="score" required>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
              <button type="submit" class="btn btn-primary">Thêm sinh viên</button>
            </div>
          </div>
    </form>
  </div>
</div>

<div class="modal fade" id="editScoreModal" tabindex="-1" aria-labelledby="editScoreModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form action = "course?action=edit-score&id=<%=id%>&lecture=<%=lecture%>&year=<%=year%>&semester=<%=semester%>" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editScoreModalLabel">Cập nhật điểm mới</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <input type = "hidden" class = "" id = "IDedit" name = "id-edit">
          <div class="form-group">
            <label for="addScore">Nhập điểm (nếu chưa có điểm nhập 0.0)</label>
            <input type="number" min = "0.0" max = "10.0" step = "0.1" class="form-control" id="editScore" name="score" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
          <button type="submit" class="btn btn-primary">Cập nhật</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
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

  function repID(id) {
    document.getElementById("IDedit").value = id;
  }
</script>
</body>
</html>
