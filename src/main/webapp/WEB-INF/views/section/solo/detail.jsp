<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="List detail">
    <%@include file="/WEB-INF/views/parts/navbar.jsp" %>

    <h1>Секция: ${section.name}</h1>

    <h2>Задачи в этой секции</h2>
    <ul>
        <c:forEach var="task" items="${tasks}">
            <li>
                <a href="${pageContext.request.contextPath}/task/${task.id}?section_id=${section.id}">${task.name}</a>
                <c:if test="${section.type == 'TASK'}">
                    <form action="<c:url value='/task/${task.id}?section_id=${section.id}'/>" method="POST">
                        <input type="hidden" name="_method" value="PATCH"/>
                        <input type="submit" value="Выполнено!"/>
                    </form>
                </c:if>
                <br/>
            </li>
        </c:forEach>
    </ul>

    <a href="${pageContext.request.contextPath}/task/new?section_id=${section.id}">Создать новую задачу</a>
    <br/>

    <a href="${pageContext.request.contextPath}/solo-section/edit?section_id=${section.id}">Обновить список</a>
    <form action="<c:url value='/solo-section/${section.id}'/>" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="submit" value="Удалить список!"/>
    </form>

    <a href="${pageContext.request.contextPath}/solo-section">Назад ко всем спискам</a>


    <%@include file="/WEB-INF/views/parts/footer.jsp" %>
</t:mainLayout>
