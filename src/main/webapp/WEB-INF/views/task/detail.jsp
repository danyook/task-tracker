<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-content">
    <h1>Задача: ${task.getName()}</h1>
    <h2>Описание:</h2>
    <p>${task.getDescription()}</p>
    <a href="#" class="action-link" id="edit-task-link">Обновить задачу</a>
    <br>
    <a href="${pageContext.request.contextPath}/team-section/${section_id}">Назад к списку задач</a>
    <form action="<c:url value='/task/${task.getId()}'/>" method="POST" class="delete-form">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="section_id" value="${section_id}"/>
        <input type="submit" value="Удалить задачу!" class="delete-button"/>
    </form>
</div>

<div class="form-container" id="edit-task-container" style="display: none;">
    <!-- Здесь будет загружена форма обновления задачи -->
</div>

<script>
    document.getElementById("edit-task-link").addEventListener("click", function(event) {
        event.preventDefault();
        toggleForm('${pageContext.request.contextPath}/task/edit?task_id=${task.getId()}&section_id=${section_id}', 'edit-task-container');
    });

    function toggleForm(url, containerId) {
        var formContainer = document.getElementById(containerId);
        if (formContainer.style.display === "none" || formContainer.innerHTML === "") {
            fetch(url)
                .then(response => response.text())
                .then(html => {
                    formContainer.innerHTML = html;
                    formContainer.style.display = "block";
                })
                .catch(error => console.error('Ошибка загрузки формы:', error));
        } else {
            formContainer.style.display = "none";
            formContainer.innerHTML = "";
        }
    }
</script>