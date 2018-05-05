<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">

    <meta rel="viewport" content="width=device=width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>Car Selection</title>
</head>

<body>
<div class="container">

    <h4>Select Your Cars</h4>
</div>
<table>
    <tr>
        <td width="50%">
            <table border="1" width="100%" cellspacing="0" cellpadding="0" align="center">
                <thead>
                <tr>
                    <th>id</th>
                    <th>brand</th>
                    <th>image</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="carUser" items="${userCars}">
                    <tr>
                        <td>${carUser.car_id}</td>
                        <td>${carUser.brand}</td>
                        <td><img src = "${carUser.path}"></td>
                        <td><a href="/deleteUserCars/${carUser.car_id}" class="btn btn-success">Delete</a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
        <td width="50%">
            <table border="1" width="100%" cellspacing="0" cellpadding="0" align="center">
                <thead>
                <tr>
                    <th>id</th>
                    <th>brand</th>
                    <th>image</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="carNotUser" items="${cars}">
                    <tr>
                        <td>${carNotUser.car_id}</td>
                        <td>${carNotUser.brand}</td>
                        <td><img src = "${carNotUser.path}"></td>
                        <td><a href="/addNewCar/${carNotUser.car_id}" class="btn btn-success">Add</a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</table>
</body>
</html>