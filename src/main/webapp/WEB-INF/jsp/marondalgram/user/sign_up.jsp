<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Marondalgram - 회원가입</title>
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
		<section class="sign-section d-flex justify-content-center">
		 <div class="section-box d-flex my-5 border rounded">
			<div class="img-box border rounded">
				<img class="image-thumbnail" id="image-thumbnail" src="https://cdn.pixabay.com/photo/2017/05/15/16/58/blue-2315434_960_720.jpg">
			</div>
			<div class="d-flex justify-content-center align-items-center border rounded">
			 <div class="h-100 w-100">
				<div class="signup-box h-75 d-flex justify-content-center align-items-center">
					 <div class="w-75">
						<h1 class="text-center mt-3">Sign Up</h1>
						<form method="post" action="/marondalgram/user/sign_up" id="signupForm">
						<div class="d-flex mt-3">
							<input type="text" class="form-control mt-3" placeholder="아이디" id="loginIdInput" name="loginId">
							<button type="button" class="btn btn-sm mt-3 ml-2 text-white" id="idDuplicateBtn">중복확인</button>
						</div>
						<div class="text-success d-none" id="noneDuplicateDiv">
							<small>사용 가능한 아이디입니다.</small>
						</div>
						<div class="text-danger d-none" id="duplicateDiv">
							<small>중복된 아이디입니다.</small>
						</div>
						<input type="password" class="form-control mt-2" placeholder="비밀번호" id="passwordInput" name="password">
						<input type="password" class="form-control mt-2" placeholder="비밀번호 확인" id="passwordCheckInput">
						<div id="errorPassword" class="text-danger d-none"><small>비밀번호가 일치하지 않습니다.</small></div>
						<input type="text" class="form-control mt-2" placeholder="이름" id="nameInput" name="name">
						<input type="text" class="form-control mt-2" placeholder="이메일" id="emailInput" name="email">
					</div>
			    </div>
					<div class="sub-box d-flex justify-content-center w-100 h-25">
						<button type="submit" class="btn btn-block text-white mt-3 w-75 h-50" id="signupBtn">가입하기</button>
					</div>
				</form>
			 </div>
			</div>
		 </div>
		</section>
		<c:import url="/WEB-INF/jsp/marondalgram/include/footer.jsp" />
	</div>
	<script>
		$(document).ready(function(){
			
			var bannerList = ["https://cdn.pixabay.com/photo/2017/05/15/16/58/blue-2315434_960_720.jpg"
				,"https://cdn.pixabay.com/photo/2017/10/07/11/04/dadaepo-beach-2826171_960_720.jpg"
				,"https://cdn.pixabay.com/photo/2017/03/27/14/49/beach-2179183_960_720.jpg"
				,"https://cdn.pixabay.com/photo/2018/02/08/22/27/flower-3140492_960_720.jpg"]
			var currentImageIndex = 0;
			setInterval(function(){
				$("#image-thumbnail").attr("src", bannerList[currentImageIndex]);
				currentImageIndex++;
				
				if(currentImageIndex == bannerList.length){
					currentImageIndex = 0;
				}
			}, 3000);
			
			var isIdCheck = false;
			var isDuplicate = true;
			
			$("#signupForm").on("submit",function(e){
				
				e.preventDefault();
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				var passwordCheck = $("#passwordCheckInput").val();
				var name = $("#nameInput").val().trim();
				var email = $("#emailInput").val().trim();
				
				if(loginId == null || loginId == ""){
					alert("아이디를 입력해주세요");
					return;
				}
				if(password == null || password ==""){
					alert("비밀번호를 입력해주세요");
					return;
				}
				
				if(password != passwordCheck){
					$("#errorPassword").removeClass("d-none");
				
				}
				
				if(name == null || name == ""){
					alert("이름을 입력해주세요");
					return;
				}
				if(email == null || email == ""){
					alert("이메일을 입력해주세요");
					return;
				}
				
				
				
				// 아이디 중복체크를 했는지?
				if(isIdCheck == false){
					alert("아이디 중복체크를 진행해주세요");
				}
				
				// 아이디가 중복되었는지, 되지 않았는지?
				if(isDuplicate == true){
					alert("아이디가 중복되었습니다");
				}
				
							
				$.ajax({
					type:"post",
					url:"/marondalgram/user/sign_up",
					data:{"loginId":loginId, "password":password, "name":name, "email": email},
					success:function(data){
						if(data.result == "success"){
							alert("회원가입성공! 로그인 화면으로 이동합니다.");
							location.href = "/marondalgram/user/signin_view";
						}else{
							alert("회원가입 실패");
						}
					},
					error:function(e){
						alert("회원가입 실패");
					}
		
				});
				return false;
			});
			
			$("#idDuplicateBtn").on("click",function(){
				var loginId = $("#loginIdInput").val();
				
				if(loginId == null || loginId ==""){
					alert("아이디를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"get",
					url:"/marondalgram/user/is_duplicate_id",
					data:{"loginId":loginId},
					success:function(data){
						isIdCheck = true;
						
						if(data.is_duplicate){
							isDuplicate = true;
							$("#duplicateDiv").removeClass("d-none");
							$("#noneDuplicateDiv").addClass("d-none");
						}else{
							isDuplicate = false;	
							$("#duplicateDiv").addClass("d-none");
							$("#noneDuplicateDiv").removeClass("d-none");
						}
					},
					error:function(e){
						alert("아이디 중복확인 실패!!");
					}
					
				});
			});
			
		});
	</script>
</body>
</html>