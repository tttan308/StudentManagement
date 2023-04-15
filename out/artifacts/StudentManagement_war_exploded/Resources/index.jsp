<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Đăng nhập</title>
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

</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">
  <form class="container py-5 h-100" action = "account" method="POST">
    <input type = "hidden" name = "action" value = "sign-in"/>
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
            <h3 class="mb-5">Đăng nhập</h3>
            <div class="form-outline mb-4">
              <input type="text" id="username" class="form-control form-control-lg" placeholder="Tài khoản" name = "username"/>
            </div>
            <div class="form-outline mb-4">
              <input type="password" id="password" class="form-control form-control-lg" placeholder="Mật khẩu" name = "password"/>
            </div>
            <div class="form-check d-flex justify-content-start mb-4">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3" />
              <label class="form-check-label mx-2" for="form1Example3">   Nhớ tài khoản </label>
            </div>

            <%
              if (request.getAttribute("error") != null) {
                String error = (String) request.getAttribute("error");
                if (error.equals("Tài khoản hoặc mật khẩu không chính xác!")) {%>
                  <div class="alert alert-danger" role="alert">
                    <%= error %>
                  </div>
                <%}
                }
            %>

            <button class="btn btn-primary btn-lg btn-block" type="submit">Đăng nhập</button>
            <hr class="my-4">
            <div>
              <p class="mb-0">Chưa có tài khoản? <a href="signup.jsp" class="text-black-50 fw-bold">Đăng kí</a>
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