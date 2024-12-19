<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Edit Task">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tasks.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture"
                 class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content">
            <h1>Редактировать задачу</h1>
            <form action="<c:url value='/task/edit'/>" method="POST" class="form-content">
                <input type="hidden" name="section_id" value="${section_id}"/>
                <input type="hidden" name="task_id" value="${task_id}"/>
                <label for="name">Название:</label>
                <input id="name" name="name" type="text" placeholder="Name" value="${task.name}"/>
                <label for="description">Описание:</label>
                <textarea id="description" name="description" placeholder="Description" rows="10"
                          cols="50">${task.description} </textarea>
                <input type="submit" value="Обновить задачу!" class="form-button"/>
            </form>
            <c:choose>
                <c:when test="${section.role == 'SOLO'}">
                    <a href="${pageContext.request.contextPath}/solo-section/${section_id}" class="action-link">Назад к списку задач</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/team-section/${section_id}" class="action-link">Назад к списку задач</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</t:mainLayout>
