<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-wrapper">
    <div class="content">
        <h2>Смена аватара</h2>
        <div class="avatar-preview mb-3">
            <img src="${urlPhoto}" alt="Avatar" class="rounded-circle" width="250" height="250">
        </div>

        <form action="${pageContext.request.contextPath}/profile/change-avatar" method="post" enctype="multipart/form-data" class="avatar-form mb-4">
            <div class="form-group">
                <label for="avatarFile">Сменить аватар</label>
                <input type="file" name="file" id="avatarFile" class="form-control-file" accept="image/*" required>
            </div>
            <button type="submit" class="form-button">Загрузить аватар</button>
        </form>

        <form action="${pageContext.request.contextPath}/profile/change-avatar" method="post">
            <input type="hidden" name="_method" value="DELETE">
            <button type="submit" class="delete-button">Удалить аватар</button>
        </form>
    </div>
</div>
