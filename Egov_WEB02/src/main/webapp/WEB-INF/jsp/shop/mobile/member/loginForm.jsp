<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/headerfooter/header.jsp"%>
<%@ include file="../include/sub01/sub_image.html"%>
<%@ include file="../include/sub01/sub_menu.html"%>

<article>
	<form method="post" action="mlogin.do" name="loginFrm">
		<table align="center" width="300">
			<tr height="50">
				<th width="80">ID</th>
				<td width="220"><input type="text" name="id"></td>
			</tr>
			<tr height="50">
				<th width="80">PASSWORD	</th>
				<td width="220"><input type="password" name="pwd"></td>
			</tr>
		</table>
		<div id="buttons">
			<input type="submit" value="로그인" class="submit">
			<input type="button" value="회원가입" class="cancel" onclick="location.href='mcontract.do'">
			<input type="button" value="아이디 비밀번호 찾기" class="submit" onclick="find_id()'">
		</div>
		<br>${message}<br>
	</form>
</article>

<%@ include file="../include/headerfooter/footer.jsp"%>
