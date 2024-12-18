<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Profile">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">

    <div class="navbar">
        <div class="navbar-left">
            <a href="${pageContext.request.contextPath}/solo-section">Мои задачи</a>
            <a href="${pageContext.request.contextPath}/team">Мои команды</a>
        </div>
    </div>

    <div class="profile-container">
        <div class="profile-content">
            <div class="profile-info">
                <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture" class="profile-picture"/>
                <p><strong>Имя пользователя:</strong> ${sessionScope.user.username}</p>
                <p><strong>Имя:</strong> ${sessionScope.user.name}</p>
                <p><strong>Фамилия:</strong> ${sessionScope.user.surname}</p>
            </div>

            <div class="profile-actions">
                <button class="profile-button" id="edit-profile-btn">Редактировать профиль</button>
                <button class="profile-button" id="change-password-btn">Сменить пароль</button>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="profile-button">Выйти</button>
                </form>
            </div>
        </div>

        <div class="form-container" id="form-container" style="display: none;">
            <!-- Здесь будет загружена форма редактирования профиля или смены пароля -->
        </div>
    </div>

    <script>
        document.getElementById("edit-profile-btn").addEventListener("click", function() {
            toggleForm('${pageContext.request.contextPath}/profile/edit');
        });

        document.getElementById("change-password-btn").addEventListener("click", function() {
            toggleForm('${pageContext.request.contextPath}/password');
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
