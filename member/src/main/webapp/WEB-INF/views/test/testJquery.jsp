<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testJquery</title>

<!-- 미리 불러와야하는 jquery, ajax 는 위에서 불러오기 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--  
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
-->

<style type="text/css">
.testli {
	color: yellow;
}
</style>
</head>
<body>
	<h1>testJquery Page</h1>

	<!-- JS로 버튼 클릭하기 -->
	<div>
		<button class="btn" onclick="btnClick()">ButtonJS</button>

		<script>
			function btnClick() {
				alert("Click!");
			}
		</script>
	</div>

	<!-- 모든 버튼 클릭시 이벤트 발생 -->
	<div>
		<button class="btn2">Button2</button>

		<script>
			/* 잠시 죽이기
				$(document).ready(function() {
					//$("button") 으로 하면 위의 button도 똑같이 적용됨. click  --> jquery event alert 순서로 작동.
					$("button").click(function() {
						alert("jQuery Event alert!");
					})
				})
			 */
		</script>
	</div>

	<!-- 클래스명 사용해서 이벤트 발생 -->
	<div>
		<button class="btn3">Button3</button>
		<script>
			$(document).ready(function() {
				//여기서는 37번째줄 alert 실행후 50번째줄 alert 실행
				$(".btn3").click(function() {
					alert("button3 Click");
				})
			})
		</script>
	</div>

	<!--  -->
	<div>
		<h3>Hello jquery</h3>
		<h3 id="h3id">id selector</h3>
		<!-- 마지막에 주었던 클래스 속성 변경되어서 color green 출력 -->
		<h3 id="h3id" class="cls1">id2 selector</h3>
		<h3 class="cls1">class selector</h3>

		<script>
			$(document).ready(function() {
				$("h3").css("color", "red");
				$("#h3id").css("color", "blue");
				$(".cls1").css("color", "green");
			})
		</script>
	</div>

	<!--  -->
	<div>
		<ul>
			<li>1111</li>
			<li>2222</li>
			<li>3333</li>
			<li>4444</li>
		</ul>
		<script>
			$(document).ready(function() {

				//2번째줄 222 만 색 변환. 동적으로 처리
				$("li:nth-child(2)").css("color", "magenta");

				//li태그 속성 주기.
				$("li").attr("class", "testli");
				//모든 li태그들에 속성명이 붙지만 1,3,4 에만 color yellow 적용
				//
			})
		</script>

	</div>

	<!--  -->
	<div>
		<img alt="" src="/resources/imgs/고양이.jpg" width="400">
		<button class="imgChange">Change Img</button>
		<script>
			$(document).ready(function() {

				$(".imgChange").click(function() {
					$("img").attr("src", "/resources/imgs/고양이6.jpg");
				})
			})
		</script>
	</div>

	<!--  -->
	<div>
		<h3 class="changeH3">입력값확인</h3>
		<input type="text" name="id" id="id">
		<button class="inputChange">Change h3Tag</button>

		<script>
			$(document).ready(function() {
				$(".inputChange").click(function() {
					//현재 작성된 값 alert 로 보여주기
					alert($("#id").val());
					//$(".changeH3").text($("#id").val());

					//h3태그안의 글을 확인완료! 수정후 input 태그 안 삭제
					$(".changeH3").text("확인완료!");
					$("#id").val("");
				})
			})
		</script>
	</div>

	<!--  -->
	<div>
		<h2 class="changeSelect">SELECT</h2>
		<select>
			<option>java</option>
			<option>jsp</option>
			<option>framework</option>
			<option>project</option>
			<option>JS</option>
		</select> 
		<select>
			<option>C</option>
			<option>C++</option>
			<option>C#</option>
		</select>
		<script>
			$(document).ready(function() {
				$("select").change(function(){
					//this를 쓰는 경우
					//여러 select 가 있을때 이벤트가 발생된 아이를 선택하기 위해 this를 사용
					alert($(this));
					console.log($(this));
					
					//2번째 select의 C를 누를경우 h2태그안의 값이 변경됨
					$(".changeSelect").text($(this).val());
				})
			})
		</script>
	</div>
	



</body>
</html>