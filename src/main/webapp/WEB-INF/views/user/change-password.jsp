<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Change password">

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <form method="POST" action="<c:url value='/password'/>">

        Old password:
        <input type="text" name="oldPass">

        <br>

        New pass:
        <input type="password" name="newPassOne">

        <br>

        New pass again:
        <input type="password" name="newPassTwo">

        <br>

        <input type="submit" value="Сменить пароль!"/>
        
    </form>

</t:mainLayout>