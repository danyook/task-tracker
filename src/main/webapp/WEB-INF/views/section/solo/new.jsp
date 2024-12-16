<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="New Section">

    <form action="<c:url value='/solo-section/new'/>" method="POST">
        <label for="name">Name:</label>
        <input id="name" name="name" type="text" placeholder="Name" value="${name}" />
        <br/>

        <label for="type">Type:</label>
        <select id="type" name="type">
            <option value="NOTE">Заметка</option>
            <option value="TASK">Задача</option>
        </select>
        <br/>

        <input type="submit" value="Создать!"/>
    </form>

</t:mainLayout>
