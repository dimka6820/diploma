<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
        <table class="table table-bordered">
            <caption class="text-center h3">Аудитории</caption>
            <thead>
            <tr>
                <th class="text-center">Корпус</th>
                <th class="text-center">Номер аудитории</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${classRoomByBody}" var="classRoomByBodyVar" varStatus="status">
                <tr>
                    <td>
                            ${classRoomByBodyVar.key}
                    </td>
                    <td>
                        <c:forEach items="${classRoomByBodyVar.value}" var="classroom" varStatus="status">
                            <a class="btn btn-default"
                               href="/classroomReport?classroomId=${classroom.id}">${classroom.body}/${classroom.number}</a>
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>

</div>

<%@ include file="../common/footer.jspf" %>