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
<form action="<c:url value="/content/downNoCardInvoice/chgcard" />" method="post">
	<input type="hidden" name="transNo" value="${item.transNo}" />
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<div class="ms-row">
		<div class=" ms-border">
			<div class="ms-header">交易信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">结算卡户名</td>
					<td class="liner-box-right"> 
						<input class="input-box" type="text" name="settleCardName" value="${item.settleCardName}" readonly>
					</td>
					<td class="liner-box-left required">身份证号</td>
					<td class="liner-box-right">
						<input class="input-box" type="text" name="settleCardCertId" value="${item.settleCardCertId}" readonly>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">结算卡卡号</td>
					<td class="liner-box-right">
						<input class="input-box" type="text" name="settleCardNo" value="${item.settleCardNo}" >
					</td>
					<td class="liner-box-left ">结算卡手机号码</td>
					<td class="liner-box-right">
						<input class="input-box" type="text" name="settleCardMobile" value="${item.settleCardMobile}" >
					</td>
				</tr>
				<tr>
					<td class="liner-box-left ">结算卡银行名称</td>
					<td class="liner-box-right">
						<input class="input-box" type="text" name="settleCardBankNm" value="${item.settleCardBankNm}" >
					</td>
					<td class="liner-box-left ">结算卡联行号</td>
					<td class="liner-box-right" >
						<input class="input-box" type="text" name="settleCardBankUnionNo" value="${item.settleCardBankUnionNo}" >
					</td>
				</tr>
				<tr>
					<td class="liner-box-left ">结算卡联行名称</td>
					<td class="liner-box-right" conspan="3">
						<input class="input-box" type="text" name="settleCardBankUnionNm" value="${item.settleCardBankUnionNm}" >
					</td>
				</tr>
				<tr>
					<td colspan="4" class="liner-box-one-line">
						<input type="submit" value="提交" class="btn" style="margin-top:100px;"/>
					</td>
				</tr>
		</table>
		</div>
		
	</div>
</form>