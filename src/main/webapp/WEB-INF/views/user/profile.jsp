<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Profile">

    <h1>Профиль пользователя</h1>

    <c:choose>
        <c:when test="${sessionScope.user != null}">
            <p><strong>Имя пользователя:</strong> ${sessionScope.user.username}</p>
            <p><strong>Имя:</strong> ${sessionScope.user.name}</p>
            <p><strong>Фамилия:</strong> ${sessionScope.user.surname}</p>

            <a href="${pageContext.request.contextPath}/solo-section">Мои задачи</a>
            <br>
            <a href="${pageContext.request.contextPath}/team">Мои команды</a>
            <br>

            <!-- Кнопка выхода -->
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Выйти</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Вы не авторизованы. <a href="${pageContext.request.contextPath}/login">Нажмите, чтобы войти</a></p>
        </c:otherwise>
    </c:choose>

</t:mainLayout>
