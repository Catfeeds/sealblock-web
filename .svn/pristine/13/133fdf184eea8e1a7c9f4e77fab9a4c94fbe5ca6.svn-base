<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="<c:url value='/resources/js/common/qrcode.js' />"></script>

<div style="width: 1200px; margin: 30px auto; padding:20px 100px 80px; background-color: #eee;">
	<c:if test="${empty item.authBindDate }">
		<h2 style="margin: 15px;">绑定谷歌验证，激活账户</h2> 
	</c:if>
	<c:if test="${not empty item.authBindDate }">
		<h2 style="margin: 15px;">变更谷歌验证</h2> 
	</c:if>
	
	<form method="post" enctype="multipart/form-data" action="<c:url value="/admin/user/renewAuth" />">
		<input type="hidden" name="id" value="${item.id}">
		<table class="liner-box">
			<c:if test="${not empty error}">
				<tr class="liner-error">
					<td class="liner-box-left"></td>
					<td class="liner-box-right">${error}</td>
				</tr>
			</c:if>
			<tr>
				<td class="liner-box-left ">&nbsp;</td>
				<td class="liner-box-right">
					<div>
						<div id="qrcode_1" style="padding-bottom: 5px;"></div>
						<input class="input-box" type="text" style="width: 260px;" id="authKey" name="authKey" value="${item.authKey }" />
						<script>
						  (function(){
						      var qrcode = new QRCode('qrcode_1');
						      $('#authKey').on('change', function() {
						          qrcode.makeCode("otpauth://totp/" + encodeURIComponent("<sec:authentication property='principal.username' />") + "@Quickpay?secret=" + encodeURIComponent(this.value) + "&issuer=Quickpay");
						      }).change();
						      // new QRCode(document.getElementById('qrcode_1'), '${item.authKey }');
						  })();
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">二次验证</td>
				<td class="liner-box-right">
					<input class="input-box" type="text" style="width: 100px;" name="authCode" value="${item.authCode }" />
					<p>绑定数据后，提交验证成功，即表示绑定成功</p>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td> 
				<td class="liner-box-right">
					<input type="submit" value="提交" class="btn" />
				</td>
			</tr>
		</table>
	</form>
	
	<br>
	<hr style="border-top: 1px solid #999;">
	<br>
	<div>
		<h3>请下载安装Google Authoticator应用</h3>
		<ul>
			<li>安卓版本: <a href="/download/Google_Authenticator_v5.00_apkpure.com.apk" target="_blank">下载</a></li>
			<li>iOS版本: <a href="https://itunes.apple.com/cn/app/google-authenticator/id388497605?mt=8" target="_blank">https://itunes.apple.com/cn/app/google-authenticator/id388497605?mt=8</a></li>
		</ul>
	</div>
</div>
