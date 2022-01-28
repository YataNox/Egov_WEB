<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<link href="<c:url value='/css/shopping.css'/>" rel="stylesheet">
		<script src="<c:url value='/script/member.js'/>"></script>
		<script src="<c:url value='/script/mypage.js'/>"></script>
	</head>
	<body>
		<div id="wrap">
			<header>
				<!-- 로고, 주메뉴, 카테고리 메뉴 등이 표시되는 영역 -->
	
				<div id="logo">
					<!-- 메인로고 시작 -->
					<a href="/main.do"> <img src="<c:url value='/images/logo.png'/>" width="180" height="100" />
					</a>
				</div>
				<!-- 메인로고 끝 -->
				
				<nav id="top_menu">
					<!-- 상단 메뉴 시작 : 로그인 CART MyPage 등-->
					<ul>
						<c:choose>
							<c:when test="${empty loginUser}">
								<li><a href="loginForm.do">LOGIN</a></li>
								<li><a href="contract.do">JOIN</a></li>
							</c:when>
							<c:otherwise>
								<li style="color:yellow; font-weight:bold; font-size: 110%">${loginUser.NAME}(${loginUser.ID})</li>
								<li><a href="memberEditForm.do">정보수정</a></li>
								<li><a href="logout.do">LogOut</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="cartList.do">CART</a></li>
						<li><a href="myPage.do">MY PAGE</a></li>
						<li><a href="qnaList.do" style="border:0px">Q&amp;A (1:1)</a></li>
					</ul>
				</nav>
				<!-- 상단 메뉴 끝 -->
				
				<nav id="catagory_menu">
					<!-- 카테고리 메뉴 시작 Heels Boots Sandals 등-->
					<ul>
						<li><a href="category.do?kind=1">Heels</a></li>
						<li><a href="category.do?kind=2">Boots</a></li>
						<li><a href="category.do?kind=3">Sandals</a></li>
						<li><a href="category.do?kind=4">Sneakers</a></li>
						<li><a href="category.do?kind=5">Sleeper</a></li>
						<li><a href="category.do?kind=6">On Sale</a></li>
					</ul>
				</nav>
				<!-- 카테고리 메뉴 끝 -->
			</header>