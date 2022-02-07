<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='admin/admin.css'/>">
<script type="text/javascript" src="<c:url value='admin/admin.js'/>"></script>
<script type="text/javascript" src="<c:url value='admin/product.js'/>"></script>
</head>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<img style="width: 800px" src="<c:url value='/admin/bar_01.gif'/>"> 
				<img src="<c:url value='/admin/text.gif'/>">
			</div>
			<input class="btn" type="button" value="logout" style="float: right;"
				onClick="location.href='adminLogout.do'">
		</header>
		<div class="clear"></div>