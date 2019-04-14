<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

	<div class="container">
		<table class="table table-bordered">
			<caption>Нечетная неделя</caption>
			<thead>
                <tr>
                    <th>Para</th>
                    <th>Pn</th>
                    <th>Vt</th>
                    <th>Sr</th>
                    <th>Cht</th>
                    <th>Pt</th>
                    <th>Sb</th>
                </tr>
			</thead>
			<tbody>
			    <c:forEach items="${lesson1}" var="lesson" varStatus="status">
			        <tr>
			            <td height="140" width="140">${status.index+1}</td>
                        <c:forEach items="${lesson}" var="currentLesson"  >
                        <td height="140" width="140" class="align-middle">
                            <c:if test="${not empty currentLesson}">
                                 ${currentLesson.id} </br>
                                 ${currentLesson.teacher.lastname}
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
                    <th>Para</th>
                    <th>Pn</th>
                    <th>Vt</th>
                    <th>Sr</th>
                    <th>Cht</th>
                    <th>Pt</th>
                    <th>Sb</th>
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
                            </c:if>
                        </td>
                       </c:forEach>
                    </tr>
                </c:forEach>
			</tbody>
		</table>
	</div>

<%@ include file="common/footer.jspf" %>