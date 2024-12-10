<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Main page">

<div class="text-info">Lists:</div>

<ul>
    <c:forEach var="section" items="${sections}">
        <li>
            <a href="${pageContext.request.contextPath}/solo-section/${section.getId()}">${section.getName()}</a>
        </li>
    </c:forEach>
</ul>

<br/>
<hr/>

<a href="${pageContext.request.contextPath}/solo-section/new">Create new list</a>

<br>

</t:mainLayout>



