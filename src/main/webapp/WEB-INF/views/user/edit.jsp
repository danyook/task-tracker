<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">

<div class="edit-profile-content">
    <h2>Редактировать профиль</h2>
    <form action="<c:url value='/profile/edit'/>" method="POST" class="edit-profile-form">
        <input type="hidden" name="team_id" value="${team_id}"/>

        <label for="username">Логин:</label>
        <input name="username" id="username" type="text" placeholder="Логин" value="${sessionScope.user.username}"/>

        <label for="name">Имя:</label>
        <input name="name" id="name" type="text" placeholder="Имя" value="${sessionScope.user.name}"/>

        <label for="surname">Фамилия:</label>
        <input name="surname" id="surname" type="text" placeholder="Фамилия" value="${sessionScope.user.surname}"/>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <input type="submit" value="Обновить профиль!"/>
    </form>
</div>
