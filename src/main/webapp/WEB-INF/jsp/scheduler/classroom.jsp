<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
    <sec:authorize access="hasAnyRole('ADMIN')">
        <a class="btn btn-success" href="/add-classroom">Добавить новую аудиторию</a>
    </sec:authorize>
</div>
<br/>
<div class="container">
    <sec:authorize access="hasAnyRole('ADMIN')">
        <table class="table table-bordered">
            <caption class="text-center h3">Аудитории</caption>
            <thead>
            <tr>
                <th class="text-center" rowspan="2">Корпус</th>
                <th class="text-center" colspan="3">Аудитории</th>
            </tr>
            <tr>
                <th class="text-center">Номер аудитории</th>
                <th class="text-center">Номер камеры</th>
                <th class="text-center">Управление</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${classRoomByBody}" var="classRoomByBodyVar" varStatus="status">
                <tr>
                    <td rowspan="${fn:length(classRoomByBodyVar.value)+1}">
                            ${classRoomByBodyVar.key}
                    </td>
                </tr>
                <c:forEach items="${classRoomByBodyVar.value}" var="classroom" varStatus="status">
                    <tr>
                        <td>
                            <a class="btn btn-default"
                               href="/scheduler?body=${classroom.body}&number=${classroom.number}">${classroom.body}/${classroom.number}</a>
                        </td>
                        <td>
                                ${classroom.camera}
                        </td>
                        <td>
                            <a class="btn btn-warning"
                               href="/update-classroom?id=${classroom.id}">Изменить</a>
                            <a class="btn btn-danger"
                               href="/remove-classroom?id=${classroom.id}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('USER')">
        <table class="table table-bordered">
            <caption class="text-center h3">Аудитории</caption>
            <thead>
            <tr>
                <th class="text-center">Корпус</th>
                <th class="text-center">Номер аудитории</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${classRoomByBody}" var="classRoomByBodyVar" varStatus="status">
                <tr>
                    <td>
                            ${classRoomByBodyVar.key}
                    </td>
                    <td>
                        <c:forEach items="${classRoomByBodyVar.value}" var="classroom" varStatus="status">

                            <a class="btn btn-default"
                               href="/scheduler?body=${classroom.body}&number=${classroom.number}">${classroom.body}/${classroom.number}</a>
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </sec:authorize>


    <p>Текущая неделя: <b>Нечетная</b></p>
    <p>Номер занятия: <b>${currentLessonNumber}</b></p>
    <p>Текущие занятия:
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