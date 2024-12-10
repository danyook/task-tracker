<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New">

    <form action="<c:url value='/team/edit'/>" method="POST">
        <input type="hidden" name="team_id" value="${team_id}"/>

        <input name="name" type="text" placeholder="Name" <c:out value="${name}"/> >
        <br/>
        <input name="owner" type="text" placeholder="new owner" <c:out value="${owner}"/> > <%-- Сделать выпадающий список для выбора нового владельца --%>
        <br/>
        <input type="submit" value="Обновить команду!"/>
    </form>

</t:mainLayout>

