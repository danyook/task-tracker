<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Main page">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tasks.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="${urlPhoto}" alt="Profile Picture" class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content">
            <h2>Командные списки</h2>
            <div class="section-list-container">
                <ul class="section-list">
                    <c:forEach var="section" items="${sections}">
                        <li class="section-item">
                            <a href="${pageContext.request.contextPath}/team-section/${section.getId()}?team_id=${team_id}">${section.getName()}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <a href="#" class="action-link" id="new-team-section-link">Создать новый список</a>
        </div>

        <div class="form-container" id="form-container" style="display: none;">
            <!-- Здесь будет загружена форма создания новой командной секции -->
        </div>
    </div>

    <script>
        document.getElementById("new-team-section-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/team-section/new?team_id=${team_id}');
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
