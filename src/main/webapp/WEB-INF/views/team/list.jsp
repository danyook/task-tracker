<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Teams">

    <div class="text-info">Teams:</div>

    <ul>
        <c:forEach var="team" items="${teams}">
            <li>
                <a href="${pageContext.request.contextPath}/team/${team.getId()}">${team.getName()}</a>
            </li>
        </c:forEach>
    </ul>

    <br/>
    <hr/>

    <a href="${pageContext.request.contextPath}/team/new">Create new team</a>


</t:mainLayout>

