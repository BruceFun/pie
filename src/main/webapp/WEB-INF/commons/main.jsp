<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commons/tags.jsp"%>

<!-- Bootstrap 样式-->
<link href="/commons/css/bootstrap.min.css" rel="stylesheet">
<link href="/commons/css/bootstrap-select.min.css" rel="stylesheet">
<!-- Bootstrap 样式增强（阴影、渐变等）-->
<link href="/commons/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/commons/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="/commons/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="/commons/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<style>
.container_top_margin {
	margin-top: 50px;
}
</style>

<!-- jQuery库 -->
<script src="/commons/js/jquery-1.11.1.min.js"></script>
<!-- bootstrap插件 -->
<script src="/commons/js/bootstrap.min.js"></script>
<script src="/commons/js/bootstrap-select.js"></script>
<script src="/commons/js/bootstrapValidator.min.js"></script>
<script src="/commons/js/bootstrap-datetimepicker.js"></script>
<script src="/commons/js/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/commons/js/ueditor.config.js"></script>
<script src="/commons/js/ueditor.all.min.js"></script>
<script src="/commons/js/pie_common.js"></script>

<div id="navbar-main">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon icon-shield" style="font-size: 30px; color: #3498db;"></span>
				</button>
				<a class="navbar-brand hidden-xs hidden-sm" href="#home">
					<span class="icon icon-shield" style="font-size: 18px; color: #3498db;"></span>
				</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/main/index" class="smoothScroll"> Home </a></li>
					<li><a href="/ie/expenses" class="smoothScroll"> PE </a></li>
					<li><a href="/ie/income" class="smoothScroll"> PI </a></li>
					<li><a href="/ie/ieDetails" class="smoothScroll"> PIED </a></li>
					<li><a href="#portfolio" class="smoothScroll"> ing……</a></li>
					<li><a href="/schedulerJob/quartz" class="smoothScroll"> Quartz</a></li>
					<li><a href="#contact" class="smoothScroll"> 联系我们</a></li>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
