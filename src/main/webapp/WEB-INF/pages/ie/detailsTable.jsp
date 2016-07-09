<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/commons/tags.jsp"%>
<script src="/commons/js/pie_common.js"></script>

<form id="form_pied_search" class="form-horizontal" action="/ie/getPageByQuery" method="post" >
	<input type="hidden" name="pageNumber" id="pageNumber" value="${retMap.pageNumber }" title="当前页码">
	<input type="hidden" name="pageSize" id="pageSize" value="${retMap.pageSize }" title="分页条数">
	<input type="hidden" name="startPage" value="${startPage }" title="paging_bar_开始条数">
	<input type="hidden" name="endPage" value="${endPage }" title="paging_bar_结束条数">
	<div class="form-group">
		<div class="col-md-3">
			<label class="col-md-5 control-label" >选择月份：</label>
			<div class="col-md-6" >
				<select class="selectpicker input-group" name="month" id="select_search" >
					<option value="">--请选择--</option>
					<option value="1" <c:if test="${month eq '1' }">selected</c:if> >1月</option>
					<option value="2" <c:if test="${month eq '2' }">selected</c:if> >2月</option>
					<option value="3" <c:if test="${month eq '3' }">selected</c:if> >3月</option>
					<option value="4" <c:if test="${month eq '4' }">selected</c:if> >4月</option>
					<option value="5" <c:if test="${month eq '5' }">selected</c:if> >5月</option>
					<option value="6" <c:if test="${month eq '6' }">selected</c:if> >6月</option>
					<option value="7" <c:if test="${month eq '7' }">selected</c:if> >7月</option>
					<option value="8" <c:if test="${month eq '8' }">selected</c:if> >8月</option>
					<option value="9" <c:if test="${month eq '9' }">selected</c:if> >9月</option>
					<option value="10" <c:if test="${month eq '10' }">selected</c:if> >10月</option>
					<option value="11" <c:if test="${month eq '11' }">selected</c:if> >11月</option>
					<option value="12" <c:if test="${month eq '12' }">selected</c:if> >12月</option>
				</select>
			</div>
		</div>
		<div class="col-md-3">
			<label class="col-md-4 control-label" >关键字：</label>
			<div class="col-md-7" >
				<input type="text" name="keyWords" class="form-control" value="${queryPage.keyWords }" placeholder="关键字">
			</div>
		</div>
		<div class="col-md-3">
			<label class="col-md-5 control-label">选择日期：</label>
			<div class="input-group date form_datetime datetimepicker" id="startDate" data-date="">
				<input class="form-control form_datetime" type="text" name="startDate" value="${startDate }" readonly> 
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class="col-md-2">
			<div class="input-group date form_datetime datetimepicker" id="endDate" data-date="">
				<input class="form-control form_datetime" type="text" name="endDate" value="${endDate }" readonly> 
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-md-3 control-label"></label>
		<div class="col-md-1">
			<div class="btn btn-success" id="btn_submit" onclick="btn_form_search()">查询</div>
		</div>
		<div class="col-md-3">
			<div class="btn btn-danger" id="btn_reset" onclick="btn_form_search_reset()">重置</div>
		</div>
	</div>
</form>

<table class="table table-hover">
	<thead>
		<tr>
			<th>序号</th>
			<th>项目</th>
			<th>金额</th>
			<th>支出/收入</th>
			<th>时间</th>
			<th>附加信息</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${retMap.data }" var="item" varStatus="vs">
			<tr>
				<td>${vs.index + 1 }</td>
				<td>
					<c:if test="${fn:length(item.item) > 5 }">
						<span title="${item.item }" style="cursor: pointer;">${fn:substring(item.item,0,5) }</span><strong>...</strong>
					</c:if>
					<c:if test="${fn:length(item.item) <= 5 }">
						${item.item }
					</c:if>
				</td>
				<td>
					<c:if test="${item.sign eq '1' }">
						- ${item.money }
					</c:if>
					<c:if test="${item.sign eq '2' }">
						+ ${item.money }
					</c:if>
				</td>
				<td>
					<c:if test="${item.sign eq '1' }">
						支出： ${item.type.name }  <strong>.</strong> ${item.typeDetails.name }
					</c:if>
					<c:if test="${item.sign eq '2' }">
						收入： ${item.type.name } <strong>.</strong>  ${item.typeDetails.name }
					</c:if>
				</td>
				<td>${fn:substring(item.payTime,0,19) }</td>
				<td>
					<c:if test="${fn:length(item.extraContent) > 7 }">
						<span title="${item.extraContent }" style="cursor: pointer;">${fn:substring(item.extraContent,0,7) }</span><strong>...</strong>
					</c:if>
					<c:if test="${fn:length(item.extraContent) <= 7 }">
						${item.extraContent }
					</c:if>
				</td>
				<td>
					<span title="编辑" class="glyphicon glyphicon-edit cursor_hander" aria-hidden="true" onclick="search_edit('${item.id}')"></span> |
					<span title="删除" class="glyphicon glyphicon-remove cursor_hander" aria-hidden="true"  onclick="search_delete('${item.id}')"></span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="form-group">
	<div class="col-md-4">
		共${retMap.totalElements }条数据——当前第${retMap.pageNumber }页，共${retMap.totalPages }页
	</div>
	<div class="col-md-8">
		<c:if test="${fn:length(retMap.data) > 0 }">
			<tags:pagingBar4Ajax startPage="${startPage}" endPage="${endPage}" retMap="${retMap }" formId="form_pied_search" modalContentId="detailsTable"></tags:pagingBar4Ajax>
		</c:if>
	</div>
</div>

<style>
	.cursor_hander{
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	//项目名称格式化
	function pied_itemFormatter(value){
		var length = value.length;
		if(length > 5){
			var v = value.substring(0,5);
			return "<span title='"+ value +"' style='cursor: pointer;'>" + v +"...</span>";
		}
		return value;
	}
	
	// 初始化下拉列表
	$(function(){
		$(".selectpicker ").selectpicker ({
			style: "btn-info", 
			size: 7, 
			width: 100
		});
	});
	
	// 初始化时间选择器
	$(".datetimepicker").datetimepicker({
		language : "zh-CN",
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		startView : 2,
		minView : 2,
		pickerPosition : "bottom-left"
	})
	
	// 搜索查询
	function btn_form_search(){
		$("#pageNumber").val("1");
		$("#detailsTable").load($("#form_pied_search").attr("action"),$("#form_pied_search").serializeObject(),function(json){});
	}
	
	// 重置按钮
	function btn_form_search_reset(){
		// 重置（标准）
		document.getElementById("form_pied_search").reset();
		// 重置（特殊）
		$("#select_search").selectedIndex = 0;
		$("#select_search").selectpicker("refresh");
		
	}
	
	// 编辑数据
	function search_edit(id){
		$("#ie_modal_body").load("/ie/edit",{id:id},function(){
			$("#ie_edit_container").modal("show").on('hidden.bs.modal',function(){
				$(this).removeData("bs.modal");
			});
		});
	}
	
	// 删除数据
	function search_delete(id){
		show_my_confirm("waring","温馨提示","您确定要删除……",function(){
			$.post("/ie/delete",{"id":id},function(re_data){
				if(re_data.flag == "200"){
					show_my_alert("waring","温馨提示","删除成功……",function(){
						$("#detailsTable").load($("#form_pied_search").attr("action"),$("#form_pied_search").serializeObject(),function(json){});
					});
				}else{
					show_my_alert("waring","温馨提示","删除失败……");
				}
			},"json");
		});
	}
</script>