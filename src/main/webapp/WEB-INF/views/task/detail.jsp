<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-content">
    <h1>Задача: ${task.getName()}</h1>
    <h2>Описание:</h2>
    <p>${task.getDescription()}</p>

    <a href="${pageContext.request.contextPath}/task/edit?task_id=${task.getId()}&section_id=${section_id}" class="action-link">Редактировать задачу</a>
    <form action="<c:url value='/task/${task.getId()}'/>" method="POST" class="delete-form">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="section_id" value="${section_id}"/>
        <input type="submit" value="Удалить задачу!" class="delete-button"/>
    </form>
</div>
