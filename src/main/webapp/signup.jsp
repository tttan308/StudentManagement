<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng kí</title>
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
<section class="vh-100 bg-image"
         style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
    <form class="mask d-flex align-items-center h-100 gradient-custom-3" action = "sign-up" method="post">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-5">Create an account</h2>

                                <div class="form-outline mb-4">
                                    <input type="text" id="username" class="form-control form-control-lg" placeholder="User name" name = "username"/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" id="password" class="form-control form-control-lg" placeholder="Password" name = "password"/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" id="repeat-password" class="form-control form-control-lg" placeholder="Repeat your password" name ="repeat-password"/>
                                </div>

                                <% if(request.getAttribute("error") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%=request.getAttribute("error")%>
                                    </div>
                                <% } %>

                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register
                                    </button>
                                </div>

                                <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="index.jsp"
                                                                                                        class="fw-bold text-body"><u>Login here</u></a></p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
</body>
</html>