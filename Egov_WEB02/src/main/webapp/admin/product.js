function go_wrt(){
	document.frm.action = "productWriteForm.do";
	document.frm.submit();
}

function go_mov(){
	location.href="productList.do";
}

function go_save(){
	var theForm = document.frm;
	if(theForm.kind.value==""){
		alert("상품분류를 선택하세요.");
		theForm.kind.focus();
	}else if(theForm.name.value==""){
		alert("상품명을 입력하세요.");
		theForm.name.focus();
	}else if(theForm.price1.value==""){
		alert("원가를 입력하세요");
		theForm.price1.focus();
	}else if(theForm.price2.value==""){
		alert("판매가를 입력하세요");
		theForm.price2.focus();
	}else if(theForm.content.value==""){
		alert("상품상세를 입력하세요");
		theForm.content.focus();
	}else if(theForm.image.value==""){
		alert("상품이미지를 입력하세요");
		theForm.image.focus();
	}else{
		theForm.action = "productWrite.do";
		theForm.submit();
	}
}

function go_detail(pseq){
	var url = "adminProductDetail.do?pseq=" + pseq;
	document.frm.action = url;
	document.frm.submit();
}

function go_mod(pseq){
	var url = "productUpdateForm.do?pseq=" + pseq;
	location.href=url;
}

function go_mod_save(){
	if(document.frm.bestyn.checked == true){
		document.frm.bestyn.value = "y";
	}
	
	if(document.frm.useyn.checked == true){
		document.frm.useyn.value = "y";
	}
	
	if(document.frm.kind.value==""){
		alert("상품분류를 선택하세요.");
		document.frm.kind.focus();
	}else if(document.frm.name.value==""){
		alert("상품명을 선택하세요.");
		document.frm.name.focus();
	}else if(document.frm.price1.value==""){
		alert("원가를 선택하세요.");
		document.frm.price1.focus();
	}else if(document.frm.price2.value==""){
		alert("판매가를 선택하세요.");
		document.frm.price2.focus();
	}else if(document.frm.content.value==""){
		alert("상품상세를 선택하세요.");
		document.frm.content.focus();
	}else{
		if(confirm('수정하시겠습니까?')){
			document.frm.action = "productUpdate.do";
			document.frm.submit();
		}
	}
}

function go_search(){
	if(document.frm.key.value=="")
		return;
		
	var url = "productList.do?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total(){
	document.frm.key.value="";
	document.frm.action = "productList.do?page=1";
	document.frm.submit();
}

function go_search_order(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminOrderList.do?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total_order(){
	document.frm.key.value="";
	document.frm.action = "adminOrderList.do?page=1";
	document.frm.submit();
}

function go_order_save(){
	var count = 0;
	if(document.frm.result.length == undefined){
		if(document.frm.result.checked == true)
			count++;
	}else{
		for(var i = 0; i < document.frm.result.length; i++){
			if(document.frm.result[i].checked == true)
				count++;
		}
	}
	
	if(count == 0){
		alert("주문처리할 항목을 선택해 주세요.")
	}else{
		document.frm.action = "adminOrderSave.do";
		document.frm.submit();
	}
}

function go_search_qna(){
	if(document.frm.key.value=="")
		return;
		
	var url = "adminQnaList.do?page=1";
	document.frm.action = url;
	document.frm.submit();
}

function go_total_qna(){
	document.frm.key.value="";
	document.frm.action = "adminQnaList.do?page=1";
	document.frm.submit();
}

function go_view(qseq){
	location.href = "adminQnaDetail.do?qseq=" + qseq;
}

function go_rep(){
	document.frm.action="adminQnaRepsave.do";
	document.frm.submit();
}

function go_search_member(){
	if(document.frm.key.value=="")
		return;
		
	var url = "memberList.do?page=1";
	// 보던 페이지가 어떤 페이지이던간에 검색 결과의 1페이지로 가기위해 파라미터 page를 1로 전송
	document.frm.action = url;
	document.frm.submit();
}

function go_total_member(){
	document.frm.key.value="";
	document.frm.action = "memberList.do?page=1";
	document.frm.submit();
}

function selectimg(){
	var opt = "toolbar=no, menubar=no, resizable=no, width=450, height=200";
	window.open('selectimg.do', 'selectimg', opt);
}
