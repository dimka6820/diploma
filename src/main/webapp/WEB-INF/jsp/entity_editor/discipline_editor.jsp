<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
    <form:form method="post" commandName="discipline">

        <fieldset class="form-group">
            <form:label path="name">Название</form:label>
            <form:input path="name" type="text" class="form-control" required="required"/>
            <form:errors path="name" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="teachers">Преподаватели</form:label>
            <form:select path="teachers" items="${teacherList}"
                         multiple="true" size="5" class="form-control"/>
        </fieldset>

        <button type="submit" class="btn btn-success">Добавить</button>
        <a type="button" class="btn btn-danger" href="/discipline">Отмена</a>
    </form:form>
</div>
<%@ include file="../common/footer.jspf" %>