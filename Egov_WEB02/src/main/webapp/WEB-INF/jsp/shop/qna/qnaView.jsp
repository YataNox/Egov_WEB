<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/headerfooter/header.jsp"%>
<%@ include file="../include/sub04/sub_image.html"%>
<%@ include file="../include/sub04/sub_menu.jsp"%>
<article>
	<h2>1:1 고객 게시판</h2>
	<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
	<form>
		<table>
			<tr>
				<th>제목</th>
				<td width="600" style="text-align: left;">${qnaVO.SUBJECT}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td align="left" style="text-align: left;">
					<fmt:formatDate value="${qnaVO.INDATE}" type="date" />
				</td>
			</tr>
			<tr>
				<th>질문내용</th>
				<td align="left" style="text-align: left; font-size: 115%;">
					<pre>${qnaVO.CONTENT}</pre>
				</td>
			</tr>
			<tr>
				<th>답변 내용</th>
				<td align="left" style="text-align: left; color: red;">${qnaVO.REPLY}</td>
			</tr>
		</table>
		<div class="clear"></div>
		<div id="buttons" style="float: right">
			<input type="button" value="목록보기" class="submit"
				onclick="location.href='qnaList.do'">
			<input type="button" value="쇼핑 계속하기" class="cancel"
				onclick="location.href='main.do'">
		</div>
	</form>
</article>
<%@ include file="../include/headerfooter/footer.jsp"%>
