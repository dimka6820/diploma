<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<sec:authorize access="hasAnyRole('ADMIN')">
    <div class="container">
        <a class="btn btn-success" href="/add-teacher">Добавить нового преподавателя</a>
    </div>
</sec:authorize>

<div class="container">
    <table class="table table-bordered">
    	<caption class="text-center h3">Преподаватели</caption>
    	<thead>
            <tr>
                <th class="text-center">Преподаватель</th>
                <th class="text-center">Пары</th>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <th class="text-center">Управление</th>
                </sec:authorize>

            </tr>
    	</thead>
    	<tbody>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>${teacher.surname} ${teacher.name} ${teacher.lastname}</td>
                    <td>
                        <ul>
                            <c:forEach items="${teacher.disciplines}" var="discipline">
                                <li>${discipline.name}</li>
                            </c:forEach>
                        </ul>
                   </td>
                   <sec:authorize access="hasAnyRole('ADMIN')">
                        <td>
                              <a type="button" class="btn btn-warning"
                                 href="/update-teacher?id=${teacher.id}">Изменить</a>
                              <a type="button" class="btn btn-danger"
                                  href="/remove-teacher?id=${teacher.id}">Удалить</a>
                        </td>
                   </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../common/footer.jspf" %>