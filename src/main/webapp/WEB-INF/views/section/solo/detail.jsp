<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="List detail">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tasks.css">

    <div class="header">
        <a href="${pageContext.request.contextPath}/profile" class="profile-link">
            <img src="https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg" alt="Profile Picture" class="profile-picture-small"/>
            Профиль
        </a>
    </div>

    <div class="container">
        <div class="content">
            <h4>Секция: ${section.name}</h4>
            <h5>Задачи в этой секции</h5>
            <div class="section-list-container">
                <ul class="section-list">
                    <c:forEach var="task" items="${tasks}">
                        <li class="section-item">
                            <a href="#" class="task-link" data-id="${task.id}">${task.name}</a>
                            <c:if test="${section.type == 'TASK'}">
                                <form action="<c:url value='/task/${task.id}?section_id=${section.id}'/>" method="POST" class="task-form">
                                    <input type="hidden" name="_method" value="PATCH"/>
                                    <label class="checkbox-container">
                                        <input type="checkbox" class="task-checkbox" onclick="this.form.submit()"/>
                                        <span class="checkmark"></span>
                                    </label>
                                </form>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr/>
            <a href="#" class="action-link" id="new-task-link">Создать новую задачу</a>
            <a href="#" class="action-link" id="edit-section-link">Обновить список</a>
            <form action="<c:url value='/solo-section/${section.id}'/>" method="POST" class="delete-form">
                <input type="hidden" name="_method" value="DELETE"/>
                <input type="submit" value="Удалить список!" class="delete-button"/>
            </form>
            <a href="${pageContext.request.contextPath}/solo-section" class="action-link">Назад ко всем спискам</a>
        </div>

        <div class="form-container" id="form-container" style="display: none;">
            <!-- Здесь будут загружены формы и информация о задачах -->
        </div>
    </div>

    <script>
        document.querySelectorAll('.task-link').forEach(item => {
            item.addEventListener('click', function(event) {
                event.preventDefault();
                const taskId = this.dataset.id;
                fetchTaskDetails(taskId);
            });
        });

        document.getElementById("new-task-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/task/new?section_id=${section.id}', 'form-container');
        });

        document.getElementById("edit-section-link").addEventListener("click", function(event) {
            event.preventDefault();
            toggleForm('${pageContext.request.contextPath}/solo-section/edit?section_id=${section.id}', 'form-container');
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

        function fetchTaskDetails(taskId) {
            var taskDetailContainer = document.getElementById("form-container");
            fetch('${pageContext.request.contextPath}/task/' + taskId + '?section_id=${section.id}')
                .then(response => response.text())
                .then(html => {
                    taskDetailContainer.innerHTML = html;
                    taskDetailContainer.style.display = "block";
                })
                .catch(error => console.error('Ошибка загрузки деталей задачи:', error));
        }
    </script>
</t:mainLayout>
