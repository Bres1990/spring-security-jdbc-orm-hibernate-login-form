<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<form:form method="POST" action="/" commandName="user">
    <table>
        <tr>
            <td>Username :</td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:input path="password"/></td>
        </tr>

        <tr>
            <td colspan="4">
                <input type="submit" value="Sign In" class="right-arrow-button"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>