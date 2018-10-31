<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/admin/downBalanceTransfer/add" />">
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
			<td class="liner-box-left required">代理商 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentNo" value="${item.agentNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构号 *</td>
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
			<td class="liner-box-left required">交易用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userNo" value="${item.userNo}">
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
			<td class="liner-box-left required">下游交易流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeNo" value="${item.tradeNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游交易时间, 格式：yyyyMMddHHmmss</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderDate" value="${item.orderDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游异步通知地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyUrl" value="${item.notifyUrl}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游接收方货币地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="receivCoinAddr" value="${item.receivCoinAddr}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="price" value="${item.price}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易备注信息</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="remark" value="${item.remark}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易矿工费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="subUserFee" value="${item.subUserFee}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户货币地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userCoinAddr" value="${item.userCoinAddr}">
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
			<td class="liner-box-left required">状态更新时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusChgDate" value="${item.statusChgDate}">
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
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

