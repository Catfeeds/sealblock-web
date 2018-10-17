<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
.ms-row .ms-border {
    min-height: 220px;
}
.liner-box-left {
    vertical-align: top;
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
        line-height: 34px;
}

.input-box, form label {
    color: #333;
    font-family: 'Microsoft YaHei', Arial, Helvetica, sans-serif;
    font-size: 0.9rem;
}
.btn {
    line-height: 30px;
    padding: 0 10px;
    /* margin: 0; */
    height: 30px;
}
input.input-box[readonly], select.input-box[readonly] {
	background-color: rgb(235, 235, 228);
}
td.special {
background-color:#c35c7a;
}
</style>
<div style="width:800px;margin: 30px auto 10px;">
<form action="<c:url value="/content/home/directPay" />" method="post" >
	<div class="ms-header">站内收款</div>
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">支付方式:</td>
			<td class="liner-box-right">
				<!-- 01网银、02银联在线、03转账码、10银联主扫、11银联刷卡、12QQ钱包、13支付宝扫码、14支付宝Wap、15微信Wap -->
				<select required="required" class="input-box search-input-box" name="payType">
					<option value="">请选择</option>
					<option <c:if test="${item.payType eq '01' }">selected='selected'</c:if> value="01">网银</option>
					<option <c:if test="${item.payType eq '02' }">selected='selected'</c:if> value="02">银联在线</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">金额:</td>
			<td class="liner-box-right">
				<input type="text" required="required"　class="input-box" placeholder="交易金额" name="price" value="${item.price}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">所属银行:</td>
			<td class="liner-box-right">
				<select required="required" class="input-box search-input-box" name="payBankCode">
					<option value="">请选择</option>
					<option <c:if test="${item.payBankCode eq '01050000' }">selected='selected'</c:if> value="01050000">建设银行</option>
					<option <c:if test="${item.payBankCode eq '03080000' }">selected='selected'</c:if> value="03080000">招商银行</option>
					<option <c:if test="${item.payBankCode eq '01020000' }">selected='selected'</c:if> value="01020000">工商银行</option>
					<option <c:if test="${item.payBankCode eq '01040000' }">selected='selected'</c:if> value="01040000">中国银行</option>
					<option <c:if test="${item.payBankCode eq '01030000' }">selected='selected'</c:if> value="01030000">农业银行</option>
					<option <c:if test="${item.payBankCode eq '03010000' }">selected='selected'</c:if> value="03010000">交通银行</option>
					<option <c:if test="${item.payBankCode eq '03050000' }">selected='selected'</c:if> value="03050000">民生银行</option>
					<option <c:if test="${item.payBankCode eq '03040000' }">selected='selected'</c:if> value="03040000">华夏银行</option>
					<option <c:if test="${item.payBankCode eq '03090000' }">selected='selected'</c:if> value="03090000">兴业银行</option>
					<option <c:if test="${item.payBankCode eq '03100000' }">selected='selected'</c:if> value="03100000">浦发银行</option>
					<option <c:if test="${item.payBankCode eq '03060000' }">selected='selected'</c:if> value="03060000">广发银行</option>
					<option <c:if test="${item.payBankCode eq '03020000' }">selected='selected'</c:if> value="03020000">中信银行</option>
					<option <c:if test="${item.payBankCode eq '03030000' }">selected='selected'</c:if> value="03030000">光大银行</option>
					<option <c:if test="${item.payBankCode eq '04031000' }">selected='selected'</c:if> value="04031000">北京银行</option>
					<option <c:if test="${item.payBankCode eq '04012900' }">selected='selected'</c:if> value="04012900">上海银行</option>
					<option <c:if test="${item.payBankCode eq '04100000' }">selected='selected'</c:if> value="04100000">平安银行</option>
					<option <c:if test="${item.payBankCode eq '04083320' }">selected='selected'</c:if> value="04083320">宁波银行</option>
					<option <c:if test="${item.payBankCode eq '04243010' }">selected='selected'</c:if> value="04243010">南京银行</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="开始支付" class="btn" />
			</td>
		</tr>
	</table>
</form>
</div>