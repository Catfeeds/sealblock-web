<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");
%>
<style>
body {
	background-color: #ccc;
}
.error {
	margin-left: 0;
}

.CardNum {
	padding-left: 1rem;
}

input+.error {
	display: block;
	position: fixed;
	top: 4.5rem;
	left: 0;
	margin: 0;
	z-index: 100;
	width: 100%;
	text-align: center;
	color: #FFF;
	background: #FF5E4E;
	line-height: 28px;
	height: 28px;
}

.input-wapper {
	width: 100%;
	position: absolute;
	left: 0;
	padding-left: 7rem;
}

</style>
<header class="pay-bar">
	<div class="tc">正在发起支付</div>
	<div class="aui-pull-right" style="position: absolute; right: 1rem; top: 1.2rem;">
		<img src="<%=resourceUrl%>/resources/mf/img/ico_logo.png" width="100px">
	</div>
</header>
<main class="MyContent bottom0 orderPayCon">
<form action="<c:url value="/directpay/verify"/>/${inv.id}"
	id="submit-form" method="post">
	<input type="hidden" name="id" value="${inv.id}" />
	<div class="aui-toast" style="display: block;">
		<div class="aui-toast-loading"></div>
		<div class="aui-toast-content" id="loadingMsg">倒计时（30s）</div>
	</div>
</form>
</main>
<script>
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	});
	var index = 1;
	function check() {
		if (index >= 10) {
			window.location.href = "<c:url value='/directpay/result/${invId}' />";
			return;
		}
		index++;
		$.ajax({
			url: "<c:url value='/directpay/checksts/${invId}' />",
			type: 'POST',
			dataType: 'json',
			timeout: 30000
		}).done(function(res){
			if (res.state == 'Successful') {
				if(res.payload.status == '01' && res.payload.url) {
					window.location.href = res.payload.url;
				}
				else if(res.payload.status != '09') {
					window.location.href = "<c:url value='/directpay/result/${invId}' />";
				}
			} 
			setTimeout(check, 3000);
		});
		index++;
	}
	var leftTime = 40;
	function countTime() {
		if(leftTime == 0) {
			window.location.href = "<c:url value='/directpay/result/${invId}' />";
			return;
		}
		$("#loadingMsg").html("倒计时（" + (leftTime--) + "s）");
		setTimeout(countTime, 1000);
	}
	
	setTimeout(check, 500);
	setTimeout(countTime, 0);
</script>