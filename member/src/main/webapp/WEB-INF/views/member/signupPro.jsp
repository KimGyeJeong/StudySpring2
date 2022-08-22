<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signupPro</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>signupPro Page</h1>
	<c:if test="${result==1 }">
		<div>
			<p>result : ${result }</p>
			<p>회원가입 성공!</p>
			<button onclick="window.location-'/member/main'">Go Main</button>
		</div>
	</c:if>
	<c:if test="${result!=1 }">
		<script type="text/javascript">
			alert("회원가입이 정상적으로 처리되지 않았습니다. 다시 시도해주세요...");
			history.go(-1);
		</script>
	</c:if>


</body>
</html>