<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marondalgram  - timeline</title>
<!-- bootstrap CDN link -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/marondalgram/include/header.jsp" />
		<section class="timeline-section d-flex justify-content-center">
			<div class="create-timeline-box">
				<div class="create-post-box p-3 m-2">
					<sapn class="title-text"><h5>New Posting</h5></span>
					<textarea class="form-control" rows="3" id="contentInput"></textarea>
					<div class="d-flex justify-content-between mt-2">
						<input type="file" accept="image/*" id="fileInput">
						<button type="button" class="btn btn-sm" id="saveBtn">공유하기</button>
					</div>
				</div>
				
				<!-- 반복 시작-->
				<c:forEach var="post" items="${postList }" varStatus="status">
				<div class="timeline-box p-3 m-2"> 
					<div class="d-flex justify-content-between ml-3 mr-2">
						<div class="title-text"><i class="bi bi-person-circle"></i></i> <b>${post.userName }</b></div>
						<div class="title-text"><i class="bi bi-three-dots"></i></div>
					</div>
					<div class="timeline-img-box mt-2 mx-3">
						<c:if test="${not empty post.imagePath }">
						<img class="image-thumbnail" src="${post.imagePath }">
						</c:if>
					</div>
					<div class="mx-3 title-text">${post.content }</div>
					<hr>
					<div class="like-comment-box mt-2 mx-3">
						<div class="title-text"><i class="bi bi-suit-heart-fill"></i> 10개</div>
						<div class="mt-2"> 아이디 코멘트</div>
						<div class="mt-2 d-flex input-group">
							<span class="title-text d-flex align-items-center"><b>comment</b></span>
							<input type="text" class="form-control ml-2"> 
							<button type="button" class="btn btn-sm ml-1">저장</button>
						</div>
					</div>
				</div>
				</c:forEach>
				<!-- 반복 끝-->
				
			</div>	
		</section>
		<c:import url="/WEB-INF/jsp/marondalgram/include/footer.jsp" />
	</div>
	<script>
	 $(document).ready(function(){
		 $("#saveBtn").on("click", function(){
		 var content = $("#contentInput").val().trim();
		 
		  if(content == null || content ==""){
			  alert("내용을 입력해주세요");
		  }
		  var formData = new FormData();
		  formData.append("content", content);
		  formData.append("file", $("#fileInput")[0].files[0]);
		  
		  $.ajax({
			  enctype:"multipart/form-data",//파일 업로드 필수
			  type:"POST",
			  url:"/marondalgram/post/create",
			  processData:false,//파일 업로드 필수
			  contentType:false,//파일 업로드 필수
			  data:formData,
			  success:function(data){
				  if(data.result == "success"){
					  alert("포스팅 성공");
				  }else{
					  alert("포스팅 실패");
				  }
			  },
			  error:function(e){
				 alert("error"); 
			  }
		  });
		 
		 });
	 });
	 
	</script>

</body>
</html>