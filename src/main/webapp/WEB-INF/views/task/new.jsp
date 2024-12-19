<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/task/new'/>" method="POST" class="form-content">
    <input type="hidden" name="section_id" value="${section_id}"/>
    <label for="name">Название:</label>
    <input id="name" name="name" type="text" placeholder="Name"/>
    <label for="description">Описание:</label>
    <textarea id="description" name="description" placeholder="Description" rows="10" cols="50">${description}</textarea>
    <input type="submit" value="Создать!" class="form-button"/>
</form>
