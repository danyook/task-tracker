<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Main page">

<div class="text-info">Lists:</div>

<ul>
    <c:forEach var="section" items="${sections}">
        <li>
            <a href="${pageContext.request.contextPath}/team-section/${section.getId()}?team_id=${team_id}">${section.getName()}</a>
        </li>
    </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/team-section/new?team_id=${team_id}">Создать новый список</a>

<br>

</t:mainLayout>



