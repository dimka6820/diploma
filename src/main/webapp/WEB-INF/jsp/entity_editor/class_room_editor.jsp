<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
	<form:form method="post" commandName="classroom">
		<fieldset class="form-group">
			<form:label path="body">Корпус</form:label>
			<form:input path="body" type="number" class="form-control"
				required="required" />
			<form:errors path="body" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="number">Аудитория</form:label>
			<form:input path="number" type="text" class="form-control"
				required="required" />
			<form:errors path="number" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="camera">Индентификатор камеры</form:label>
			<form:input path="camera" type="text" class="form-control"/>
			<form:errors path="camera" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Добавить</button>
	</form:form>
</div>
<%@ include file="../common/footer.jspf" %>