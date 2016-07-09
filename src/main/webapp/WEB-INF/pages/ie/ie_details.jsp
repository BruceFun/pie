<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PIED</title>
<!-- 引用CSS样式 -->
<link href="/commons/css/bootstrap-table.min.css" rel="stylesheet">

<!-- 引用JS脚本 -->
<script src="/commons/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/commons/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="/commons/js/locale/bootstrap-table.zh-CN.min.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="/WEB-INF/commons/main.jsp"%>
	<div class="container container_top_margin" >
		<h1 class="centered">人个收入、支出明细</h1>
		<hr>
		<div id="detailsTable">
		</div>
		
	</div>
	<!-- container -->
	<div class="modal" id="ie_edit_container">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					<strong>编辑</strong> 
				</div>
				<div class="modal-body" id="ie_modal_body"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 点击明细时，默认加载数据
	$(function(){
		$("#detailsTable").load("/ie/getPageByQuery");
	});
</script>
</html>
