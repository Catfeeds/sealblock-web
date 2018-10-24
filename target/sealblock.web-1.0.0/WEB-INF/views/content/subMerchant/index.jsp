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
<form class="search-form" action="<c:url value="/content/subMerchant/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/subMerchant/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>代理商编号</td>
		<td>机构号</td>
		<td>机构名称 *</td>
		<td>机构用户标识</td>
		<td>平台用户标识</td>
		<td>用户币地址</td>
		<td>用户姓名</td>
		<td>用户身份证号</td>
		<td>用户联系电话</td>
		<td>用户状态</td>
		<td>鉴权状态</td>
		<td>鉴权结果</td>
		<td>鉴权日期</td>
		<td>已开通币类型，多个逗号分隔开</td>
		<td>备用字段1</td>
		<td>备用字段 2</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.agentNo}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.mchUserNo}" />
			</td>
			<td>
				<c:out value="${item.userNo}" />
			</td>
			<td>
				<c:out value="${item.address}" />
			</td>
			<td>
				<c:out value="${item.userName}" />
			</td>
			<td>
				<c:out value="${item.userCertId}" />
			</td>
			<td>
				<c:out value="${item.userPhone}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<c:out value="${item.authStatus}" />
			</td>
			<td>
				<c:out value="${item.authMessage}" />
			</td>
			<td>
				<c:out value="${item.authDate}" />
			</td>
			<td>
				<c:out value="${item.enabledCoin}" />
			</td>
			<td>
				<c:out value="${item.extra1}" />
			</td>
			<td>
				<c:out value="${item.extra2}" />
			</td>
			<td>
				<a href="<c:url value="/content/subMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/subMerchant/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
