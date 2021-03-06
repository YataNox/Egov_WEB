<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp" %>
<%@ include file="../../include/sub05/sub_menu.jsp"%>

<article>
	<h1>상품 상세 보기</h1>
	<table id="list">
		<tr>
			<th>상품분류</th>
			<td colspan="5">${kind}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="5">${productVO.NAME}</td>
		</tr>
		<tr>
			<th>원가[A]</th><td width="60">${productVO.PRICE1}</td>
			<th>판매가[B]</th><td width="60">${productVO.PRICE2}</td>
			<th>[B-A]</th><td>${productVO.PRICE3}</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td colspan="5"><pre>${productVO.CONTENT}</pre></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="5" align="center">
				<c:if test="${not empty productVO.IMAGE}">
					<img src="product_images/${productVO.IMAGE}" width="200px">
				</c:if>
			</td>
		</tr>
	</table>
	<input type="button" class="btn" value="수정" onclick="go_mod('${productVO.PSEQ}')">
	<input type="button" class="btn" value="목록" onclick="go_mov()">
</article>

<%@ include file="../../include/admin/footer.jsp"%>