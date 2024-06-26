
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h6 style="color: red ; margin-left: 100px"  >${error}</h6>
<h2 class="text-center mt-2 mb-2">List user</h2>
<table class="table table-striped table-hover table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Password</th>
        <th>Email</th>
        <th>Fullname</th>
        <th>Admin</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="st">
        <tr>
            <td>${st.id}</td>
            <td>${st.password}</td>
            <td>${st.email}</td>
            <td>${st.fullname}</td>
            <td>${st.admin? "Admin":"User"}</td>
            <td>
                <a class="btn btn-primary btn-sm" href="update-user?id=${st.id}">Update</a> |
                </a>

                <a class="btn btn-danger btn-sm" href="delete-user?id=${st.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?')">Delete</a>


            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
