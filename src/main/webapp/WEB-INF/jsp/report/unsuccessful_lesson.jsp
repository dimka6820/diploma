<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">Непроведеные занятия</caption>
        <thead>
        <tr>
            <th class="text-center">Занятие</th>
            <th class="text-center">Время</th>
            <th class="text-center">Фото</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${unsuccessfulLessons}" var="unsuccessfulLesson">
            <tr>
                <td rowspan="${fn:length(unsuccessfulLesson.image)+1}">
                    <a href="/lesson?lessonId=${unsuccessfulLesson.lesson.id}">
                            ${unsuccessfulLesson.lesson.teacher.surname}
                            ${unsuccessfulLesson.lesson.teacher.name}
                            ${unsuccessfulLesson.lesson.teacher.lastname}
                    </a>
                </td>
            </tr>
            <c:forEach items="${unsuccessfulLesson.image}" var="image">

                <tr>
                    <td>
                            ${image}
                    </td>
                    <td>
                        <img class="popup" tabindex="1" src="cover/${image}">
                    </td>
                </tr>

            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../common/footer.jspf" %>