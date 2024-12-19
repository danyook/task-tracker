<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/profile/upload-photo'/>" method="post" enctype="multipart/form-data">
    <label for="image">Выберите изображение:</label>
    <input type="file" name="image" id="image" accept="image/*" required/>
    <input type="submit" value="Загрузить"/>
</form>
