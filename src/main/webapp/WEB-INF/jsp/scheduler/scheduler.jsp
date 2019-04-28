<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
	<table class="table table-bordered">
		<caption class="text-center h3">Нечетная неделя</caption>
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
		    <c:forEach items="${lesson1}" var="lesson" varStatus="number">
		        <tr>
		            <td class="text-center" height="140" width="140">${number.index+1}</td>
                    <c:forEach items="${lesson}" var="currentLesson" varStatus="day">
                    <td class="text-center" height="140" width="140">
                        <c:if test="${not empty currentLesson}">
                             <b>${currentLesson.classRoom.body}/${currentLesson.classRoom.number}</b>
                             </br>
                             ${currentLesson.discipline.name}
                             <br/>
                             ${currentLesson.teacher.surname}
                             <br/>
                             <a type="button" class="btn btn-danger"
                                 href="/delete-lesson?id=${currentLesson.id}&classRoomId=${classRoom}">Удалить</a>
                        </c:if>

                        <c:if test="${empty currentLesson}">
                            <a type="button" class="btn btn-success" href="/add-lesson?week=1&day=${day.index+1}&number=${number.index+1}&classRoom=${classRoom}">Добавить</a>
                        </c:if>

                    </td>
                   </c:forEach>
                </tr>
            </c:forEach>
		</tbody>
	</table>
	<div>
		<a class="button" href="/add-todo">Add a Todo</a>
	</div>
</div>

<br/>

<div class="container">
	<table class="table table-bordered">
		<caption class="text-center h3">Четная неделя</caption>
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
		    <c:forEach items="${lesson2}" var="lesson" varStatus="number">
		        <tr>
		            <td class="text-center" height="140" width="140">${number.index+1}</td>
                    <c:forEach items="${lesson}" var="currentLesson"  varStatus="day">
                    <td class="text-center" height="140" width="140">
                        <c:if test="${not empty currentLesson}">
                             <b>${currentLesson.classRoom.body}/${currentLesson.classRoom.number}</b>
                             </br>
                             ${currentLesson.discipline.name}
                             <br/>
                             ${currentLesson.teacher.surname}
                             <br/>
                                <a type="button" class="btn btn-danger"
                                    href="/delete-todo?id=${currentLesson.id}&classRoomId=${classRoom}">Удалить</a>
                        </c:if>

                        <c:if test="${empty currentLesson}">
                            <a type="button" class="btn btn-success" href="/add-lesson?week=2&day=${day.index+1}&number=${number.index+1}&classRoom=${classRoom}">Добавить</a>
                        </c:if>

                    </td>
                   </c:forEach>
                </tr>
            </c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../common/footer.jspf" %>