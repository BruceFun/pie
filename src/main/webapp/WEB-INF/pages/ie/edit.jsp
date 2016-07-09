<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/commons/tags.jsp"%>
<script src="/commons/js/pie_common.js"></script>

<style type="text/css">
	#form_edit .has-feedback .form-control-feedback {
	    top: 0;
	    right: -15px;
	}
	#form_edit .has-feedback .input-group .form-control-feedback {
	    top: 0;
	    right: -30px;
	}
</style>

<form id="form_edit" class="form-horizontal" >
	<input type="hidden" name="id" value="${oneObjectDetails.data.id }" />
	<input type="hidden" name="time" value="${oneObjectDetails.data.time }" />
	<input type="hidden" name="sign" value="${oneObjectDetails.data.sign }" />
	
	<div class="form-group">
		<label class="col-sm-2 control-label">选择分类:</label>
		<div class="col-sm-3">
			<select class="selectpicker input-group" name="typeId" id="edit_typeId" >
				<option value="">--请选择--</option>
				<c:forEach items="${allTypes.data }" var="typeObject">
					<option value="${typeObject.id }" <c:if test="${oneObjectDetails.data.type.id eq typeObject.id }">selected</c:if> >${typeObject.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-3">
			<select class="selectpicker input-group" name="typeDetailsId" id="edit_typeDetailsId">
				<option value="">--请选择--</option>
				<c:forEach items="${allTypesDetails.data }" var="typeObject">
					<option value="${typeObject.id }" <c:if test="${oneObjectDetails.data.typeDetails.id eq typeObject.id }">selected</c:if> >${typeObject.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">项目:</label>
		<div class="col-sm-6">
			<input type="email" name="item" class="form-control" id="edit_item" value="${oneObjectDetails.data.item }" placeholder="项目">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">金额:</label>
		<div class="col-sm-6">
			<input type="text" name="money" class="form-control" id="edit_money" value="${oneObjectDetails.data.money }" placeholder="金额">
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">日期:</label>
		<div class="col-sm-6">
			<div class="input-group date" id="edit_datetime" data-date="">
			    <input class="form-control form_datetime" id="edit_payTime" name="payTime" type="text" value="${fn:substring(oneObjectDetails.data.payTime,0,19) }" readonly>
				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">附加信息:</label>
		<div class="col-sm-6">
			<textarea class="form-control" name="extraContent" rows="1" style="resize:none;" >${oneObjectDetails.data.extraContent }</textarea>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-3">
			<button type="button" class="btn btn-success" onclick="edit_save()">保存</button>
		</div>
		<div class="col-sm-3">
			<button type="button" class="btn btn-danger" onclick="edit_cancel()">取消</button>
		</div>
	</div>
	<div class="form-group">
		
	</div>
</form>
<script type="text/javascript">
	$(function(){
		$(".selectpicker").selectpicker({
			style : "btn-info",
			size : 5,
			width : 120
		});
		
		$("#form_edit").bootstrapValidator({
			message : "This value is not valid",
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				item:{
					message:"项目不能为空……",
					validators: {
	                    notEmpty: {
	                        message: "项目不能为空"
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
							message: "金额不能为空"
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
							message: "请选择日期"
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
	
	// 初始化日期控件
	$("#edit_datetime").datetimepicker({
		language : "zh-CN",
		format : "yyyy-mm-dd hh:ii:ss",
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
	});
	
	// 保存修改信息
	function edit_save(){
		// 保存之前先验证
		$("#form_edit").bootstrapValidator("validate").on("success.form.bv",function(e){
			$.post("/ie/editSave", $("#form_edit").serializeObject(), function(re_data){
				if(re_data.flag == "200"){
					// 删除模态框
					$("#ie_edit_container").modal("hide");
					show_my_alert("info","提示","数据提交成功……",function(){
						$("#detailsTable").load($("#form_pied_search").attr("action"),$("#form_pied_search").serializeObject(),function(json){});
					});
				}
			},"json");
		});
	}
	
</script>