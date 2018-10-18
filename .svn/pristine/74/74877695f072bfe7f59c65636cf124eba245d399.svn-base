<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
	font-weight: bold;
}
.liner-box-right {
	padding: 0;
}
.liner-box-right input {
	margin: 2px 0;
}
</style>
<form action="<c:url value="/admin/downNoCardInvoice/detail" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
		<div class="ms-col-5 ms-border">
			<div class="ms-header">平台信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">货币单位:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.coin}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">公链平台:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="<utils:enum mode="lbl" value="${item.channel}" key="EnumDownNoCardChannel" />" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">平台流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.transNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">受理时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.createDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.mchNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构名称:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.mchName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易费率:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box"  value="${item.feeRatio}" readonly/>%。
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">单笔加收费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.addFeeAmt}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">手续费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box"  value="${item.merchantFee}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">结算额:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.downRealPrice}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">姓名:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.userName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">联系电话:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.userPhone}" readonly/>
					</td>
				</tr>
				<%-- <tr>
					<td class="liner-box-left">币地址:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.address}" readonly/>
					</td>
				</tr> --%>
			</table>
		</div>
		
		<div class="ms-col-5 ms-border">
			<div class="ms-header">机构信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.tradeNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">订单标题:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.subject}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">订单时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.orderDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="<utils:enum mode="lbl" value="${item.status}" key="EnumOrderStatus" />" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易额:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.price}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态描述:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.statusDesc}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">更新时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.statusChgDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通知地址:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.notifyUrl}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通知结果:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.notifySuccess}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知次数:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.notifyTimes}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">最后通知时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" value="${item.notifyLastDate}" readonly/>
					</td>
				</tr>
				<%-- <tr>
					<td class="liner-box-left">支付链接:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="支付链接" name="payLink" value="${item.payLink}" readonly/>
					</td>
				</tr> --%>
			</table>
		</div>
	</div>
</form>