<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<div class="container">
	<form:form method="post" commandName="lesson">
		<form:hidden path="classRoom" />
		<form:hidden path="group" />
		<form:hidden path="weekNumber" />
		<form:hidden path="weekDay" />
		<form:hidden path="lessonNumber" />
		<form:hidden path="canceled" />
		<form:hidden path="done" />

		<fieldset class="form-group">
		    <form:label path="teacher">Преподаватель</form:label>
            <form:select path="teacher" items="${teacherList}"
                multiple="false" size="5" class="form-control" required="required" />
		</fieldset>

		<fieldset class="form-group">
		    <form:label path="discipline">Предмет</form:label>
            <form:select path="discipline" items="${disciplineList}"
                multiple="false" size="5" class="form-control" required="required" />
		</fieldset>

		<button type="submit" class="btn btn-success">Добавить</button>
	</form:form>
</div>
<%@ include file="../common/footer.jspf" %>