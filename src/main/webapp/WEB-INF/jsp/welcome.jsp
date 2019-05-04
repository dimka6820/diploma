<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	Welcome ${name}!! <a href="/list-todos">Click here</a> to manage your
	todo's.

</div>

<img src="cover=${123}">

<c:forEach items="${all}" var="img">
   ${img.image} <img src="/cover/${img.image}">
</c:forEach>
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
<%@ include file="common/footer.jspf"%>