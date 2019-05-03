<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
    <form:form method="post" commandName="user">

        <fieldset class="form-group">
            <form:label path="username">Логин</form:label>
            <form:input path="username" type="text" class="form-control" required="required"/>
            <form:errors path="username" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="password">Пароль</form:label>
            <form:input path="password" type="text" class="form-control" required="required"/>
            <form:errors path="password" cssClass="text-warning"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="roles">Роли</form:label>
            <form:select path="roles" items="${roles}"
                         multiple="true" size="5" class="form-control"/>
        </fieldset>

        <fieldset class="form-group">
            <form:label path="active">Активный</form:label>
            <form:checkbox path="active" items="${user.active}"/>
            <form:errors path="active" cssClass="text-warning"/>
        </fieldset>

        <button type="submit" class="btn btn-success">Добавить</button>
        <a type="button" class="btn btn-danger" href="/user">Отмена</a>
    </form:form>
</div>
<%@ include file="../common/footer.jspf" %>