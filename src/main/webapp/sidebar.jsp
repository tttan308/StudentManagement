<%@ page import="model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Account account = (Account) session.getAttribute("account");
    String username = account.getUsername();
%>
<div class="col-2 bg-dark text-light vh-100">
    <form action="student-list" method="get">
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
                    <a href="student?action=show-list" class="nav-link text-light">
                        <h5> Student List </h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="courseList.jsp" class="nav-link text-light">
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
            <ul class="dropdown-menu dropdown-menu-dark text-small shadow dropdown-menu-end" aria-labelledby="dropdownUser1">
                <li>
                    <a class="dropdown-item" href="account?action=sign-out">Sign out</a>
                    <input type="hidden" name="action" value="sign-out">
                </li>
                <li>
                    <a class="dropdown-item" href="changePassword.jsp">Change Password</a>
                    <input type="hidden" name="action" value="change-password">
                </li>
            </ul>
        </div>
    </div>
    </form>
</div>
