<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	$(function() {
		bindEncForm();
	});
</script>
<div id="tabs-down" style="overflow-y: auto;">

	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>终端登录</legend> 
		<form ref-auth-key="#passwdPlain" action="<spring:url value="/ws/app/qrcode/auth"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 用户名:</td>
					<td class="field"><input type="text" name="userName" value="D0000" /></td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 密码:</td>
					<td class="field">
						<input type="hidden" class="input-pwd md5" id="passwdPlain" value="" />
						<input type="hidden" class="input-pwd tmd5" name="userPasswd"  />
						
						<input type="text" class="required-md5" ref-selector='.input-pwd' id="password" value="123456" />
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 设备标识:</td>
					<td class="field">
						<input type="text" name="deviceUid" value="UUID-20180318-0001" />
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">a</span>完成打码任务</legend> 
		<form action="<spring:url value="/ws/app/qrcode/doneSingleTask"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 流水号:</td>
					<td class="field">
						<input type="text" name="transNo" value="" />
					</td>
				</tr>
				<tr>
					<td class="label"><font style="color:red">*</font> 二维码数据:</td>
					<td class="field">
						<input type="text" name="qrcode" value="" />
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">b</span>同步订单状态</legend> 
		<form action="<spring:url value="/ws/app/qrcode/transUpload"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 交易明细:</td>
					<td class="field">
						<textarea ref-json='invs' style="height: 160px;">[
	{
		"no": "2018031921001004750552453685",
		"pe": "big***@163.com",
		"pn": "*新星",
		"rm": "AYAxkRGUF5QQAQ",
		"ta": 1,
		"tm": "20180319213354"
	}
]</textarea>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">2</span>查询可执行任务</legend> 
		<form action="<spring:url value="/ws/app/qrcode/getTask"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">3</span>查询可执行任务</legend> 
		<form action="<spring:url value="/ws/app/qrcode/doneTask"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 完成数据:</td>
					<td class="field">
						<textarea ref-json='codes'>{
	"AYAxkRGUFQYQAA": "wxp://f2f1rmwhTQoYeyNIT3hMcZcltJNF4NKLNcXR"
}</textarea>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">4</span>同步数据</legend> 
		<form action="<spring:url value="/ws/app/qrcode/upload"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label"><font style="color:red">*</font> 交易明细:</td>
					<td class="field">
						<textarea ref-json='invs' style="height: 160px;">[
	{
		"no": "2018031921001004750552453685",
		"pe": "big***@163.com",
		"pn": "*新星",
		"rm": "AYAxkRGUF5QQAQ",
		"ta": 1,
		"tm": "20180319213354"
	}
]</textarea>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">5</span>版本检查</legend> 
		<form action="<spring:url value="/ws/app/qrcode/checkVersion"></spring:url>" method="post" class="enc-service-form">
			<table class="editor">
				<tr>
					<td class="label">Version:</td>
					<td class="field"><input type="text" name="version" value="1.0.0" /></td>
				</tr>
				<tr>
					<td class="label">Device Type:</td>
					<td class="field"><input type="text" name="deviceType" value="ANDROID" /><br>ANDROID|IOS</td>
				</tr>
				<tr>
					<td class="label">deviceId:</td>
					<td class="field"><input type="text" id="deviceId" value="123456" /></td>
				</tr>
				<tr>
					<td class="label">Baidu User ID:</td>
					<td class="field"><input type="text" name="baiduUserId" value="" /></td>
				</tr>
				<tr>
					<td class="label">Baidu Token:</td>
					<td class="field"><input type="text" name="baiduToken" value="" /></td>
				</tr>
				<tr>
					<td class="label">Brand:</td>
					<td class="field"><input type="text" name="brand" value="xiaomi" /></td>
				</tr>
				<tr>
					<td class="label">OS Version:</td>
					<td class="field"><input type="text" name="osVersion" value="9.1.0" /></td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>