<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>PI</title>

<style type="text/css">
	#form_validator_income .has-feedback .form-control-feedback {
	    top: 0;
	    right: -15px;
	}
	#form_validator_income .has-feedback .input-group .form-control-feedback {
	    top: 0;
	    right: -30px;
	}
</style>

</head>
<body>
	<%@ include file="/WEB-INF/commons/main.jsp"%>
	<div class="container container_top_margin" >
		<h1 class="centered">人个收入</h1>
		<hr>
		
		<form id="form_validator_income" class="form-horizontal">
			<input type="hidden" name="sign" value="2">
			<div class="form-group">
				<label class="col-sm-2 control-label">收入分类：</label>
				<div class="col-lg-2">
					<select class="selectpicker input-group" id="i_typeId" name="typeId" onchange="getDetailsByTypeId(this)">
						<option value="">--请选择--</option>
						<c:forEach items="${allIncomeTypes.data }" var="typeMap">
							<option value="${typeMap.id }">${typeMap.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-8">
					<select class="selectpicker input-group" id="i_typeDetailsId" name="typeDetailsId">
						<option value="">--请选择--</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">收入项目：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="i_item" name="item" placeholder="收入项目">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">收入金额：</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="i_money" name="money" placeholder="收入金额">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">收入日期：</label>
				<div class="col-sm-5">
					<div class="input-group date" id="div_income_datatime" data-date-format="yyyy-mm-dd hh:ii:ss">
		                <input type="text" class="form-control" id="i_payTime" name="payTime" />
		                <span class="input-group-addon">
		                    <span class="glyphicon-calendar glyphicon"></span>
		                </span>
		            </div>
				</div>   
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">附加信息：</label>
				<div class="col-sm-5">
					<div id="extra_content" name="extraContent" style="width: 100%;"></div>
				</div> 
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-1">
					<div class="btn btn-success" onclick="btn_form_income_submit()"> 提交 </div>
				</div>
				<div class="col-sm-3">
					<div class="btn btn-danger" id="btn_reset" onclick="btn_income_reset()">重置</div>
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
		$('.selectpicker').selectpicker({
			style : 'btn-info',
			size : 5,
			width : 160
		});
		
		// 表单验证
		$("#form_validator_income").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				item:{
					message:"收入项目不能为空……",
					validators: {
	                    notEmpty: {
	                        message: "收入项目不能为空"
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
							message: "收入金额不能为空"
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
							message: "请选择收入日期"
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
    ue = UE.getEditor('extra_content',{
    	toolbars : [ [ 'bold', 'italic', 'underline','undo', 'redo' ] ],
    	elementPathEnabled : false,
    	maximumWords : 30,
    	autoHeightEnabled : false,
    	initialFrameHeight : 70
    });

	// 重置按钮
	function btn_income_reset(){
		// 清除数据（标准）
		document.getElementById("form_validator_income").reset();
		// 清除数据（其他）
		ue.setContent("");
		$("#i_typeId").selectedIndex = 0;
		$("#i_typeId").selectpicker('refresh');
		$("#i_typeDetailsId").selectedIndex = 0;
		$("#i_typeDetailsId").selectpicker('refresh');
		// 清除勾叉符号
		$("#form_validator_income").data('bootstrapValidator').resetForm();
	}
	
	// 当选择了第一个下拉，验证第二个下拉
	$('#i_typeId').change(function(e) {  
        // Revalidate the date when user change it  
        $('#form_validator_income')
        .data('bootstrapValidator')
        .updateStatus('typeDetailsId', 'NOT_VALIDATED', null)
        .validateField('typeDetailsId');
	});

	// 初始化时间控件,并添加验证
	$("#div_income_datatime").datetimepicker({
		language : 'zh-CN',
		format : "yyyy-mm-dd hh:ii:ss",
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
	}).on('changeDate show', function(e) {
        $('#form_validator_income')
        .data('bootstrapValidator')
        .updateStatus('payTime', 'NOT_VALIDATED', null)
        .validateField('payTime');
	});

	// 类型下拉列表
	function getDetailsByTypeId(dom) {
		$("#i_typeDetailsId").empty();
		var typeId = $(dom).val();
		if (typeId == "") {
			$("#i_typeDetailsId").append('<option value="">--请选择--</option>');
			$("#i_typeDetailsId").selectpicker('refresh');
			return;
		}
		$.post("/systemDictionaryDetails/getDetailsByTypeId", {
			typeId : typeId
		}, function(re_data) {
			var data = re_data.data;
			var length = data.length;
			$("#i_typeDetailsId").append('<option value="">--请选择--</option>');
			if (length == 0) {
				$("#i_typeDetailsId").selectpicker('refresh');
				return;
			}
			for (var i = 0; i < length; i++) {
				$("#i_typeDetailsId").append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
			}
			$("#i_typeDetailsId").selectpicker('refresh');
		}, "json");
	}
	
	// 提交按钮
	function btn_form_income_submit(){
		$('#form_validator_income').bootstrapValidator('validate').on("success.form.bv",function(e){
			e.preventDefault();
			show_my_confirm("warning","温馨提示","您确认要提交吗……",function(){
				var typeId = $("#i_typeId").val();
				var typeDetailsId = $("#i_typeDetailsId").val();
				var item = $("#i_item").val();
				var money = $("#i_money").val();
				var payTime = $("#i_payTime").val();
				var extraContent = ue.getPlainTxt();
				$.post("/ie/saveIncome",{
					typeId:typeId,
					typeDetailsId:typeDetailsId,
					item:item,
					money:money,
					payTime:payTime,
					extraContent:extraContent
				},function(re_data){
					if(re_data.flag == "200"){
						show_my_alert("info","温馨提示",re_data.msg,function(){
							$('#form_validator_income').data('bootstrapValidator').resetForm();
						});
					}
				},"json");
			});
		});
	}
</script>
</body>
</html>
