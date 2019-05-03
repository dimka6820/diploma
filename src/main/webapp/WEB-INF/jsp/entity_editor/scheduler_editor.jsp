<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
    <form:form method="post" commandName="lesson">
        <form:hidden path="classRoom"/>
        <form:hidden path="group"/>
        <form:hidden path="weekNumber"/>
        <form:hidden path="weekDay"/>
        <form:hidden path="lessonNumber"/>
        <form:hidden path="canceled"/>
        <form:hidden path="done"/>

        <fieldset class="form-group">
            <form:label path="teacher">Преподаватель</form:label>
            <form:select id="first" path="teacher" multiple="false" size="5" class="form-control" required="required">
                <c:forEach items="${teachers}" var="teacher">
                    <option value="${teacher.id}" data-id="${teacher.id}">
                            ${teacher.surname} ${teacher.name} ${teacher.lastname}
                    </option>
                </c:forEach>
            </form:select>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="discipline">Предмет</form:label>
            <form:select id="second" path="discipline" multiple="false" size="5" class="form-control"
                         required="required">
                <c:forEach items="${teachers}" var="teacher">
                    <c:forEach items="${teacher.disciplines}" var="discipline">
                        <option class="discipline" value="${discipline.id}"
                                data-id="${teacher.id}">${discipline.name}</option>
                    </c:forEach>
                </c:forEach>
            </form:select>
        </fieldset>

        <button type="submit" class="btn btn-success">Добавить</button>
        <a type="button" class="btn btn-danger" href="/scheduler?body=1&number=201">Отмена</a>
    </form:form>
</div>

<script>
    $(document).ready(function () {
        $("#first").find("option").click(function () {
            id = $(this).data("id");
            $("#second").find("option").each(function () {
                $(this).css("display", "block");
                if ($(this).data("id") !== id) {
                    $(this).css("display", "none");
                }
            });
            $("#second").change();
        });
    });
</script>

<%@ include file="../common/footer.jspf" %>