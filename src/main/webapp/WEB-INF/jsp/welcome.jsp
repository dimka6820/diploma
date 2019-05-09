<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    ${name}, Добро пожаловать
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
<%@ include file="common/footer.jspf"%>