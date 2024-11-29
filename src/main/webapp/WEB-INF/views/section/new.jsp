<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

<form action="<c:url value="/section/new"/>" method="POST">
    <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
    <br/>
    <input name="type" type="text" placeholder="Type (TASK/NOTE)" <c:out value="${type}"/> >

    <input type="submit" value="Create!"/>

</form>

</t:mainLayout>

