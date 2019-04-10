<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Your todos are</caption>
			<thead>
                <tr>
                    <th>Пара</th>
                    <th>Пн</th>
                    <th>Вт</th>
                    <th>Ср</th>
                    <th>Чт</th>
                    <th>Пт</th>
                    <th>Сб</th>
                </tr>
			</thead>
			<tbody>
                <c:forEach items="${lessons}" var="lesson">
                    <tr>
                        <td></td>
                        <td>
                            <c:if test="${(lesson.weekDay == 1) && (lesson.lessonNumber == 1)}">
                                ${lesson.id} </br>
                                ${lesson.teacher.lastname}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(lesson.weekDay == 2) && (lesson.lessonNumber == 1)}">
                                ${lesson.id}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(lesson.weekDay == 3) && (lesson.lessonNumber == 1)}">
                                ${lesson.id}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(lesson.weekDay == 4) && (lesson.lessonNumber == 1)}">
                                ${lesson.id}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(lesson.weekDay == 5) && (lesson.lessonNumber == 1)}">
                                ${lesson.id}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(lesson.weekDay == 6) && (lesson.lessonNumber == 1)}">
                                ${lesson.id}
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

				<!--<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>->
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-todo">Add a Todo</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>