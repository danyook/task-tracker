<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Profile">

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <form method="POST" action="<c:url value='/login'/>">

        Login:
        <input type="text" name="username">

        <br>

        Password:
        <input type="password" name="password">

        <br>

        <input type="submit" value="Login!"/>

        <a href="${pageContext.request.contextPath}/registration">Registration</a>
    </form>

</t:mainLayout>
