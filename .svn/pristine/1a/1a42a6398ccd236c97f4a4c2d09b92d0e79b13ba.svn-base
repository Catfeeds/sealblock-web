<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downMerchantFundAccount/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">账户类别</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accountType" value="${item.accountType}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币标识, eth, usdt</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="coin" value="${item.coin}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="coinName" value="${item.coinName}">
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
			<td class="liner-box-left required">清算金额</td>
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
			<td class="liner-box-left required">当日入账额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="curInAmt" value="${item.curInAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现金额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="curOutAmt" value="${item.curOutAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">变动日期</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="changeDate" value="${item.changeDate}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

