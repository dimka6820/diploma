<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    ${name}, Добро пожаловать в систему контроля за занятиями.
    <br/>
    <br/>
    Вам доступны следующие действия:
    <ul>
        <li><a href="/classroom">Расписание</a></li>
        <li><a href="/qwert">TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEST</a></li>
        <li><a href="/teacher">Преподаватели</a></li>
        <li><a href="/discipline">Предметы</a></li>

        <sec:authorize access="hasAnyRole('ADMIN', 'STATIST')">
            <li><a href="/report">Отчеты</a></li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ADMIN')">
            <li><a href="/user">Пользователи</a></li>
        </sec:authorize>
        <li><a href="/logout">Выход</a></li>
    </ul>

</div>

<div class="wrapper">
    <video id="video" width="400" height="300"></video>
</div>
<script>
    //      (function() {
    //           var video = document.getElementById('video');
    //             alert(video);
    //var constraints = {
    //               video: true,
    //               audio: false
    //           };

    //           navigator.mediaDevices.getUserMedia(constraints).then(function(stream){
    //               video.srcObject=stream;
    //               video.play();
    //           }).catch(function(err){
    //  alert(err);

    //           });
    //       })();
</script>
<%@ include file="common/footer.jspf" %>