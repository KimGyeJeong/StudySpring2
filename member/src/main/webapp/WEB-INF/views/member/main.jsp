<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Member Main Page</h1>


	<div>
		<br />
		<c:if test="${sessionScope.memId == null }">
			<table>
				<tr>
					<td><button onclick="window.location='/member/login'">로그인</button></td>
				</tr>
				<tr>
					<td><button onclick="window.location='/member/signup'">회원가입</button></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${sessionScope.memId != null }">
			<table>
				<tr>
					<td><button onclick="window.location='/member/logout'">로그아웃</button></td>
				</tr>
				<tr>
					<td><button onclick="window.location='/member/mypage'">마이페이지</button></td>
				</tr>
			</table>
		</c:if>
		<br /> <br />
		<div align="center">
			<img src="/resources/imgs/고양이.jpg" width="600" />
		</div>
		<br /> <br />

	</div>
</body>
</html>