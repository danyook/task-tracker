<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

    <form action="<c:url value='/profile/edit'/>" method="POST">
        <input type="hidden" name="team_id" value="${team_id}"/>

        <input name="username" type="text" placeholder="username" <c:out value="${username}"/> >
        <br/>
        <input name="name" type="text" placeholder="name" <c:out value="${name}"/> >
        <br/>
        <input name="surname" type="text" placeholder="surname" <c:out value="${surname}"/> >
        <br/>

        <input type="submit" value="Обновить профиль!"/>
    </form>

</t:mainLayout>