<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
		<a class="btn btn-success" href="/add-classroom">Добавить новую аудиторию</a>
</div>
<br/>
<div class="container">
    <c:forEach items="${classrooms}" var="classroom" varStatus="status">
        <a class="btn btn-default" href="/scheduler?body=${classroom.body}&number=${classroom.number}">${classroom.body}/${classroom.number}</a>
        -<a class="btn btn-danger" href="/remove-classroom?id=${classroom.id}">Удалить</a>
        <br/>
    </c:forEach>
</div>

<%@ include file="../common/footer.jspf" %>