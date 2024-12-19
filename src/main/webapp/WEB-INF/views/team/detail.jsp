<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Team detail">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teams.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture" class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content team-detail">
            <h2>Команда: ${team.name}</h2>
            <h3>Участники:</h3>
            <div class="section-list-container">
                <ul class="section-list">
                    <c:forEach var="user" items="${users}">
                        <li class="section-item">${user.username} (${user.name} ${user.surname})</li>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <a href="${pageContext.request.contextPath}/team-section?team_id=${team.id}" class="action-link">Наши задачи</a>
            <c:if test="${currentUser.id == team.owner.id}">
                <a href="#" class="action-link" id="edit-team-link">Редактировать команду</a>
                <a href="#" class="action-link" id="add-user-link">Добавить участника</a>
                <form action="<c:url value='/team/${team.id}'/>" method="POST" class="delete-form">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="submit" value="Удалить команду!" class="delete-button"/>
                </form>
            </c:if>
            <a href="${pageContext.request.contextPath}/team" class="action-link">Назад к списку команд</a>
        </div>

        <div class="form-container" id="form-container" style="display: none;">
            <!-- Здесь будет загружена форма для обновления команды или добавления участника -->
        </div>
    </div>

    <script>
        document.getElementById("edit-team-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/team/edit?team_id=${team.id}', 'form-container');
        });

        document.getElementById("add-user-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/team/add-user?team_id=${team.id}', 'form-container');
        });

        function toggleForm(url, containerId) {
            var container = document.getElementById(containerId);
            if (container.style.display === "none" || container.innerHTML === "") {
                fetch(url)
                    .then(response => response.text())
                    .then(html => {
                        container.innerHTML = html;
                        container.style.display = "block";
                    });
            } else {
                container.style.display = "none";
                container.innerHTML = "";
            }
        }
    </script>
</t:mainLayout>
