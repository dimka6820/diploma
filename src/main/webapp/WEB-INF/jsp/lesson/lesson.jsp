<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">О зантии</caption>
        <tbody>
        <tr>
            <th>Преподаватель</th>
            <td>
                ${lesson.teacher.surname}
                ${lesson.teacher.name}
                ${lesson.teacher.lastname}
            </td>
        </tr>
        <tr>
            <th>Аудитория</th>
            <td>
                ${lesson.classRoom.body}/${lesson.classRoom.number}
            </td>
        </tr>
        <tr>
            <th>Предмет</th>
            <td>
                ${lesson.discipline.name}
            </td>
        </tr>
        <tr>
            <th>Номер пары</th>
            <td>
                ${lesson.lessonNumber}
            </td>
        </tr>
        <tr>
            <th>Дата</th>
            <td>
                ${fn:substring(unsuccessfulLesson.image[1], 0, 10)}
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">Снимки аудитории</caption>
        <thead>
        <tr>
            <th class="text-center">Время</th>
            <th class="text-center">Фото</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${unsuccessfulLesson.image}" var="image">

            <tr>
                <td>
                        ${image}
                </td>
                <td>
                    <img width="150px" height="150px" src="cover/${image}">
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../common/footer.jspf" %>