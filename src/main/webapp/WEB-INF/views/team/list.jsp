<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Teams">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teams.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture" class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content">
            <h2>Команды</h2>
            <div class="section-list-container">
                <ul class="section-list">
                    <c:forEach var="team" items="${teams}">
                        <li class="section-item">
                            <a href="${pageContext.request.contextPath}/team/${team.getId()}">${team.getName()}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <a href="#" class="action-link" id="new-team-link">Создать новую команду</a>
        </div>

        <div class="form-container" id="form-container" style="display: none;">
            <!-- Здесь будет загружена форма создания новой команды -->
        </div>
    </div>

    <script>
        document.getElementById("new-team-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/team/new');
        });

        function toggleForm(url) {
            var formContainer = document.getElementById("form-container");
            if (formContainer.style.display === "none" || formContainer.innerHTML === "") {
                fetch(url)
                    .then(response => response.text())
                    .then(html => {
                        formContainer.innerHTML = html;
                        formContainer.style.display = "block";
                    });
            } else {
                formContainer.style.display = "none";
                formContainer.innerHTML = "";
            }
        }
    </script>
</t:mainLayout>
