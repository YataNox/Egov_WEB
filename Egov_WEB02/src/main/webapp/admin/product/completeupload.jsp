<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>completeupload</title>
		<script type="text/javascript">
			window.opener.frm.imgfilename.value="${image}";
			window.opener.frm.image.value="${image}";
			window.opener.document.getElementById("updateimage").src="/upload/${image}";
			self.close()
		</script>
	</head>
	<body>
	
	</body>
</html>