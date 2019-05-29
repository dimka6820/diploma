<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>


<div class="container">
    <table class="table table-bordered">
    	<caption class="text-center h3">Выберите преподавателя</caption>

    	<tbody>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>
                    <a href="/teacherReport?teacherId=${teacher.id}">
                        ${teacher.surname} ${teacher.name} ${teacher.lastname}
                    </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../common/footer.jspf" %>