<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginPro</title>
</head>
<body>

	<h1>loginPro</h1>

	<div>
		result : ${result }

		<c:if test="${result == 0 }">
			<script type="text/javascript">
				alert("id 또는 pw가 일치하지 않습니다. 다시 시도해주세요.");
				history.go(-1);
			</script>
		</c:if>
	</div>

</body>
</html>