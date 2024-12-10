<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

    <form action="<c:url value="/task/new"/>" method="POST">
        <input type="hidden" name="section_id" value="${section_id}"/>
        <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
        <input name="description" type="text" placeholder="Description" <c:out value="${description}"/> >

        <input type="submit" value="Создать!"/>

    </form>

</t:mainLayout>

