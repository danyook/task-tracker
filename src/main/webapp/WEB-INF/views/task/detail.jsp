<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Task detail">

    <h1>Задача: ${task.getName()}</h1>

    <h2>Описание:</h2>
    <ul>
        <li>${task.getDescription()}</li>
    </ul>


    <a href="${pageContext.request.contextPath}/task//edit?task_id=${task.getId()}&section_id=${section.getId()}">Обновить задачу</a>

    <form action="<c:url value='/task/${task.getId()}'/>" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="section_id" value="${section.getId()}"/>
        <input type="submit" value="Delete task!"/>
    </form>

    <a href="${pageContext.request.contextPath}/solo-section/${section.getId()}">Назад к списку задач</a>


</t:mainLayout>