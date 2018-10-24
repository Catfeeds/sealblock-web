<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty error}">
<script>
$(function(){
	$.messager.alert('错误','${error}');
});
</script>
</c:if>
<style>
.lbl-summary {
display:inline-block;width:70px;font-weight:bold;
}
</style>
<form class="search-form" action="<c:url value="/admin/downMerchant/index" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	
	<label for="mercCategory">类别：</label>
	<utils:enum mode="sel" name="mercCategory" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.mercCategory}" key="EnumMercCategory" />
	
	<label for="mercCategory">状态：</label>
	<utils:enum mode="sel" name="status" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.status}" key="EnumDownUserStatus" />
	
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/downMerchant/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<form action="<c:url value="/admin/downMerchant/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构</td>
		<td>类别</td>
		<td>开通货币</td>
		<td>信任IP</td>
		<td>创建时间</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.name}" />(<c:out value="${item.mchNo}" />)</td>
			<td><utils:enum mode="lbl" value="${item.mercCategory}" key="EnumMercCategory" /></td> 
			<td>${item.fundSummary }</td>
			<td><c:out value="${item.limitIps }" /></td>
			<td><c:out value="${item.createDate}" /></td>
			<td><utils:enum mode="lbl" value="${item.status}" key="EnumDownUserStatus" /></td>
			<td>
				<a href="<c:url value="/admin/downMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/downMerchant/delete/${item.id}" />" class="choose-link operate-delete" >删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="11">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>