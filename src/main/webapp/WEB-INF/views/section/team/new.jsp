<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

<form action="<c:url value="/team-section/new"/>" method="POST">
    <input type="hidden" name="team_id" value="${team_id}"/>
    <br>
    <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
    <br/>
    <input name="type" type="text" placeholder="Type (TASK/NOTE)" <c:out value="${type}"/> >

    <input type="submit" value="Создать!"/>

</form>

</t:mainLayout>
