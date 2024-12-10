<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

    <form action="<c:url value="/team/new"/>" method="POST">
        <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >

        <input type="submit" value="Создать!"/>

    </form>

</t:mainLayout>

