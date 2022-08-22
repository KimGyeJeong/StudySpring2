<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyPro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>ModifyPro page</h1>

	<div>
		<c:if test="${result==1 }">
			<script type="text/javascript">
				alert("수정완료");
				window.location.href="/member/main";
			</script>
		</c:if>
		<c:if test="${result==0 }">
			<script type="text/javascript">
				alert("pw가 일치하지않습니다. 다시 시도해주세요");
				history.go(-1);
			</script>
		</c:if>
	</div>

</body>
</html>