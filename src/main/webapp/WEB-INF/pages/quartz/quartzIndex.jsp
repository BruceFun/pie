<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>quartz</title>

</head>
<body>
	<%@ include file="/WEB-INF/commons/main.jsp"%>
	<div class="container container_top_margin" >
		<h1 class="centered">定时任务测试</h1>
		<div type="button" class="btn btn-success" onclick="runAllJobs()">运行所有的Job</div>
		<div type="button" class="btn btn-primary" onclick="getAllJobs()"> 得到所有的Job</div>
		<hr>
		
		暂停：
		<div type="button" class="btn btn-warning" onclick="pauseJob('402880a0544b56a601544b56bacc0000')">暂停任务一</div>
		<div type="button" class="btn btn-warning" onclick="pauseJob('402880a0544b57da01544b57ec4f0000')">暂停任务二</div>
		<div type="button" class="btn btn-warning" onclick="pauseJob('402880a0544b58d801544b58e9cc0000')">暂停任务三</div>
		<hr>
		
		恢复：
		<div type="button" class="btn btn-info" onclick="resumeJob('402880a0544b56a601544b56bacc0000')">恢复任务一</div>
		<div type="button" class="btn btn-info" onclick="resumeJob('402880a0544b57da01544b57ec4f0000')">恢复任务二</div>
		<div type="button" class="btn btn-info" onclick="resumeJob('402880a0544b58d801544b58e9cc0000')">恢复任务三</div>
		<hr>
		
		删除：
		<div type="button" class="btn btn-danger" onclick="deleteJob('402880a0544b56a601544b56bacc0000')">删除任务一</div>
		<div type="button" class="btn btn-danger" onclick="deleteJob('402880a0544b57da01544b57ec4f0000')">删除任务二</div>
		<div type="button" class="btn btn-danger" onclick="deleteJob('402880a0544b58d801544b58e9cc0000')">删除任务三</div>
		<hr>
		
		立即执行：
		<div type="button" class="btn btn-success" onclick="runAJobNow('402880a0544b56a601544b56bacc0000')">立即执行任务一</div>
		<div type="button" class="btn btn-success" onclick="runAJobNow('402880a0544b57da01544b57ec4f0000')">立即执行任务二</div>
		<div type="button" class="btn btn-success" onclick="runAJobNow('402880a0544b58d801544b58e9cc0000')">立即执行任务三</div>
		<hr>
		
		用户类业务测试
		<div type="button" class="btn btn-success" onclick="userBusinessTest('402882f15405e346015405e356670002')">添加用户类业务测试</div>
		
		
	</div>
	<!-- container -->
	
<script type="text/javascript">
	// 运行所有的Job
	function runAllJobs(){
		$.post("/schedulerJob/runAllJob",{},function(re_data){
			data = re_data.data;
			console.debug(data);
		},"json");
	}
	
	// 得到所有的Job
	function getAllJobs(){
		$.post("/schedulerJob/getAllJob",{},function(re_data){
			data = re_data.data;
			console.debug(data);
		},"json");
	}
	
	// 暂停一个任务
	function pauseJob(id){
		$.post("/schedulerJob/pauseJob",{"id":id},function(re_data){
			
		},"json");
	}
	
	// 恢复一个任务
	function resumeJob(id){
		$.post("/schedulerJob/resumeJob",{"id":id},function(re_data){
			
		},"json");
	}
	
	// 删除一个任务
	function deleteJob(id){
		$.post("/schedulerJob/deleteJob",{"id":id},function(re_data){
			
		},"json");
	}
	
	// 立即执行一个任务
	function runAJobNow(id){
		$.post("/schedulerJob/runAJobNow",{"id":id},function(re_data){
			
		},"json");
	}
	
	// 用户类业务测试
	function userBusinessTest(id){
		$.post("/schedulerJob/userBusiness",{"id":id},function(re_data){
			
		},"json");
	}
</script>
</body>
</html>
