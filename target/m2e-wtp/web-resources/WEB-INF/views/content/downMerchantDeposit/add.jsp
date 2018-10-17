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
<form action="<c:url value="/content/downMerchantDeposit/add" />" method="post">
	<input type="hidden" name="tradeNo" value="${item.tradeNo}" />
	<div class="ms-row">
		<div class=" ms-border">
			<div class="ms-header">手动提现</div>
			<table class="liner-box">
				<c:if test="${not empty error}">
					<tr class="liner-error">
						<td class="liner-box-left"></td>
						<td class="liner-box-right" style="color: red;">${error}</td>
					</tr>
				</c:if>
				<tr>
					<td class="liner-box-left required">提现账户</td>
					<td class="liner-box-right"> 
						<select name='mode' class='input-box'>
							<option value="S1">清算账户</option>
							<option value="S0">实时账户</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">收款人</td>
					<td class="liner-box-right"> 
						<input class="input-box" type="text" name="accName" value="${item.accName}" >
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">收款卡号</td>
					<td class="liner-box-right"> 
						<input class="input-box" type="text" name="accCardNo" value="${item.accCardNo}" >
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">提现金额</td>
					<td class="liner-box-right"> 
						<input class="input-box" type="text" name="price" value="${item.price}" >
					</td>
				</tr>
				<tr>
					<td colspan="2" class="liner-box-one-line">
						<input type="submit" value="提交" class="btn" style="margin-top:100px;" />
					</td>
				</tr>
		</table>
		</div>
	</div>
</form>