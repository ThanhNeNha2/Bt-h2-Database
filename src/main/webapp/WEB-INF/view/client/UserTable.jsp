<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
                                                                  uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
        uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
    ></script>
</head>
<body>
<div class="container mt-3">
    <h1>Võ Chí Thanh Lớp ST21A1B</h1>

    <br />
    <h3>Nhóm thích cầm cơ</h3>
    <br />

    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">First</th>
            <th scope="col">Email</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>Võ Chí Thanh</td>
            <td>ta.coaching.v2@gmail</td>
            <td>
                <button class="btn btn-warning">Update</button>
                <button class="btn btn-danger">delete</button>
            </td>
        </tr>

        <tr>
            <th scope="row">2</th>
            <td>Lê Quốc Tuấn</td>
            <td>ta.coaching.v2@gmail</td>
            <td>
                <button class="btn btn-warning">Update</button>
                <button class="btn btn-danger">delete</button>
            </td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>Trương Văn Quyến</td>
            <td>ta.coaching.v2@gmail</td>
            <td>
                <button class="btn btn-warning">Update</button>
                <button class="btn btn-danger">delete</button>
            </td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td>Phạm Anh Tuấn</td>
            <td>ta.coaching.v2@gmail</td>
            <td>
                <button class="btn btn-warning">Update</button>
                <button class="btn btn-danger">delete</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>