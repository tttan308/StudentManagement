<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Change Password</title>
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
    <style>
        .red {
            color: red;
        }
    </style>

    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    %>
    <link href="<%=url%>/css/signup.css" rel="stylesheet">
</head>
<body>
<section class="vh-100">
    <form class="mask d-flex align-items-center h-100 gradient-custom-3" action = "account?action=change-password" method="post">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-5">Thay đổi mật khẩu</h2>
                            <div class="form-outline mb-4">
                                <input type="password" id="old-password" class="form-control form-control-lg" placeholder="Mật khẩu cũ" name = "old-password"/>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password" id="new-password" class="form-control form-control-lg" placeholder="Mật khẩu mới" name = "new-password"/>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password" id="repeat-new-password" class="form-control form-control-lg" placeholder="Nhập lại mật khẩu mới" name ="repeat-new-password"/>
                            </div>

                            <% if(request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger" role="alert">
                                <%=request.getAttribute("error")%>
                            </div>
                            <% } %>

                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Thay đổi</button>
                            </div>
                            <p class="text-center text-muted mt-5 mb-0">Về trang chủ -
                                <a href="homepage.jsp" class="fw-bold text-body"><u>Trang chủ</u></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
</body>
</html>
