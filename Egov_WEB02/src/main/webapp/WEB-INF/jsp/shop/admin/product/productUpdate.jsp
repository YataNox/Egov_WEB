<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp" %>
<%@ include file="../../include/sub05/sub_menu.jsp"%>

<article>
	<h1>상품 수정${productVO.KIND}</h1>
	<form name="frm" method="post">
		<input type="hidden" name="pseq" value="${productVO.PSEQ}">
		<input type="hidden" name="code">
		<input type="hidden" name="oldImage" value="${productVO.IMAGE}">
		<table id="list">
			<tr>
				<th>상품분류</th>
				<td colspan="5">
					<select name="kind">
						<c:forEach items="${kindList}" var="kind" varStatus="status">
							<c:choose>
								<c:when test="${productVO.KIND==status.count}">
									<option value="${status.count}" selected="selected">${kind}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.count}">${kind}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5">
					<input type="text" name="name" size="47" maxlength="10-" value="${productVO.NAME}">
				</td>
			</tr>
			
			<tr>
				<th>원가[A]</th>
				<td width="70">
					<input type="text" name="price1" size="11" value="${productVO.PRICE1}">
				</td>
				
				<th>판매가[B]</th>
				<td width="70">
					<input type="text" name="price2" size="11" value="${productVO.PRICE2}">
				</td>
				
				<th>[B-A]</th>
				<td width="72">
					<input type="text" name="price3" size="11" value="${productVO.PRICE2 - productVO.PRICE1}">
				</td>
			</tr>
			
			<tr>
				<th>베스트상품</th>
				<td>
					<c:choose>
						<c:when test='${productVO.BESTYN == "y" }'>
							<input type="checkbox" name="bestyn" value="y" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="bestyn" value="n">
						</c:otherwise>
					</c:choose>
				</td>
				<th>사용유무</th>
				<td>
					<c:choose>
						<c:when test='${productVO.USEYN == "y" }'>
							<input type="checkbox" name="useyn" value="y" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="useyn" value="n">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5">
					<textarea rows="8" cols="70" name="content">${productVO.CONTENT}</textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="5">
					<div id="filename">
						<c:choose>
							<c:when test="${not empty productVO.IMAGE}">
								<img src="<c:url value='/product_images/${productVO.IMAGE}'/>" width="200pt">
							</c:when>
							<c:otherwise>
								이미지가 없습니다.
							</c:otherwise>
						</c:choose>
					</div><br>
					<%-- <input type="hidden" name="imgfilename" value="${productVO.IMAGE}">
					<input type="button" value="파일선택" onclick="selectimg();"> --%>
				</td>
			</tr>
			<tr>
				<td height="30">
					<div id="filename"></div>
					<input type="hidden" id="img" name="image">
					<input type="hidden" name="oldimage" value="${productVO.IMAGE}">
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="수정" onclick="go_mod_save()">
		<input type="button" class="btn" value="취소" onclick=
		"location.href='adminProductDetail.do?pseq=${productVO.PSEQ}'">
	</form>
	
	<div style="position: relative; top: -80px; width: 500px; margin: 0 auto;">
		<form name="formm" id="fileupForm" method="post" enctype="multipart/form-data">
			<input type="file" name="image1"> 이미지를 변경할 때만 사용하세요.
			<input type="button" id="myButton" value="변경">
		</form>
	</div>
</article>

<%@ include file="../../include/admin/footer.jsp"%>