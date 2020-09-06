<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="UTF-8" />
    <title>Login Page</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/login.css" />
    <link rel="stylesheet" href="assets/plugins/magic/magic.css" />
</head>
<body>
    <div class="container">
        <div class="text-center">
            <img src="assets/img/logo1.png" id="logoimg" alt=" Logo" />
        </div>
        <div class="tab-content">
            <div id="login" class="tab-pane active">
                <spring:form action="login.htm" class="form-signin" commandName="lg" name="myFormLogin" method="post">
                    <spring:input path="username" type="text" id="username" name="username" placeholder="" class="form-control"/>
                    <spring:input path="password" type="password" id="password" name="password" placeholder="" class="form-control"/>
                    <span style="color: red;white-space: nowrap;">${message}</span>
                    <spring:errors path="username" cssStyle="color:red;white-space: nowrap;"/>
                    <spring:errors path="password" cssStyle="color:red;white-space: nowrap;"/>
                    <button class="btn text-muted text-center btn-danger" style="margin-top: 5px; margin-bottom: 5px;" type="submit">Đăng nhập</button>
                </spring:form>
            </div>
        </div>
    </div>
    <script src="assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.js"></script>
    <script src="assets/js/login.js"></script>
    <script type="text/javascript">
        document.getElementById('username').focus();
    </script>
</body>
</html>