<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Done Tasks">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tasks.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture" class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content">
            <h2>Выполненные задачи</h2>
            <div class="section-list-container">
                <ul class="section-list">
                    <c:forEach var="doneTask" items="${doneTasks}">
                        <li class="section-item">
                            <span class="task-info">${doneTask.getName()} <br> <span class="task-date">Дата выполнения: ${doneTask.getDateOfAdd()}</span></span>
                            <form action="<c:url value='/done-tasks'/>" method="POST" class="task-form">
                                <input type="hidden" name="task_id" value="${doneTask.getId()}"/>
                                <input type="submit" value="Отмена" class="task-button"/>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <a href="${pageContext.request.contextPath}/solo-section" class="action-link">Назад ко всем спискам</a>
        </div>
    </div>

</t:mainLayout>
