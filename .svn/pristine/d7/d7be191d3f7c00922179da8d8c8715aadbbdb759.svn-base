<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	$(function() {
		bindForm();
	});
</script>
<div id="tabs-down" style="overflow-y: auto;">

	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">0</span>绑定支付参数</legend> 
		<form action="#" method="post" class="enc-bind-form">
			<table class="editor">
				<tr>
					<td class="label">接口版本:</td>
					<td class="field"><input type="text" id="versionNo" value="1" /></td>
				</tr>
				<tr>
					<td class="label">商户号:</td>
					<td class="field"><input type="text" id="mchNo" value="T0001" /></td>
				</tr>
				<tr>
					<td class="label">加密KEY:</td>
					<td class="field"><input type="text"  id="encKey" value="1234567812345678" /></td>
				</tr>
				<tr>
					<td class="label">签名Key:</td>
					<td class="field"><input type="text" id="signKey" value="1234" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="绑定" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>下单支付</legend> 
		<form action="<spring:url value="/ws/trans/nocard/makeOrder"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 订单号:</td>
					<td class="field"><input type="text" name="tradeNo" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单日期:</td>
					<td class="field"><input type="text" name="orderDate" value="<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyyMMddHHmmss"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 交易货币:</td>
					<td class="field"><input type="text" name="coin" value="ETH" />
						<p>如: ETH,USDT,BTC</p>
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 交易额:</td>
					<td class="field"><input type="text" name="price" value="0.01" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 平台用户标识:</td>
					<td class="field"><input type="text" name="userNo" value="AAAAAAAAAAAAAAAAA" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 订单名称:</td>
					<td class="field"><input type="text" name="subject" value="测试订单" /></td>
				</tr>
				<tr>
					<td class="label">异步通知地址:</td>
					<td class="field"><input type="text" name="notifyUrl" value="http://www.baidu.com" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">2</span>确认支付</legend> 
		<form action="<spring:url value="/ws/trans/nocard/confirmOrder"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">平台订单号:</td>
					<td class="field"><input type="text" name="transNo" value="" /></td>
				</tr>
				<tr>
					<td class="label">短信验证码:</td>
					<td class="field"><input type="text" name="smsCode" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">3</span>重新发送短信</legend> 
		<form action="<spring:url value="/ws/trans/nocard/resendSms"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">平台订单号:</td>
					<td class="field"><input type="text" name="transNo" value="" /></td>
				</tr>
				<tr>
					<td class="label">短信验证码:</td>
					<td class="field"><input type="text" name="smsCode" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">4</span>交易状态查询</legend> 
		<form action="<spring:url value="/ws/trans/nocard/orderQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">平台订单号:</td>
					<td class="field"><input type="text" name="transNo" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">5</span>机构余额查询</legend> 
		<form action="<spring:url value="/ws/trans/nocard/accBalQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">机构号:</td>
					<td class="field"><input type="text" name="mchNo" value="A0001" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>