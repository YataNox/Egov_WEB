<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<link rel="stylesheet" href="admin/css/admin.css">
		<script type="text/javascript" src="admin/product/product.js"></script>
	</head>
	<body>
		<div id="wrap">
			<header>
				<div id="logo">
					<img style="width: 800px" src="admin/images/bar_01.gif">
					<img src="admin/images/text.gif">
					<input type="button" class="btn" value="logout" style="float: right;"
					onclick="location.href='shop.do?command=adminLogout'">
				</div>
			</header>
			<div class="clear"></div>
