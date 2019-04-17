<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<sec:authorize access="hasAnyRole('ADMIN')">
    <div class="container">
        <a class="btn btn-success" href="/add-discipline">Добавить новый предмет</a>
    </div>
</sec:authorize>

<div class="container">
    <table class="table table-bordered">
    	<caption class="text-center h3">Предметы</caption>
    	<thead>
            <tr>
                <th class="text-center">Предмет</th>
                <th class="text-center">Преподаватели</th>
                <sec:authorize access="hasAnyRole('ADMIN')"><th class="text-center">Управление</th></sec:authorize>

            </tr>
    	</thead>
    	<tbody>
            <c:forEach items="${disciplines}" var="discipline">
                <tr>
                    <td>${discipline.name}</td>
                    <td>
                        <ul>
                            <c:forEach items="${discipline.teachers}" var="teacher">
                                <li>${teacher.surname} ${teacher.name} ${teacher.lastname}</li>
                            </c:forEach>
                        </ul>
                   </td>
                   <sec:authorize access="hasAnyRole('ADMIN')">
                        <td>
                              <a type="button" class="btn btn-warning"
                                 href="/update-discipline?id=${discipline.id}">Обновить</a>
                              <a type="button" class="btn btn-danger"
                                  href="/remove-discipline?id=${discipline.id}">Удалить</a>
                        </td>
                   </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../common/footer.jspf" %>