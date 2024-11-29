<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Registration">

    <h1>Регистрация</h1>
    <!-- Показываем сообщение об ошибке, если есть -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    <form action="<c:url value='/registration'/>" method="post">

        <label for="name">Имя:</label><br>
        <input type="text" name="name" id="name" required /><br><br>

        <label for="surname">Фамилия:</label><br>
        <input type="text" name="surname" id="surname" required /><br><br>

        <label for="username">Логин:</label><br>
        <input type="text" name="username" id="username" required /><br><br>

        <label for="password">Пароль:</label><br>
        <input type="password" name="password" id="password" required /><br><br>
        <button type="submit">Зарегистрироваться</button>
    </form>

</t:mainLayout>


