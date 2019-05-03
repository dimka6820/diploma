<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
    <sec:authorize access="hasAnyRole('ADMIN')">
        <a class="btn btn-success" href="/add-classroom">Добавить новую аудиторию</a>
    </sec:authorize>
</div>
<br/>
<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">Аудитории</caption>
        <thead>
        <tr>
            <th class="text-center">Номер аудитории</th>
            <sec:authorize access="hasAnyRole('ADMIN')">
                <th class="text-center">Номер камеры</th>
                <th class="text-center">Управление</th>
            </sec:authorize>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${classrooms}" var="classroom" varStatus="status">
            <tr>
                <td><a class="btn btn-default"
                       href="/scheduler?body=${classroom.body}&number=${classroom.number}">${classroom.body}/${classroom.number}</a>
                </td>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <td>
                            ${classroom.camera}
                    </td>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <td>
                        <a class="btn btn-warning" href="/update-classroom?id=${classroom.id}">Изменить</a>
                        <a class="btn btn-danger" href="/remove-classroom?id=${classroom.id}">Удалить</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <p>Текущая неделя: <b>Нечетная</b></p>
    <p>Номер пары: <b>${currentLessonNumber}</b></p>
    <p>Текущие пары:
        <b>
            <ul>
                <c:forEach items="${currentLessons}" var="currentLesson">
                    <li>${currentLesson}</li>
                </c:forEach>
            </ul>
        </b>
    </p>


    <br/>
</div>

<%@ include file="../common/footer.jspf" %>