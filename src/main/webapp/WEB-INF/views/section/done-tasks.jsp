<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="List detail">

    <h1>Выполненные задачи:</h1>

    <ul>
        <c:forEach var="doneTask" items="${doneTasks}">
            <a>${doneTask.getName()}. Дата выполнения: ${doneTask.getDateOfAdd()}</a>

            <form action="<c:url value='/done-tasks'/>" method="POST">
                <input type="hidden" name="task_id" value="${doneTask.getId()}"/>
                <input type="submit" value="Задача не выполнена!"/>
            </form>
            <br/>
        </c:forEach>
    </ul>

    <br/>

    <a href="${pageContext.request.contextPath}/solo-section">Назад ко всем спискам</a>

</t:mainLayout>
