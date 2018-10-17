<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
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
			<div class="ms-header">订单基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">支付方式:</td>
					<td class="liner-box-right">
						<c:choose>
							<c:when test="${item.payType eq '01' }"><c:set var="payType" value="网银" /></c:when>
							<c:when test="${item.payType eq '02' }"><c:set var="payType" value="银联在线" /></c:when>
							<c:otherwise><c:set var="payType" value="${item.payType }" /></c:otherwise>
						</c:choose>
						<input type="text" class="input-box" placeholder="支付方式" value="${payType}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">平台流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="平台流水号" name="transNo" value="${item.transNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">平台受理时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="平台受理时间" name="createDate" value="${item.createDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构号" name="mchNo" value="${item.mchNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构名称:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构名称" name="mchName" value="${item.mchName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易通道:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道" name="channel" value="${item.channel}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通道账户号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道账户号" name="channelAccNo" value="${item.channelAccNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通道商户:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道商户" name="channelAccName" value="${item.channelAccName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易费率:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易费率" name="price" value="${item.feeRatio}" readonly/>%。
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">单笔加收费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="单笔加收费" name="price" value="${item.addFeeAmt}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">商品标题:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="商品标题" name="subject" value="${item.subject}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">商品描述:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="商品描述" name="description" value="${item.description}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">同步回调地址:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="同步回调地址" name="callbackUrl" value="${item.callbackUrl}" readonly/>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="ms-col-5 ms-border">
			<div class="ms-header">机构状态信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">机构流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构订单时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="订单时间" name="orderDate" value="${item.orderDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易状态:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易状态" name="status" value="${item.status}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态描述:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易状态描述" name="statusDesc" value="${item.statusDesc}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态更新时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="状态更新时间" name="successDate" value="${item.statusChgDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易金额:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易金额" name="price" value="${item.price}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易手续费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="商户手续费金额" name="downPayFee" value="${item.downPayFee}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">结算金额:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="结算金额" name="downRealT1Price" value="${item.downRealT1Price}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知地址:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通知地址" name="notifyUrl" value="${item.notifyUrl}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知是否成功:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="异步通知是否成功" name="notifySuccess" value="${item.notifySuccess}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知次数:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="异步通知次数" name="notifyTimes" value="${item.notifyTimes}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">末次异步通知时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="末次异步通知时间" name="notifyLastDate" value="${item.notifyLastDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">支付链接:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="支付链接" name="payLink" value="${item.payLink}" readonly/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>