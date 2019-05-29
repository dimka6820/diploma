<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<sec:authorize access="hasAnyRole('ADMIN')">
    <div class="container">
     <c:if test="${teacherId != null}">
        <a class="btn btn-success" href="/createPdf?teacherId=${teacherId}">Выгрузить отчет</a>
     </c:if>
    </div>
</sec:authorize>

<div class="container">
     <c:if test="${unsuccessfulLessons[0] == null || empty unsuccessfulLessons}">
           <p  class="text-center h3">  Непроведеных занятий нет </p>
     </c:if>

     <c:if test="${unsuccessfulLessons[0] != null}">
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
    </c:if>

</div>
<%@ include file="../common/footer.jspf" %>