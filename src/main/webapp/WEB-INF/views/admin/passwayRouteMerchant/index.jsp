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
	
	<label for="channel">通道：</label>
	<utils:combo mode="sel3" name="channel" needDefaultValue="请选择" cssName="input-box search-input-box" value="${search.channel}"></utils:combo>
	
	<label for="channel">结算模式：</label>
	<utils:enum key="EnumSettleType" name="settleType" needDefaultValue="请选择"  cssName="input-box search-input-box" value="${search.settleType}"></utils:enum>
	
	<label for="channel">状态：</label>
	<utils:enum mode="sel" name="status" needDefaultValue="请选择" cssName="input-box search-input-box" value="${search.status}" key="EnumDownUserStatus" />
	
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/passwayRouteMerchant/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>通道</td>
		<td>通道商编</td>
		<td>通道商户</td>
		
		<td>上日结算</td>
		<td>账户余额</td>
		<td>授信额度</td>
		<td>已代付额</td>
		<td>冻结金额</td>
		
		<td>同步时间</td>
		<td>状态</td>
	
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.channel}" /></td>
			<td><c:out value="${item.channelAccNo}" /></td>
			<td><c:out value="${item.channelAccName}" /></td>
			
			<td><fmt:formatNumber value="${item.settleAmt / 100.0}" pattern="#,##0.00#"/></td>
			<td><fmt:formatNumber value="${item.balanceAmt / 100.0}" pattern="#,##0.00#"/></td>
			<td><fmt:formatNumber value="${item.creditAmt / 100.0}" pattern="#,##0.00#"/></td>
			<td><fmt:formatNumber value="${item.curOutAmt / 100.0}" pattern="#,##0.00#"/></td>
			<td><fmt:formatNumber value="${item.frozenAmt / 100.0}" pattern="#,##0.00#"/></td>
			
			<td><c:out value="${item.amtChgDate}" /></td>
			<td><utils:enum mode="lbl" value="${item.status}" key="EnumDownUserStatus" /></td>
			<td>
				<a href="<c:url value="/admin/passwayRouteMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/passwayRouteMerchant/delete/${item.id}" />" class="choose-link operate-delete">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="11">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>