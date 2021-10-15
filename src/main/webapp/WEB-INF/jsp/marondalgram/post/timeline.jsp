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
				<!--  포스트 입력 박스 -->
				<c:if test="${not empty userName }" >
				<div class="create-post-box p-3 m-2 border rounded" id="create-post-box">
					<sapn class="title-text"><h5>New Posting</h5></span>
					<!-- 포스팅 내용 입력 -->
					<textarea class="form-control border-0 non-resize" rows="3" id="contentInput"></textarea>
					<!-- /포스팅 내용 입력 -->
					<!-- 파일 업로드 -->
					<div class="d-flex justify-content-between mt-2">
						<input type="file" accept="image/*" id="fileInput" class="d-none">
						<a href="#" id="imageUploadBtn"><i class="bi bi-file-earmark-image title-text"></i></a>
						<button type="button" class="btn btn-sm" id="saveBtn">업로드</button>
					</div>
					<!-- /파일 업로드 -->
				</div>
				</c:if>
				<!--  포스트 입력 박스 -->
				
				<!-- 타임라인 -->
				<c:forEach var="postWithComment" items="${postList }" varStatus="status">
				<div class="timeline-box p-3 m-2 border rounded"> 
					<div class="d-flex justify-content-between ml-2 mr-2">
						<div class="title-text"><i class="bi bi-person-circle"></i> <b>${postWithComment.post.userName }</b></div>
						<!-- 모어버튼 -->
						<!--  글의 userId와 세션의 userId가 일치하면 더보기 버튼 노출 -->
						<c:if test="${postWithComment.post.userId eq userId }">
						<div class="title-text" >
							<a href="#" class="moreBtn title-text" data-toggle="modal" data-target="#deleteModal" data-post-id="${postWithComment.post.id }"><i class="bi bi-three-dots" ></i></a>
						</div>
						</c:if>
					</div>
					<!-- 이미지 -->
					<div class="timeline-img-box mt-2 mx-2 border rounded">
						<img class="image-thumbnail" src="${postWithComment.post.imagePath }" id="imagePath" data-post-id="${postWithComment.post.id }">
					</div>
					<div class="post-content-box my-2 title-text">
						<div class="mx-3 mb-2 "> ${postWithComment.post.content } </div>
					</div>
					
					<div class="like-comment-box mt-2 mx-2">
						<!-- 좋아요 출력 -->
						<div class="title-text pb-2" >
							<a href="#" class="likeBtn" data-post-id="${postWithComment.post.id }">
							<c:choose>
								<c:when test="${postWithComment.like }">
									<!-- 풀하트 -->
									<i class="bi bi-suit-heart-fill text-danger" id="heartIcon-${postWithComment.post.id }"></i> 
								</c:when>
									<c:otherwise>
									<!-- 빈하트 -->
										<i class="bi bi-suit-heart title-text" id="heartIcon-${postWithComment.post.id }"></i>
									</c:otherwise>
							</c:choose>
							</a>
							<!-- 좋아요 갯수-->
							<b><span id="likeCount-${postWithComment.post.id }" data-post-id="${postWithComment.post.id }">${postWithComment.likeCount }</span> Like</b>
						</div>
						<!-- /좋아요 출력-->
						
						<!-- 코멘트 출력 -->
						<c:forEach var="comment" items="${postWithComment.commentList }">
						<div class="d-flex">
							<!-- 코멘트 내용 -->
							<div class="mt-2"> <span class="title-text"><b>${comment.userName }</b></span> ${comment.content }</div>
							<!-- 코멘트 삭제버튼 -->
							<!-- 글의 userId와 세션의 userId가 일치하면 삭제 버튼 노출 -->
							<c:if test="${comment.userId eq userId }">
								<div class="mt-2">
									<i class="deleteCommentBtn bi bi-x-square title-text ml-3" data-comment-id="${comment.id }"></i>
								</div>
							</c:if>
						</div>
						</c:forEach>
						<!-- /코멘트 출력 -->
						
						<!--  코멘트 입력 -->
						<div class="mt-2 d-flex input-group">
							<input type="text" class="commentBox form-control title-text border-0" placeholder="comment" id="commentInput-${postWithComment.post.id }"> 
							<button type="button" class="btn commentBtn btn-sm" data-post-id="${postWithComment.post.id }">게시</button>
						</div>
						<!--  /코멘트 입력 -->
					</div>
				</div>
				</c:forEach>
				<!-- /타임라인-->
			</div>	
		</section>
		<c:import url="/WEB-INF/jsp/marondalgram/include/footer.jsp" />
	</div>

	<!-- 모달의 a태그에 data-post-id값을 더보기 클릭시 마다 바꿔준다. -->
	
	<!-- Modal -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content"> 
	      <div class="modal-body text-center">
	        <a href="#"  class="title-text" id="deleteBtn"><b>삭제하기</b></a>
	      </div>      
	    </div>
	  </div>
	</div>
	
	<script>
	// 라이크 함수 생성
	function processLike(postId){
		 <!--빈하트 클릭했을때-->
		 if($("#heartIcon-"+postId).hasClass("bi-suit-heart")){
			
		 $.ajax({
			type:"get",
			url:"/marondalgram/post/like",
			data:{"postId":postId},
			success:function(data){
					// 좋아요
					if(data.likeList == "success"){
						if($("#heartIcon-"+postId).hasClass("bi-suit-heart")){
							$("#heartIcon-"+postId).removeClass("bi-suit-heart");
							$("#heartIcon-"+postId).addClass("bi-suit-heart-fill");
							
							$("#heartIcon-"+postId).removeClass("title-text");
							$("#heartIcon-"+postId).addClass("text-danger");
			
					}else{
						alert("좋아요 입력 실패");
						}
					}	
					 $("#likeCount-"+postId).text(data.likeCount);
			},
			error:function(e){
				alert("error");
			}
		 });
		 
		 <!-- 꽉찬 하트 클릭했을때 -->
		 }else if($("#heartIcon-"+postId).hasClass("bi-suit-heart-fill")){
			 $.ajax({
				 type:"get",
				 url:"/marondalgram/post/dislike",
				 data:{"postId": postId},
				 success:function(data){
					 if(data.dislikeList == "success"){
						 if($("#heartIcon-"+postId).hasClass("bi-suit-heart-fill")){
								$("#heartIcon-"+postId).removeClass("bi-suit-heart-fill");
								$("#heartIcon-"+postId).addClass("bi-suit-heart");
								
								$("#heartIcon-"+postId).removeClass("text-danger");
								$("#heartIcon-"+postId).addClass("title-text");
					
						}else{
							alert("좋아요 취소 실패");
						}
					 }
					 $("#likeCount-"+postId).text(data.likeCount);
				 },
				 error:function(e){
					 alert("error");
				 }
			 });
			
		 }
	 
	}
	
	// 로그인이 되어있지 않은 사용자일때 벨리데이션 함수
	var userId = "${userId}";
	function processCheckLogin(userId){
		
		if(userId == null || userId == ""){
			alert("로그인 후 이용가능합니다.");
			location.href = "/marondalgram/user/signin_view";
			return;
		}
			
	}
	
	 $(document).ready(function(){ 
		 		 
		// 포스팅 저장 
		 $("#saveBtn").on("click", function(){
		 let content = $("#contentInput").val().trim();
		 
		  if(content == null || content ==""){
			  alert("내용을 입력해주세요");
			  return;
		  }
		  // 사진 필수 밸리데이션
		  if($("#fileInput")[0].files.length == 0 ){
			  alert("파일을 선택해 주세요");
			  return;
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
					  location.reload();
				  }else{
					  alert("포스팅 실패");
				  }
			  },
			  error:function(e){
				 alert("error"); 
			  }
		  });
		 
		 });
		 
		 
		 // 코멘트 저장
		 $(".commentBtn").on("click",function(e){
			 
			e.preventDefault();
			 var postId = $(this).data("post-id");
			 var comment = $("#commentInput-"+ postId).val().trim(); 
			 
			 processCheckLogin(userId);
			 
			 if(comment == null || comment ==""){
				 alert("코멘트를 입력하세요");
				 return;
			 }
			 
			 
			 $.ajax({
				type:"get",
				url:"/marondalgram/post/comment/create",
				data:{"postId":postId, "content":comment},
				success:function(data){
					
					if(data.result == "success"){
						alert("코멘트 입력 성공");
						location.href="/marondalgram/post/timeline";	
					}else{
						alert("코멘트 입력 실패");
					}
	
				},
				error:function(e){
					alert("코멘트 입력 실패");
				}
			 });
		 });
		 
		 // 코멘트 삭제
		 $(".deleteCommentBtn").on("click",function(){
			 var commentId = $(this).data("comment-id");
			 $.ajax({
					type:"get",
					url:"/marondalgram/post/comment/delete",
					data:{"id":commentId},
					success:function(data){
						
						if(data.result == "success"){
							alert("코멘트 삭제 성공");
							location.href="/marondalgram/post/timeline";	
						}else{
							alert("코멘트 삭제 실패");
						}
		
					},
					error:function(e){
						alert("error");
					}
				 });
		 });
		 
		 // 파일 인풋 이미지 클릭 이벤트
		 $("#imageUploadBtn").on("click",function(){
			 $("#fileInput").click();
		 });
		 
		 // 좋아요
		 $(".likeBtn").on("click",function(e){	
			 e.preventDefault();
			 var postId = $(this).data("post-id");	
			 
			 processCheckLogin(userId);
			 
			 processLike(postId);
			 
		 });
		 
		 // 이미지 더블클릭했을때도 라이크 온&오프
		 $(".image-thumbnail").on("dblclick", function(){
			 var postId = $(this).data("post-id");
			 
			 processCheckLogin(userId);
			 processLike(postId);
		 });
		 
		 // 포스팅 삭제(모달은 하나만 만들고, 모어버튼 클릭시에 모달에 postId를 주입한다.)
		 $(".moreBtn").on("click",function(){
			 var postId = $(this).data("post-id");
 			 // 모달에 postId 값을 주입한다.
			 $("#deleteBtn").data("post-id", postId);		 
		 });
		 
		 //모달의 a태그의 클릭 이벤트를 만들고, 그 안에서 post-id로 삭제를 진행한다.
		 $("#deleteBtn").on("click", function(e){
			 e.preventDefault();
			var postId = $(this).data("post-id"); 
			
			 $.ajax({
					type:"get",
					url:"/marondalgram/post/delete",
					data:{"postId":postId},
					success:function(data){
						if(data.result == "success"){
							location.reload();
						}else{
							alert("삭제 실패");
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