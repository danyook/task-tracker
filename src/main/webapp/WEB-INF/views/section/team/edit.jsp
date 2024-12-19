<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/team-section/edit'/>" method="POST" class="form-content">
    <input type="hidden" name="section_id" value="${section_id}"/>
    <label for="name">Название:</label>
    <input id="name" name="name" type="text" placeholder="Name" value="${section.name}" />
    <label for="type">Тип:</label>
    <select id="type" name="type">
        <option value="NOTE">Заметка</option>
        <option value="TASK">Задача</option>
    </select>
    <input type="submit" value="Изменить список!" class="form-button"/>
</form>

