<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
span.overhide {
    overflow: hidden; 
    text-overflow: ellipsis; 
    -o-text-overflow: ellipsis;
    white-space:nowrap;
    width:250px;
    display:block;
}
</style>
<form class="search-form" action="<c:url value="/admin/passwayRouteMerchant/index" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	
	<label for="channel">公链平台：</label>
	<utils:enum mode="sel" name="channel" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.channel}" key="EnumDownNoCardChannel" />
	
	<label for="status">状态：</label>
	<utils:enum mode="sel" name="status" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.status}" key="EnumDownUserStatus" />

	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/passwayRouteMerchant/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>公链平台</td>
		<td>货币名称</td>
		
		<td>货币精度</td>
		<td>成交量限制</td>
		<td>日累计成交</td>
		
		<td>开放时间</td>
		
		<!-- <td>创建时间</td> -->
		<td>状态</td>
	
		<td>操作</td>
	</tr>
	
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.channel}" /></td>
			<td><c:out value="${item.coinName}" />(<c:out value="${item.coin}" />)</td>
			
			<td><c:out value="${item.priceScale}" /></td>
			<td><c:out value="${item.minPerAmt}" /> ~ <c:out value="${item.maxPerAmt}" /></td>
			<td><c:out value="${item.maxTotAmt}" /></td>
			
			<td><c:out value="${item.startTime}" /> - <c:out value="${item.endTime}" /></td>
			<td><utils:enum mode="lbl" value="${item.status}" key="EnumDownUserStatus" /></td>
			<td>
				<a href="<c:url value="/admin/passwayRouteMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/passwayRouteMerchant/delete/${item.id}" />" class="choose-link operate-delete">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="8">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>