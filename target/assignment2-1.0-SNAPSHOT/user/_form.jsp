<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h2 class="text-center mt-3 mb-3">Add user</h2>
<form method="post" action="user" class="container">
    <h3 style="color: red">${message}</h3>
    <p>User name: <input type="text" name="id" required class="form-control"></p>
    <p>Password: <input type="password" name="password" required class="form-control"></p>
    <p>Email: <input type="email" name="email" required class="form-control"></p>
    <p>Full name: <input type="text" name="fullname" required class="form-control"></p>
    Admin: <div class="form-check form-switch"><input type="checkbox" name="admin" class="form-check-input"></div>
    <button class="btn btn-success mt-2">Add</button>
</form>
</body>
</html>
