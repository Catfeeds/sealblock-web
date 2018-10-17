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
<form class="search-form" action="<c:url value="/admin/subMerchantFundInvoice/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/subMerchantFundInvoice/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>平台用户标识</td>
		<td>记录日期</td>
		<td>用户姓名</td>
		<td>订单号</td>
		<td>是否支付交易</td>
		<td>用户地址</td>
		<td>关联地址</td>
		<td>交易额（交易额）</td>
		<td>手续费（仅出帐有值）</td>
		<td>变动日期</td>
		<td>gasPrice</td>
		<td>gasWeight</td>
		<td>备用字段1</td>
		<td>备用字段 2</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.userNo}" />
			</td>
			<td>
				<c:out value="${item.recordDate}" />
			</td>
			<td>
				<c:out value="${item.userName}" />
			</td>
			<td>
				<c:out value="${item.txHashNo}" />
			</td>
			<td>
				<c:out value="${item.payFlag}" />
			</td>
			<td>
				<c:out value="${item.address}" />
			</td>
			<td>
				<c:out value="${item.referAddress}" />
			</td>
			<td>
				<c:out value="${item.price}" />
			</td>
			<td>
				<c:out value="${item.feeAmt}" />
			</td>
			<td>
				<c:out value="${item.tradeTime}" />
			</td>
			<td>
				<c:out value="${item.gasPrice}" />
			</td>
			<td>
				<c:out value="${item.gasWeight}" />
			</td>
			<td>
				<c:out value="${item.extra1}" />
			</td>
			<td>
				<c:out value="${item.extra2}" />
			</td>
			<td>
				<a href="<c:url value="/admin/subMerchantFundInvoice/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/admin/subMerchantFundInvoice/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
