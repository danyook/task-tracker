<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/team/edit'/>" method="POST" class="form-content">
    <input type="hidden" name="team_id" value="${team.getId()}"/>
    <label for="name">Название:</label>
    <input id="name" name="name" type="text" placeholder="Name" value="${team.getName()}"/>
    <br/>
    <label for="owner">Новый владелец:</label>
    <select id="owner" name="owner">
        <c:forEach var="member" items="${teamMembers}">
            <option value="${member.id}" ${member.id == team.owner.id ? 'selected' : ''}>${member.username}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Обновить команду" class="form-button"/>
</form>
