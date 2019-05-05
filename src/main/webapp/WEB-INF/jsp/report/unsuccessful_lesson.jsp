<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
    <table class="table table-bordered">
        <caption class="text-center h3">Непроведеные занятия</caption>
        <thead>
        <tr>
            <th class="text-center">Пара</th>
            <th class="text-center">Время</th>
            <th class="text-center">Фото</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${unsuccessfulLessons}" var="unsuccessfulLesson">
            <tr>
                <td rowspan="${fn:length(unsuccessfulLesson.image)+1}">
                        ${unsuccessfulLesson.lesson.id}
                </td>
            </tr>
            <c:forEach items="${unsuccessfulLesson.image}" var="image">

                <tr>
                    <td>
                            ${image}
                    </td>
                    <td>
                        <img width="100px" height="100px" src="cover/${image}">
                    </td>
                </tr>

            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../common/footer.jspf" %>