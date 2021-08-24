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
  <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/marondalgram/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<div class="create-timeline-box">
				<div class="create-post-box p-3 m-2">
					<b>New Posting</b>
					<textarea class="form-control" rows="3" id="contentInput"></textarea>
					<div class="d-flex justify-content-between mt-2">
						<input type="file" accept="image/*" id="fileInput">
						<button type="button" class="btn btn-sm" id="saveBtn">공유하기</button>
					</div>
				</div>
				<div class="timeline-box p-3 m-2"> <!-- 반복 -->
					<div class="d-flex justify-content-between">
						<div>게시자 아이디</div>
						<div>삭제 -- 햄버거 버튼</div>
					</div>
					<div class="timeline-img-box">
						<img src="#" class="image-thumbnail">
					</div>
					<div class="like-comment-box">
						<div>좋아요</div>
						<div>comment</div>
					</div>
				</div>
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