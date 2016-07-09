/**
 * 构建StringBuffer
 */
function StringBuffer(){
	this.content = new Array();
}

/**
 * 给StringBuffer 添加属性 append
 * @param str
 * @returns {StringBuffer}
 */
StringBuffer.prototype.append = function(str){
	this.content.push(str);
	return this;
}

/**
 * 给StringBuffer 添加属性 toString
 * @returns
 */
StringBuffer.prototype.toString = function(){
	return this.content.join("");
}

/**
 * 将form 转换成 json 使用方法:在使用ajax提交保存的时候,$("#formId").serializeObject()
 * 可以将表单数据转为k-v格式
 */
$.fn.serializeObject = function() {
	var o = {}; // 初始化json对象
	var a = this.serializeArray(); // 将form序列化成数组
	// 如下：
//	[ 
//	  {name: 'firstname', value: 'Hello'}, 
//	  {name: 'lastname', value: 'World'},
//	  {name: 'alias'}, // 值为空
//	]
	$.each(a, function() { // 遍历
		if (o[this.name]) { // 判断json对象中是否存在该值
			if (!o[this.name].push) { // 判断不否为嵌套数组（不是）
				o[this.name] = [ o[this.name] ]; // 初始化成嵌套数组
			}
			o[this.name].push(this.value || ''); // 在用push向数组中赋值
		} else {
			o[this.name] = this.value || ''; // 向json对象中赋值
		}
	});
	return o;
};


( function ($){
	$.fn.serializeJson= function (){
		var serializeObj={};
		var array= this .serializeArray();
		var str= this .serialize();
		$(array).each( function (){
			if (serializeObj[ this .name]){
				if ($.isArray(serializeObj[ this .name])){
					serializeObj[ this .name].push( this .value);
				} else {
					serializeObj[ this .name]=[serializeObj[ this .name], this .value];
				}
			} else {
				serializeObj[ this .name]= this .value;
			}
		});
		return serializeObj;
	};
})(jQuery);






/**
 * 提示框
 * @param type  "info":绿色,"warning":黄色,"error":红色
 * @param msg_title
 * @param msg_content
 * @param callback  例：$("#id").show_my_alert( "header_type", "title", "content", function(){	//some coding	} );
 */
function show_my_alert(type, msg_title, msg_content, callback) {
	var string = new StringBuffer();
	string.append('<div class="modal fade bs-example-modal-md" id="alert_model" tabindex="-1"')
	      .append('role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">')
	      .append('<div class="modal-dialog modal-md" style="margin:180px auto">')
	      .append('<div class="modal-content">')
	      .append('<div class="modal-header ')
	      .append((type.length > 0 && type == "info" ? "info_header" : type == "warning" ? "warning_header" : "error_header"))
	      .append('">')
	      .append((msg_title.length > 0 ? msg_title : '提示'))
	      .append('</div> <div class="modal-body">')
	      .append(msg_content)
	      .append('</div> <div class="modal-footer">')
	      .append('<button type="button" class="btn btn-primary" data-dismiss="modal" id="btn_alert">确认</button>')
	      .append('</div></div></div></div>');
	$('body').append(string.toString());

	$("#alert_model").modal("show").on("hidden.bs.modal", function() {
		$('#alert_model').remove();
		$(this).removeData("bs.modal");
	});
	$("#btn_alert").bind("click", function() {
		if(typeof callback != 'undefined'){
			callback();
		}	
	});
}

/**
 * 确认提示框
 * @param type  "info":绿色,"warning":黄色,"error":红色
 * @param msg_title
 * @param msg_content
 * @param callback  例：$("#id").show_my_confirm( "header_type", "title", "content", function(){	//some coding	} );
 */
function show_my_confirm(type, msg_title, msg_content, callback) {
	var str = '<div class="modal fade bs-example-modal-md" id="confirm_model" tabindex="-1"'
			+ 'role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">'
			+ '<div class="modal-dialog modal-md" style="margin:180px auto">'
			+ '<div class="modal-content">'
			+ '<div class="modal-header '
			+ (type.length > 0 && type == "info" ? "info_header"
					: type == "warning" ? "warning_header" : "error_header")
			+ '">'
			+ (msg_title.length > 0 ? msg_title : '提示')
			+ '</div>'
			+ '<div class="modal-body">'
			+ msg_content
			+ '</div>'
			+ '<div class="modal-footer">'
			+ '<button type="button" class="btn btn-primary" data-dismiss="modal" id="btn_confirm_cancel">取消</button>'
			+ '<button type="button" class="btn btn-danger" data-dismiss="modal" id="btn_confirm">确认</button>'
			+ '</div></div></div></div>';
	$('body').append(str);
	$("#confirm_model").modal("show").on("hidden.bs.modal", function() {
		$('#confirm_model').remove();
		$(this).removeData("bs.modal");
	});
	$("#btn_confirm").bind("click", function() {
		if(typeof callback != 'undefined')	callback();
	});
}
