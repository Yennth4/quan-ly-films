<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý video</title>
</head>
<body>
<h6 style="color: #ff0000 ; margin-left: 100px">${error}</h6>
<h3 class="text-center mt-3 mb-3">Quản lý video</h3>
<table class="table table-striped table-hover table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Active</th>
        <th>Poster</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="st" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${st.title}</td>
            <td>${st.description}</td>
            <td>${st.active? "Yes":"No"}</td>

            <td>
                <iframe src="https://www.youtube.com/embed/${st.poster}" width="212px"></iframe>
            </td>

            <td>
                <a class="btn btn-warning btn-sm" href="update-video?id=${st.id}">Update</a>

                <a class="btn btn-outline-danger btn-sm" href="delete-video?id=${st.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa video + ${st.id}')">Delete</a>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
