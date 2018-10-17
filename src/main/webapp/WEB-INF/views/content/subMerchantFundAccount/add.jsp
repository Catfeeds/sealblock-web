<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/subMerchantFundAccount/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">代理商编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentNo" value="${item.agentNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构名称 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchUserNo" value="${item.mchUserNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userNo" value="${item.userNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币标识, eth, usdt</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="currency" value="${item.currency}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户币地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="address" value="${item.address}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userName" value="${item.userName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">期初余额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="lastSettleAmt" value="${item.lastSettleAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">账户余额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleInAmt" value="${item.settleInAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">消费金额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleOutAmt" value="${item.settleOutAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">冻结金额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="frozenAmt" value="${item.frozenAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">变动日期</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="changeDate" value="${item.changeDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备用字段1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra1" value="${item.extra1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备用字段 2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra2" value="${item.extra2}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

