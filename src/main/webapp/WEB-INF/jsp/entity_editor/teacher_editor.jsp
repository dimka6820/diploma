<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
	<form:form method="post" commandName="teacher">

		<fieldset class="form-group">
			<form:label path="surname">Фамилия</form:label>
			<form:input path="surname" type="text" class="form-control" required="required" />
			<form:errors path="surname" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="name">Имя</form:label>
			<form:input path="name" type="text" class="form-control" required="required" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="lastname">Отчество</form:label>
			<form:input path="lastname" type="text" class="form-control" required="required" />
			<form:errors path="lastname" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="number">Табельный номер</form:label>
			<form:input path="number" type="text" class="form-control" required="required" />
			<form:errors path="number" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Добавить</button>
	</form:form>
</div>
<%@ include file="../common/footer.jspf" %>