<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

	<div class="container">
		<table class="table table-bordered">
			<caption>Нечетная неделя</caption>
			<thead>
                <tr>
                    <th class="text-center">Пара</th>
                    <th class="text-center">Пн</th>
                    <th class="text-center">Вт</th>
                    <th class="text-center">Ср</th>
                    <th class="text-center">Чт</th>
                    <th class="text-center">Пт</th>
                    <th class="text-center">Сб</th>
                </tr>
			</thead>
			<tbody>
			    <c:forEach items="${lesson1}" var="lesson" varStatus="status">
			        <tr>
			            <td class="text-center" height="140" width="140">${status.index+1}</td>
                        <c:forEach items="${lesson}" var="currentLesson"  >
                        <td class="text-center" height="140" width="140">
                            <c:if test="${not empty currentLesson}">
                                 <b>${currentLesson.classRoom.body}/${currentLesson.classRoom.number}</b>
                                 </br>
                                 ${currentLesson.discipline.name}
                                 <br/>
                                 ${currentLesson.teacher.lastname}
                                 <br/>
                                 <a type="button" class="btn btn-warning"
                                    href="/update-todo?id=${currentLesson.id}">Update</a>
                                    <a type="button" class="btn btn-danger"
                                        href="/delete-todo?id=${currentLesson.id}">Delete</a>
                            </c:if>

                            <c:if test="${empty currentLesson}">
                                <a type="button" class="btn btn-success" href="/add-todo">Add</a>
                            </c:if>

                        </td>
                       </c:forEach>
                    </tr>
                </c:forEach>
			</tbody>
		</table>

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
				</c:forEach>-->
		<div>
			<a class="button" href="/add-todo">Add a Todo</a>
		</div>
	</div>

<br/>

	<div class="container">
		<table class="table table-bordered">
			<caption>Четная неделя</caption>
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
			    <c:forEach items="${lesson2}" var="lesson" varStatus="status">
			        <tr>
			            <td height="140" width="140">${status.index+1}</td>
                        <c:forEach items="${lesson}" var="currentLesson"  >
                        <td height="140" width="140" class="align-middle">
                            <c:if test="${not empty currentLesson}">
                                 ${currentLesson.id} </br>
                                 ${currentLesson.teacher.lastname}
                                 <td><a type="button" class="btn btn-success"
                                 	href="/update-todo?id=${todo.id}">Update</a></td>
                                 <td><a type="button" class="btn btn-warning"
                                 	href="/delete-todo?id=${todo.id}">Delete</a></td>

                            </c:if>

                            <c:if test="${empty currentLesson}">
                                <a type="button" class="btn btn-warning" href="/add-todo">Add</a>
                            </c:if>
                        </td>
                       </c:forEach>
                    </tr>
                </c:forEach>
			</tbody>
		</table>
	</div>

<%@ include file="../common/footer.jspf" %>