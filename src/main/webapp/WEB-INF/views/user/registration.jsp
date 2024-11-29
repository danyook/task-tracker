<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Registration">

    <form method="POST" action="/registration">

        Name:
        <input type="text" name="name">

        <br>

        Last name:
        <input type="text" name="lastname">

        <br>

        Login:
        <input type="text" name="login">

        <br>

        Password:
        <input type="password" name="password">

        <br>

        <input type="submit" value="Regist"/>

    </form>

</t:mainLayout>


