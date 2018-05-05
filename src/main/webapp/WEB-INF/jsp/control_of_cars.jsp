<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: babic
  Date: 26.02.2018
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Cars</title>
    <meta charset="utf-8">
    <meta rel="viewport" content="width=device=width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <p>List of Library Cars</p>
    <table border="1" class="table-condensed">
        <thead>
        <tr>
            <th>id</th>
            <th>brand</th>
            <th>image</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.car_id}</td>
                <td>${car.brand}</td>
                <td><img src = "${car.path}"></td>
                <td><a href="/deleteCar/${car.car_id}" class="btn btn-success">Delete</a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button  type="button" class="btn btn-success" data-toggle="modal" data-target="#popUpWindow">Add Car</button>
        <div class="modal fade" id="popUpWindow">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- header-->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- body-->
                    <div class="modal-body">
                        <form role="form" name="car" action="/addCar" method="post">
                            <div>
                                <p>Brand</p>
                                <input title="brand" type="text" name="brand" class="form-control" placeholder="Enter brand here">
                            </div>
                        </form>
                        <form method="POST" action="/addCar" enctype="multipart/form-data">
                            File to upload: <input type="file" name="file"><br />
                            <button th:href="@{/addCar}" class="btn btn-success btn-block" type="submit">Ok</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>
