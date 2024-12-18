<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Profile">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">

    <div class="login-container">



        <form method="POST" action="<c:url value='/login'/>" class="login-form">
            <h2>Войти</h2>
            <label for="username">Логин:</label>
            <input type="text" name="username" id="username" required>

            <label for="password">Пароль:</label>
            <input type="password" name="password" id="password" required>

            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <input type="submit" value="Войти" class="login-button"/>

            <div class="not-registered">Вы еще не зарегистрированы?</div>
            <a href="${pageContext.request.contextPath}/registration" class="register-link">Зарегистрироваться</a>
        </form>
    </div>

</t:mainLayout>
