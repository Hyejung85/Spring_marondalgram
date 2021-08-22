<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="d-flex justify-content-between align-items-center">
		<h2 class="ml-3">Marondalgram</h2>
		<c:if test="${not empty userName }">
			<div class="mr-3"><b>${userName }님</b> <a href="/marondalgram/user/sign_out">로그아웃</a></div>
		</c:if>
</header>