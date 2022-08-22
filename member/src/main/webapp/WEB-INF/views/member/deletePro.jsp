<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deletePro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>DELETE PRO PAGE</h1>

	<c:if test="${result!=1 }">
		<script type="text/javascript">
			alert("비밀번호가 일치하지 않습니다.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${result==1 }">
		<script type="text/javascript">
			alert("회원탈퇴 성공");
			window.location.href = "/member/main";
		</script>
	</c:if>

</body>
</html>