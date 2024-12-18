<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">

<div class="edit-profile-content">
    <h2>Сменить пароль</h2>
    <form method="POST" action="<c:url value='/password'/>" class="edit-profile-form">
        <label for="oldPass">Старый пароль:</label>
        <input type="password" name="oldPass" id="oldPass" placeholder="Старый пароль"/>

        <label for="newPassOne">Новый пароль:</label>
        <input type="password" name="newPassOne" id="newPassOne" placeholder="Новый пароль"/>

        <label for="newPassTwo">Повторите новый пароль:</label>
        <input type="password" name="newPassTwo" id="newPassTwo" placeholder="Повторите новый пароль"/>

        <input type="submit" value="Сменить пароль!"/>
    </form>
</div>
