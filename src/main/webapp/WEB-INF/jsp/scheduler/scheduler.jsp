<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
    <p class="text-center bg-success">Занятие проведено</p>
    <p class="text-center bg-warning">Занятие отменено</p>
    <p class="text-center bg-danger">Занятие не было проведено</p>
    <table class="table table-bordered">
        <caption class="text-center h3">Нечетная неделя</caption>
        <thead>
        <tr>
            <th class="text-center">Пара</th>
            <th class="text-center">Пн</th>
            <th class="text-center">Вт</th>
            <th class="text-center">Ср</th>
            <th class="text-center">Чт</th>
            <th class="text-center">Пт</th>
            <th class="text-center">Сб</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lesson1}" var="lesson" varStatus="number">
            <tr>
                <td class="text-center" height="140" width="140">${number.index+1}</td>
                <c:forEach items="${lesson}" var="currentLesson" varStatus="day">
                    <td height="140" width="140" class="text-center
                    <c:if test="${currentLesson.done}"> bg-success </c:if>
                    <c:if test="${not empty currentLesson&& not empty currentLesson.done && !currentLesson.done  && !currentLesson.canceled}"> bg-danger </c:if>
                    <c:if test="${currentLesson.canceled}"> bg-warning </c:if>">

                        <c:if test="${not empty currentLesson}">
                            ${currentLesson.id}
                            <b>${currentLesson.classRoom.body}/${currentLesson.classRoom.number}</b>
                            </br>
                            ${currentLesson.discipline.name}
                            <br/>
                            ${currentLesson.teacher.surname}
                            <br/>

                            <sec:authorize access="hasAnyRole('ADMIN')">
                                <a type="button" class="btn btn-danger"
                                   href="/delete-lesson?id=${currentLesson.id}&classRoomId=${classRoom}">Удалить</a>
                                <a type="button" class="btn btn-warning"
                                   href="/cancel-lesson?id=${currentLesson.id}&classRoomId=${classRoom}">Отменить</a>
                            </sec:authorize>
                        </c:if>

                        <sec:authorize access="hasAnyRole('ADMIN')">
                            <c:if test="${empty currentLesson}">
                                <a type="button" class="btn btn-success"
                                   href="/add-lesson?week=1&day=${day.index+1}&number=${number.index+1}&classRoom=${classRoom}">Добавить</a>
                            </c:if>
                        </sec:authorize>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<br/>

<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">Четная неделя</caption>
        <thead>
        <tr>
            <th class="text-center">Пара</th>
            <th class="text-center">Пн</th>
            <th class="text-center">Вт</th>
            <th class="text-center">Ср</th>
            <th class="text-center">Чт</th>
            <th class="text-center">Пт</th>
            <th class="text-center">Сб</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lesson2}" var="lesson" varStatus="number">
            <tr>
                <td class="text-center" height="140" width="140">${number.index+1}</td>
                <c:forEach items="${lesson}" var="currentLesson" varStatus="day">
                    <td height="140" width="140" class="text-center
                    <c:if test="${currentLesson.done}"> bg-success </c:if>
                    <c:if test="${not empty currentLesson&& not empty currentLesson.done && !currentLesson.done  && !currentLesson.canceled}"> bg-danger </c:if>
                    <c:if test="${currentLesson.canceled}"> bg-warning </c:if>">

                        <c:if test="${not empty currentLesson}">
                            <b>${currentLesson.classRoom.body}/${currentLesson.classRoom.number}</b>
                            </br>
                            ${currentLesson.discipline.name}
                            <br/>
                            ${currentLesson.teacher.surname}
                            <br/>
                            <sec:authorize access="hasAnyRole('ADMIN')">
                                <a type="button" class="btn btn-danger"
                                   href="/delete-todo?id=${currentLesson.id}&classRoomId=${classRoom}">Удалить</a>
                                <a type="button" class="btn btn-warning"
                                   href="/cancel-lesson?id=${currentLesson.id}&classRoomId=${classRoom}">Отменить</a>
                            </sec:authorize>
                        </c:if>

                        <sec:authorize access="hasAnyRole('ADMIN')">
                            <c:if test="${empty currentLesson}">
                                <a type="button" class="btn btn-success"
                                   href="/add-lesson?week=2&day=${day.index+1}&number=${number.index+1}&classRoom=${classRoom}">Добавить</a>
                            </c:if>
                        </sec:authorize>

                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../common/footer.jspf" %>