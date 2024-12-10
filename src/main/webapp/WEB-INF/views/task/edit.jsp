<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

    <form action="<c:url value='/task/edit'/>" method="POST">
        <input type="hidden" name="section_id" value="${section_id}"/>
        <input type="hidden" name="task_id" value="${task_id}"/>

        <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
        <br/>
        <input name="description" type="text" placeholder="Description" <c:out value="${description}"/> >
        <br/>
        <input type="submit" value="Обновить задачу!"/>
    </form>

</t:mainLayout>

