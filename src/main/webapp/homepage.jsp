<%@ page import="model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
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
</head>
<body>
<%
    Account account = (Account) session.getAttribute("account");
    String username = account.getUsername();
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-2 bg-dark text-light vh-100">
            <div class="d-flex flex-column align-items-center justify-content-between h-100">
                <div class="p-3">
                    <a href="homepage.jsp" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-light text-decoration-none">
                        <span class="fs-4">
                            <h2 class="text-light">Student Management</h2>
                        </span>
                    </a>
                    <hr>
                    <ul class="nav flex-column mb-4">
                        <li class="nav-item">
                            <a href="#" class="nav-link text-light">
                                <h5> Student List </h5>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link text-light">
                                <h5> Course List </h5>
                            </a>
                        </li>
                    </ul>
                </div>
                <hr>


                <div class="dropdown py-4 mt-auto ms-auto ms-sm-0">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <span class="d-none d-sm-inline mx-1">Username: <%=username%></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="index.jsp">Sign out</a></li>
                        <li><a class="dropdown-item" href="changePassword.jsp">ChangePassword</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
