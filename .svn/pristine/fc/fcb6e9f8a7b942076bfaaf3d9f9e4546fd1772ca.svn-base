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
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>入网注册</legend> 
		<form action="<spring:url value="/ws/trans/subMerchant/createUser"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 机构用户标识:</td>
					<td class="field"><input type="text" name="mchUserNo" value="ORG<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyMMddHHmmssSSS"/>" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 用户姓名:</td>
					<td class="field"><input type="text" name="userName" value="张三" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 身份证号:</td>
					<td class="field"><input type="text" name="userCertId" value="321323190010105339" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 联系电话:</td>
					<td class="field"><input type="text" name="userPhone" value="18951812474" /></td>
				</tr>
				<tr>
					<td class="label">开通货币(可选项目):</td>
					<td class="field"><input type="text" name="enabledCoin" value="ETH" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">2</span>开通货币交易</legend> 
		<form action="<spring:url value="/ws/trans/subMerchant/openUserCoin"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 平台用户标识:</td>
					<td class="field"><input type="text" name="userNo" value="" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 开通货币:</td>
					<td class="field"><input type="text" name="enabledCoin" value="ETH,USDT" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">3</span>查询用户账户信息</legend> 
		<form action="<spring:url value="/ws/trans/subMerchant/userBalQuery"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 平台用户标识:</td>
					<td class="field"><input type="text" name="userNo" value="" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">4</span>变更用户信息</legend> 
		<form action="<spring:url value="/ws/trans/subMerchant/updateUser"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 平台用户标识:</td>
					<td class="field"><input type="text" name="userNo" value="" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 用户姓名:</td>
					<td class="field"><input type="text" name="userName" value="张三" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 身份证号:</td>
					<td class="field"><input type="text" name="userCertId" value="321323190010105339" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 联系电话:</td>
					<td class="field"><input type="text" name="userPhone" value="18951812474" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>