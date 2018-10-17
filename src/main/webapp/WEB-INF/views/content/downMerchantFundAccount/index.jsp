<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty errorMsg}">
<script>
$(function(){
	$.messager.alert('错误','${errorMsg}');
});
</script>
</c:if>
<form class="search-form" action="<c:url value="/content/downMerchantFundAccount/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downMerchantFundAccount/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>账户类别</td>
		<td>机构编号</td>
		<td>姓名</td>
		<td>币标识, eth, usdt</td>
		<td>币名称</td>
		<td>期初余额</td>
		<td>账户余额</td>
		<td>清算金额</td>
		<td>冻结金额</td>
		<td>当日入账额</td>
		<td>提现金额</td>
		<td>变动日期</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.accountType}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.coin}" />
			</td>
			<td>
				<c:out value="${item.coinName}" />
			</td>
			<td>
				<c:out value="${item.lastSettleAmt}" />
			</td>
			<td>
				<c:out value="${item.settleInAmt}" />
			</td>
			<td>
				<c:out value="${item.settleOutAmt}" />
			</td>
			<td>
				<c:out value="${item.frozenAmt}" />
			</td>
			<td>
				<c:out value="${item.curInAmt}" />
			</td>
			<td>
				<c:out value="${item.curOutAmt}" />
			</td>
			<td>
				<c:out value="${item.changeDate}" />
			</td>
			<td>
				<a href="<c:url value="/content/downMerchantFundAccount/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downMerchantFundAccount/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
