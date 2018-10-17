<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/admin/subMerchantFundInvoice/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">平台用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userNo" value="${item.userNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">记录日期</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="recordDate" value="${item.recordDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userName" value="${item.userName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="txHashNo" value="${item.txHashNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">是否支付交易</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="payFlag" value="${item.payFlag}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="address" value="${item.address}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">关联地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="referAddress" value="${item.referAddress}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易额（交易额）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="price" value="${item.price}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">手续费（仅出帐有值）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeAmt" value="${item.feeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">变动日期</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeTime" value="${item.tradeTime}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">gasPrice</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="gasPrice" value="${item.gasPrice}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">gasWeight</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="gasWeight" value="${item.gasWeight}">
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

