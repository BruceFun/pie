<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PIE</title>

</head>
<body>
	<%@ include file="/WEB-INF/commons/main.jsp" %>
    <div class="container" id="about" name="about">
		<div class="row white">
			<br>
			<br>
			<h1 class="centered"> 首页（正在努力开发中）  </h1>
			<hr>
			
			<input type="text" id="a" />
			<input type="text" id="bd" />
			<input type="text" id="cc" />
			<input type="text" id="ed" />
			<input type="text" id="21" />
			<input type="text" id="75" />
			<input type="text" id="e53" />
			<input type="text" id="45678" />
			<input type="text" id="3455" style="display: none;" />
			
			<input type="button" value="测试 " onclick="testInput()" >
			
			
		</div><!-- row -->
	</div><!-- container -->
</body>
<script type="text/javascript">
	function testInput(){
		$("input").each(function(index,element){
			var idValue = $(element).attr("id");
			if(idValue>0){
				$(element).val(idValue);
				console.debug(element);
			}
			
		});
		
	}
</script>
</html>