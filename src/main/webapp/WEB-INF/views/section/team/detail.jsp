<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="List detail">

    <h1>Секция: ${section.getName()}</h1>

    <h2>Задачи в этой секции</h2>
    <ul>
        <c:forEach var="task" items="${tasks}">
            <a href="${pageContext.request.contextPath}/task/${task.getId()}?section_id=${section.getId()}">${task.getName()}</a>
            <br/>
        </c:forEach>
    </ul>

    <a href="${pageContext.request.contextPath}/task/new?section_id=${section.getId()}">Создать новую задачу</a>
    <br/>

    <a href="${pageContext.request.contextPath}/team-section/edit?section_id=${section.getId()}">Обновить список</a>

    <form action="<c:url value='/team-section/${section.getId()}?team_id=${team_id}'/>" method="POST"><%--todo добавить team_id--%>
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="submit" value="Удалить список!"/>
    </form>

    <a href="${pageContext.request.contextPath}/team-section?team_id=${team_id}">Назад ко всем спискам</a>


</t:mainLayout>
