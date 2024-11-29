<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Team detail">

    <h1>Команда: ${team.getName()}</h1>

    <h2>Участники:</h2>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>${user.getUsername()}</li>
            <br/>
        </c:forEach>
    </ul>


    <a href="${pageContext.request.contextPath}/team/edit?team_id=${team.getId()}">Обновить команду</a>

    <br>

    <a href="${pageContext.request.contextPath}/team/add-user?team_id=${team.id}">Добавить участника</a>

    <form action="<c:url value='/team/${team.getId()}'/>" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="submit" value="Delete team!"/>
    </form>

    <a href="${pageContext.request.contextPath}/team">Назад к списку команд</a>


</t:mainLayout>