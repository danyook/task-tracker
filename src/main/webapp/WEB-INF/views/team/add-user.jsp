<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Add member">

    <h2>Добавить участника в команду</h2>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <form action="<c:url value='/team/add-user'/>" method="post">
        <input type="hidden" name="team_id" value="${teamId}">
        <label for="username">Введите имя пользователя:</label>
        <input type="text" id="username" name="username" required>
        <button type="submit">Добавить</button>
    </form>

    <a href="/team/${teamId}">Назад к команде</a>


</t:mainLayout>