<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Team detail">

    <h1>Команда: ${team.name}</h1>

    <h2>Участники:</h2>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>${user.username}</li>
            <br/>
        </c:forEach>
    </ul>

    <a href="${pageContext.request.contextPath}/team-section?team_id=${team.id}">Наши задачи</a>
    <br/>

    <c:if test="${currentUser.id == team.owner.id}">
        <a href="${pageContext.request.contextPath}/team/edit?team_id=${team.id}">Обновить команду</a>
        <br/>
        <a href="${pageContext.request.contextPath}/team/add-user?team_id=${team.id}">Добавить участника</a>
        <br/>
        <form action="<c:url value='/team/${team.id}'/>" method="POST">
            <input type="hidden" name="_method" value="DELETE"/>
            <input type="submit" value="Удалить команду!"/>
        </form>
        <br/>
    </c:if>

    <a href="${pageContext.request.contextPath}/team">Назад к списку команд</a>

</t:mainLayout>
