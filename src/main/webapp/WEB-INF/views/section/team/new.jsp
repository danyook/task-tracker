<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

<form action="<c:url value="/team-section/new"/>" method="POST">
    <input type="hidden" name="team_id" value="${team_id}"/>
    <br>
    <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
    <br/>

    <label for="type">Type:</label>
    <select id="type" name="type">
        <option value="NOTE">Заметка</option>
        <option value="TASK">Задача</option>
    </select>

    <input type="submit" value="Создать!"/>

</form>

</t:mainLayout>

