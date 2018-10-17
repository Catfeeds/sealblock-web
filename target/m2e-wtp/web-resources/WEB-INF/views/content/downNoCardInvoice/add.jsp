<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downNoCardInvoice/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">公链类型 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channel" value="${item.channel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币标识, eth, usdt</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="currency" value="${item.currency}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="currencyName" value="${item.currencyName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户号 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户名 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transNo" value="${item.transNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台日期yyyyMMdd</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transDate" value="${item.transDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商品标题</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="subject" value="${item.subject}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游交易流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeNo" value="${item.tradeNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单时间, 格式：yyyyMMddHHmmss</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderDate" value="${item.orderDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">同步回调地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="callbackUrl" value="${item.callbackUrl}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyUrl" value="${item.notifyUrl}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代理商 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentNo" value="${item.agentNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="price" value="${item.price}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">费率，单位：千分之</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeRatio" value="${item.feeRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户交易手续费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="downPayFee" value="${item.downPayFee}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户交易附加费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="downPlusFee" value="${item.downPlusFee}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户清算金额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="downRealPrice" value="${item.downRealPrice}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代理商利润</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agtProfit" value="${item.agtProfit}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台利润</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="profit" value="${item.profit}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userName" value="${item.userName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户身份证号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userCertId" value="${item.userCertId}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户联系电话</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userPhone" value="${item.userPhone}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="merResv1" value="${item.merResv1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="merResv2" value="${item.merResv2}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">上游流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="upTransNo" value="${item.upTransNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态描述</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusDesc" value="${item.statusDesc}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">公网交易状态</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="fundStatus" value="${item.fundStatus}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">公网状态描述</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="fundStatusDesc" value="${item.fundStatusDesc}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">公网更新时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="fundChgDate" value="${item.fundChgDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">支付链接</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="payLink" value="${item.payLink}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

