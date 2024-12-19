<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-content">
    <form action="<c:url value='/task/edit'/>" method="POST">
        <input type="hidden" name="section_id" value="${section_id}"/>
        <input type="hidden" name="task_id" value="${task_id}"/>
        <label for="name">Name:</label>
        <input id="name" name="name" type="text" placeholder="Name" value="${name}" />
        <label for="description">Description:</label>
        <input id="description" name="description" type="text" placeholder="Description" value="${description}" />
        <input type="submit" value="Обновить задачу!" class="form-button"/>
    </form>
</div>