<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");
%>
<header class="pay-bar">
	<div class="tc">支付结果</div>
	<div class=" aui-pull-right" style="position: absolute; right: 1rem; top: 1.2rem;">
		<img src="<%=resourceUrl%>/resources/mf/img/ico_logo.png" width="100px">
	</div>
</header>
<main class="MyContent bottom0 orderPayCon">
	<h1 id="error-info" style="text-align: center; padding: 80px 0; color: #888;">${error}</h1>
	<c:if test="${not empty inv and inv.status ne '00' and inv.status ne '02'}">
		<img src="<%=resourceUrl%>/resources/mf/img/load.gif" id="loading-img" style="max-width:100%; margin: 0 auto; display:block;"/>
		<script>
			var index = 1;
			function check() {
				if (index > 8) {
					$("#error-info").html("支付未完成，或状态未同步");
					$("#loading-img").remove();
					return;
				}
				var url = '/directpay/check/${invId}';
				if(index == 5) {
					url = '/directpay/sync/${invId}';
				}
				$.ajax({
					url: window.CMS_URL + url,
					type: 'POST',
					dataType: 'json',
					timeout: 30000
				}).done(function(res){
					if (res.state=='Successful') {
						window.location.href = "${returnurl}00";
					} 
					else {
						if (res.payload == '02') {
							$("#error-info").html(res.message);
							$("#loading-img").remove();
						}
						else {
							setTimeout(check, 3000);
						}
					}
				});
				index++;
			}
			setTimeout(check, 3000);
		</script>
	</c:if>
</main>