<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>checkSuccess</title>
	</head>
	<body>
		<script type="text/javascript">
			if(window.name=="update"){
				window.opener.location.href="boardUpdateForm.do?num=${param.num}";
			}else if(window.name=="delete"){
				var answer = confirm("정말로 삭제할까요?");
				if(answer)
					window.opener.location.href="boardDelete.do?num=${param.num}";
			}
			self.close();
			
			// if문의 경우의 수 모두 수정-삭제 동작에 해당하는 command 루틴을 생성하여 실행되게 합니다.
			// 모든 작업을 마치면 boardlist로 이동합니다.
		</script>
	</body>
</html>