<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/admin/admin.css' /> ">
<script src="<c:url value='/admin/product.js' />" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
$(function(){

   $("#myButton").click( function () {
      var formselect = $("#fileupForm")[0];   // 바디에 있는 대상 폼을 변수에 저장
      var formdata = new FormData(formselect);   // 폼 안에 있는 데이터를 객체형으로 변환해서 변수에 저장
      
      // ajax : 웹페이지의 새로고침이 필요없는 요청
      $.ajax({
         url:"<%=request.getContextPath() %>/fileup.do",
         type:"POST",
         enctype:"multipart/form-data",
         async:false,
         data:formdata,
         contentType : false,
         processData : false,
         
         success : function(data){
            if( data.STATUS == 1){
	            //   alert("성공");
	            $("#filename").empty();
	            // $("#filename").append("<div>" + data.FILENAME + "</div>");
	            $("#filename").append(
	            	"<img src=\"<c:url value='/product_images/" + data.IMG + "'/>\" width='200'/>"
	           	);
	           	document.frm.image.value=data.IMG;
            }
         },
         error:function(){
            alert("실패");
         }
         
      });
      
   });   
   
});
</script>
</head>
<body>
<div id="wrap">
<header>         
   <div id="logo">
      <img style="width:800px" src="<c:url value='/admin/bar_01.gif' />" >
      <img src="<c:url value='/admin/text.gif' /> ">
   </div>   
   <input class="btn" type="button" value="logout" style="float: right;"
      onClick="location.href='adminLogout.do'">         
</header>
<div class="clear"></div>