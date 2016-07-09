<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="retMap" type="com.pie.utils.ResultDataFormat" required="true"%>
<%@ attribute name="formId" type="java.lang.String" required="true"%>
<%@ attribute name="modalContentId" type="java.lang.String" required="true"%>
<%@ attribute name="startPage" type="java.lang.Integer" required="true"   %>
<%@ attribute name="endPage" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${fn:length(retMap.data) > 0 && retMap.totalPages > 1 }">
	<nav class="pull-right">
		<ul class="pagination pagination-sm">
			<li <c:if test="${retMap.pageNumber le 1 }">class="disabled"</c:if>>
				<a href="javascript:void(0);" <c:if test="${retMap.pageNumber gt 1 }">onclick="gotoPage('${retMap.pageNumber-1}');"</c:if>>
					<span> <span aria-hidden="true">&laquo;</span> </span>
				</a>
			</li>
			<c:forEach begin="${startPage}" end="${endPage}" var="p">
				<c:choose>
					<c:when test="${p <= retMap.totalPages}">
						<li <c:if test="${p eq retMap.pageNumber }">class="active" </c:if>>
							<a href="javascript:void(0);" onclick="gotoPage('${p}');"><span>${p }<span	class="sr-only"></span></span></a>
						</li>
					</c:when>
				</c:choose>
			</c:forEach>
			<li <c:if test="${retMap.pageNumber ge retMap.totalPages }">class="disabled"</c:if>>
				<a href="javascript:void(0);" <c:if test="${retMap.pageNumber lt retMap.totalPages }"> onclick="gotoPage('${retMap.pageNumber+1}');"</c:if>>
					<span> <span aria-hidden="true">&raquo;</span> </span>
				</a>
			</li>
		</ul>
	</nav>
</c:if>
<script>
	function gotoPage(page){
		$("form input[name='pageNumber']").val(page);
		$("#${modalContentId}").load($("#${formId}").attr("action"),$("#${formId}").serializeObject(),function(json){
			$("#${modalContentId}").html(json);
			$('html, body').animate({scrollTop:0}, 'slow');	//滚到顶部
		});
	}
</script>
