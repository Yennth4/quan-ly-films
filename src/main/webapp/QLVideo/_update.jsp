<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update video</title>
</head>
<body>
<h2 class="text-center mt-3 mb-3">Update video</h2>
<form method="post" action="" class="container">
    <h3 style="color: red">${message}</h3>
    <p>ID: <input type="text" name="id" value="${video.id}" class="form-control" disabled></p>
    <p>Title: <input type="text" name="title" value="${video.title}" class="form-control" required></p>
    <p>
        Description:<textarea id="w3review" name="description" rows="4" cols="50" required class="form-control">
                        ${video.description}
                    </textarea>
    </p>

    <p>Active: <div class="form-check form-switch"><input type="checkbox" name="active" ${video.active? "checked":""} class="form-check-input"></div></p>
    <p>Poster: <input type="text" name="poster" value="${video.poster}" required class="form-control"></p>

    <button class="btn btn-warning mt-2"> Update</button>
</form>
</body>
</html>
