<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

    <div class="container">
        <a class="btn btn-success" href="/add-user">Добавить нового пользователя</a>
    </div>

<div class="container">
    <table class="table table-bordered">
    	<caption class="text-center h3">Пользователи</caption>
    	<thead>
            <tr>
                <th class="text-center">Логин</th>
                <th class="text-center">Роли</th>
                <th class="text-center">Активен</th>
                <th class="text-center">Управление</th>
            </tr>
    	</thead>
    	<tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>
                        <ul>
                            <c:forEach items="${user.roles}" var="role">
                                <li>${role}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${user.active}</td>
                    <td>
                        <a type="button" class="btn btn-warning"
                                 href="/update-user?id=${user.id}">Обновить</a>
                        <a type="button" class="btn btn-danger"
                                  href="/remove-user?id=${user.id}">Удалить</a>
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../common/footer.jspf" %>