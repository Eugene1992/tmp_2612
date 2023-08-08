<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 03.08.2023
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<h1>Registration Page</h1>
<div class="form-group row">
    <label for="login" class="col-sm-2 col-form-label">Login</label>
    <div class="col-sm-10">
        <input type="text" class="form-control" name="login" id="login" value="${login}">

    </div>
</div>

<p class="text-center"> ${errorLogin} </p>

<div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
        <input type="password" class="form-control" name="password" id="password"  value="${password}">
    </div>
</div>
<p class="text-center"> ${errorPassword} </p>
</body>
</html>
