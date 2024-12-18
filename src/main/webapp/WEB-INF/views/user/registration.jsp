<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Registration">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registration.css">

    <div class="login-container">



        <form action="<c:url value='/registration'/>" method="post" class="login-form">
            <h2>Регистрация</h2>
            <label for="name">Имя:</label>
            <input type="text" name="name" id="name" required/>

            <label for="surname">Фамилия:</label>
            <input type="text" name="surname" id="surname" required/>

            <label for="username">Логин:</label>
            <input type="text" name="username" id="username" required/>

            <label for="password">Пароль:</label>
            <input type="password" name="password" id="password" required/>

            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <button type="submit" class="login-button">Зарегистрироваться!</button>

            <div class="not-registered">Уже есть аккаунт?</div>
            <a href="${pageContext.request.contextPath}/login" class="register-link">Войти!</a>
        </form>
    </div>

</t:mainLayout>


