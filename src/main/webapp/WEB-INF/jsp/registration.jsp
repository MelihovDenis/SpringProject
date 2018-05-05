<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: babic
  Date: 18.02.2018
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table of users </title>
    <meta charset="utf-8">
    <meta rel="viewport" content="width=device=width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form:form method="POST" action="/registration" modelAttribute="userForm">
        <h2 class="form-signin-heading">Create your account</h2>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text"  path="name"  class="form-control" placeholder="Enter name here"
                            autofocus="true" required="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="surname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="surname" class="form-control"
                            placeholder="Enter surname here" required="true"></form:input>
                <form:errors path="surname"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="login">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="login" class="form-control"
                            placeholder="Enter login here"></form:input>
                <form:errors path="login"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control"
                            placeholder="Enter password here"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        <div class="modal-footer">
            <button th:href="@{/registration}" class="btn btn-primary btn-block" type="submit">Submit!</button>

        </div>


    </form:form>

</div>

</body>
</html>
