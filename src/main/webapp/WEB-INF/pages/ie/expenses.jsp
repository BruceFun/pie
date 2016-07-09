<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PE</title>

<style type="text/css">
	#form_validator_expenses .has-feedback .form-control-feedback {
	    top: 0;
	    right: -15px;
	}
	#form_validator_expenses .has-feedback .input-group .form-control-feedback {
	    top: 0;
	    right: -30px;
	}
</style>

</head>
<body>
	<%@ include file="/WEB-INF/commons/main.jsp"%>
	<div class="container container_top_margin" >
		<h1 class="centered">人个支出</h1>
		<hr>
		
		<form id="form_validator_expenses" class="form-horizontal" action="" method="post">
			<input type="hidden" name="sign" value="2">
			<div class="form-group">
				<label class="col-sm-2 control-label">支出分类：</label>
				<div class="col-lg-2">
					<select class="selectpicker input-group" id="e_typeId" name="typeId" onchange="getDetailsByTypeId(this)">
						<option value="">--请选择--</option>
						<c:forEach items="${allExpensesTypes.data }" var="typeMap">
							<option value="${typeMap.id }">${typeMap.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-8">
					<select class="selectpicker input-group" id="e_typeDetailsId" name="typeDetailsId">
						<option value="">--请选择--</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">支出项目：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="e_item" name="item" placeholder="支出项目">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">支出金额：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="e_money" name="money" placeholder="支出金额">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">支出日期：</label>
				<div class="col-sm-5">
					<div class="input-group date form_datetime" id="div_expenses_datatime" data-date="">
					    <input class="form-control form_datetime" id="e_payTime" name="payTime" type="text" value="" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>   
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">附加信息：</label>
				<div class="col-sm-5">
					<div id="e_extraContent" name="extraContent" style="width: 100%;"></div>
				</div> 
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-1">
					<div class="btn btn-success" id="btn_submit" onclick="btn_form_submit()">提交</div>
				</div>
				<div class="col-sm-3">
					<div class="btn btn-danger" id="btn_reset" onclick="btn_form_expendses_reset()">重置</div>
<!-- 					<input type="reset" class="btn btn-danger" value=" 重置  " /> -->
				</div>
			</div>

		</form>
	</div>
	<!-- container -->
<script type="text/javascript">
	var ue;
	
	$(function() {
		// 初始化单选下拉框
		$(".selectpicker").selectpicker({
			style : "btn-info",
			size : 5,
			width : 160
		});
	
		// 表单验证
		$("#form_validator_expenses").bootstrapValidator({
			message : "This value is not valid",
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				item:{
					message:"支出项目不能为空……",
					validators: {
	                    notEmpty: {
	                        message: "支出项目不能为空"
	                    },
	                    stringLength: {
	                        min: 1,
	                        max: 15,
	                        message: "输入长度应该为1-15个字符"
	                    }
	                }
				},
				money:{
					validators:{
						notEmpty:{
							message: "支出金额不能为空"
						},
						regexp:{
							regexp: /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
							message: "请正确输入金额，两位有效小数"
						}
					}
				},
				payTime:{
					validators:{
						notEmpty:{
							message: "请选择支出日期"
						}
					}
				},
				typeId:{
					validators:{
						notEmpty:{
							message: "请正确选择分类"
						}
					}
				},
				typeDetailsId:{
					validators:{
						notEmpty:{
							message: "请正确选择分类"
						}
					}
				}
			}
		});
	});
	
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    ue = UE.getEditor("e_extraContent",{ // 此处为ID值
    	toolbars : [ [ "bold", "italic", "underline","undo", "redo" ] ],
    	elementPathEnabled : false,
    	maximumWords : 30,
    	autoHeightEnabled : false,
    	initialFrameHeight : 70
    });
	
	// 表单提交
	function btn_form_submit(){
		$("#form_validator_expenses").bootstrapValidator("validate").on("success.form.bv",function(e){
			e.preventDefault();
			show_my_confirm("warning","温馨提示","您确认要提交吗……",function(){
				var typeId = $("#e_typeId").val();
				var typeDetailsId = $("#e_typeDetailsId").val();
				var item = $("#e_item").val();
				var money = $("#e_money").val();
				var payTime = $("#e_payTime").val();
				var extraContent = ue.getPlainTxt();
				$.post("/ie/saveExpenses",{
					"typeId":typeId,
					"typeDetailsId":typeDetailsId,
					"item":item,
					"money":money,
					"payTime":payTime,
					"extraContent":extraContent
				},function(re_data){
					if(re_data.flag == "200"){
						show_my_alert("info","提示","数据提交成功……");
					}
				},"json");
			});
		});
	}
	
	// 初始化时间控件，并添加验证
	$("#div_expenses_datatime").datetimepicker({
		language : "zh-CN",
		format : "yyyy-mm-dd hh:ii:ss",
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
	}).on("changeDate show",function(e){
		$("#form_validator_expenses")
		.data('bootstrapValidator')
        .updateStatus('payTime', 'NOT_VALIDATED', null)
        .validateField('payTime');
	});
	
	// 重置按钮
	function btn_form_expendses_reset(){
		// 清除数据（标准）
		document.getElementById("form_validator_expenses").reset();
		// 清除数据（其它）
		$("#e_typeId").selectedIndex = 0;
		$("#e_typeId").selectpicker("refresh");
		$("#e_typeDetailsId").selectedIndex = 0;
		$("#e_typeDetailsId").selectpicker('refresh');
		ue.setContent("");
		// 清除勾叉符号
		$("#form_validator_expenses").data('bootstrapValidator').resetForm();
	}

	// 类型下拉列表
	function getDetailsByTypeId(dom) {
		$("#e_typeDetailsId").empty();
		var typeId = $(dom).val();
		if (typeId == "") {
			$("#e_typeDetailsId").append('<option value="">--请选择--</option>');
			$("#e_typeDetailsId").selectpicker("refresh");
			return;
		}
		$.post("/systemDictionaryDetails/getDetailsByTypeId", {
			typeId : typeId
		}, function(re_data) {
			console.debug(re_data.data);
			var data = re_data.data;
			var length = data.length;
			$("#e_typeDetailsId").append('<option value="">--请选择--</option>');
			if (length == 0) {
				$("#e_typeDetailsId").selectpicker("refresh");
				return;
			}
			for (var i = 0; i < length; i++) {
				$("#e_typeDetailsId").append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
			}
			$("#e_typeDetailsId").selectpicker("refresh");
		}, "json");
	}
	
	// 点击一个下拉列表，验证第二个下拉列表
	$("#e_typeId").change(function(){
		$("#form_validator_expenses")
		.data('bootstrapValidator')
        .updateStatus('typeDetailsId', 'NOT_VALIDATED', null)
        .validateField('typeDetailsId');
	});
	
</script>
</body>
</html>
