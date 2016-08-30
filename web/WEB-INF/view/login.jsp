<%@ page contentType="text/html; charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2016-08-05
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/theme1/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/theme1/css/common.css" rel="stylesheet">
</head>

<body link="#f0ffff" alink="#f0ffff" vlink="#f0ffff">

<div class="container">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button name="login" class="btn btn-lg btn-primary btn-block" type="submit">Proceed</button>
            <span style =color:#f0ffff>
                <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
            </span>
        </div>

    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/theme1/js/bootstrap.min.js"></script>
</body>
</html>
