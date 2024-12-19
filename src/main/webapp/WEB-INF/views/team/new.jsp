<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value='/team/new'/>" method="POST" class="form-content">
    <label for="name">Название:</label>
    <input id="name" name="name" type="text" placeholder="Name"/>
    <br/>
    <input type="submit" value="Создать!" class="form-button"/>
</form>
