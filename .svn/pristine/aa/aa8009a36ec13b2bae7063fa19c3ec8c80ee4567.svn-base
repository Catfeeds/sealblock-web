<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");
%>
<style>
.error {
	margin-left:0;
}
.CardNum {
	padding-left:1rem;
}
input+.error {
	display:block;
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
    height:28px;
}
.input-wapper {
    width: 100%;
    position: absolute;
    left: 0;
    padding-left: 7rem;
}
.card-exp {
    width: 6rem;
    text-align: center;
}
.card-exp-split {
	line-height:3.6rem;
}
.card-phone {
    width: 12rem;
}
.regcode-btn {
    position: absolute;
    right: 1rem;
    height: 2.6rem;
    line-height: 2.6REM;
    color: #FFF;
    border: none;
    border-radius: 5px;
    width: 8rem;
    text-align: center;
    margin-top: 0.4rem;
    cursor: pointer;
}
.card-type {
	background: url('<%=resourceUrl%>/resources/mf/img/ico_arrow_down.png') no-repeat 96% 50%;
    background-size: 10px 5px;
}
</style>
<header class="pay-bar">
	<div class="tc">交易异常</div>
	<div class=" aui-pull-right" style="position: absolute;right: 1rem;top: 1.2rem;"><img src="<%=resourceUrl%>/resources/mf/img/ico_logo.png" width="100px"></div>
</header>
<main class="MyContent bottom0 orderPayCon">
	<form action="<c:url value="/directpay/verify"/>/${inv.id}" id="submit-form" method="post">
	<input type="hidden" name="id" value="${inv.id}"/>
	<div class="OrderDeatilBox" style="min-height: 200px;">
		<h1>${error}</h1>
	</div>
	<div class="MyHeight"></div>
</form>
</main>
<c:if test="${not empty targetUrl }">
<script>
	setTimeout(function() {
		window.location.href = "<c:url value='${targetUrl}' />";
	}, 1500);
</script>
</c:if>