<%--
  Created by IntelliJ IDEA.
  User: dakon
  Date: 04.02.2018
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Table of users </title>
    <meta charset="utf-8">
    <meta rel="viewport" content="width=device=width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <div class="container">

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            ${pageContext.request.userPrincipal.name}
            <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>

        </c:if>
        <sec:authorize access="hasRole('ROLE_USER')">
        <td><a href="/carsView" class="btn btn-success" style="width:50%">Show
            my cars</a></td>
    </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')or hasRole('ROLE_MANAGER')">
            <td><a href="/control_of_cars" class="btn btn-success" style="width:50%">Show
                my cars</a></td>

    </div>

</head>
<body>
<div class="container">
    <p></p>
    <p>List of Library Users</p>
    <table border="1" class="table-condensed">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>login</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>Password</th>
                <th></th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.user_id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.login}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td> ${user.password} </td>
                <td><a href="/delete/${user.user_id}" class="btn btn-success">Delete</a></td>
                </sec:authorize>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h3>               </h3>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a  type="button" class="btn btn-success" data-toggle="modal" data-target="#popUpWindow">  Add User </a>
    <div class="modal fade" id="popUpWindow">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- body-->
                <div class="modal-body">
                    <form role="form" name="car" action="/addUser/" method="post">
                        <div>
                            <p>Name</p>
                            <input title="name" type="text" name="name" class="form-control" placeholder="Enter Name">
                        </div>
                        <div>
                            <p>Surname</p>
                            <input title="surname" type="text" name="surname" class="form-control" placeholder="Enter Surname">

                        </div>
                        <div>
                            <p>login</p>
                            <input title="login" type="text" name="login" class="form-control" placeholder="Enter Login">

                        </div>
                        <div>
                            <p>password</p>
                            <input title="password" type="text" name="password" class="form-control" placeholder="Enter Password">

                        </div>
                        <div class="modal-footer">
                            <!--<a th:href="@{/addUser}"> </a>-->
                            <button th:href="@{/addUser}" class="btn btn-primary btn-block" type="submit">Ok</button>

                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
    <a  type="button" class="btn btn-success" data-toggle="modal" data-target="#popUpWindow">  Add Manager </a>
    <div class="modal fade" id="popUpWindow">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- body-->
                <div class="modal-body">
                    <form role="form" name="car" action="/addUser/" method="post">
                        <div>
                            <p>Name</p>
                            <input title="name" type="text" name="name" class="form-control" placeholder="Enter Name">
                        </div>
                        <div>
                            <p>Surname</p>
                            <input title="surname" type="text" name="surname" class="form-control" placeholder="Enter Surname">

                        </div>
                        <div>
                            <p>login</p>
                            <input title="login" type="text" name="login" class="form-control" placeholder="Enter Login">

                        </div>
                        <div>
                            <p>password</p>
                            <input title="password" type="text" name="password" class="form-control" placeholder="Enter Password">

                        </div>
                        <div class="modal-footer">
                            <!--<a th:href="@{/addUser}"> </a>-->
                            <button th:href="@{/addManager}" class="btn btn-primary btn-block" type="submit">Ok</button>

                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
            </sec:authorize>
</sec:authorize>
</div>
</body>
</html>
