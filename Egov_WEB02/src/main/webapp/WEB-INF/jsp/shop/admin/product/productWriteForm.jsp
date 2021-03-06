<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp" %>
<%@ include file="../../include/sub05/sub_menu.jsp"%>

<article>
	<form name="frm" method="post">
		<table id="list">
			<tr>
				<th>상품분류</th>
				<td colspan="5">
					<select name="kind">
						<option value="">선택</option>
						<c:forEach items="${kindList}" var="kind" varStatus="status">
							<option value="${status.count}">${kind}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5">
					<input type="text" name="name" size="47" maxlength="100">
				</td>
			</tr>
			<tr>
				<th>원가[A]</th>
				<td width="70"><input type="text" name="price1" size="11"></td>
			
				<th>판매가[B]</th>
				<td width="70"><input type="text" name="price2" size="11"></td>
				
				<th>[B-A]</th>
				<td width="72"><input type="text" name="price3" size="11"></td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5">
					<textarea rows="8" cols="70" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="5" width="343">
					<div id="filename"></div>
					<input type="text" id="image" name="image">
					<!-- <img alt="사진을 등록해주세요." width="80" id="updateimage"><br>
					<input type="text" name="image" disabled>
					<input type="hidden" name="imgfilename">
					<input type="button" value="파일선택" onclick="selectimg();"> -->
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="등록" onclick="go_save()">
		<input type="button" class="btn" value="목록" onclick="go_mov()">
	</form>
	<div style="position: relative; top: -80px; left: -95px; width: 300px; margin: 0 auto;">
		<form name="formm" id="fileupForm" method="post" enctype="multipart/form-data" style="float: left;">
			<input type="file" name="image1">
			<input type="button" id="myButton" value="추가">
		</form>
	</div>
</article>

<%@ include file="../../include/admin/footer.jsp"%>