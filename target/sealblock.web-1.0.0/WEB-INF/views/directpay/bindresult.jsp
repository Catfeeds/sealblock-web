<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");
%>
<header class="pay-bar">
	<div class="tc">绑卡结果</div>
	<div class=" aui-pull-right" style="position: absolute; right: 1rem; top: 1.2rem;">
		<img src="<%=resourceUrl%>/resources/mf/img/ico_logo.png" width="100px">
	</div>
</header>
<main class="MyContent bottom0 orderPayCon">
	<h1 id="error-info" style="text-align: center; padding: 80px 0; color: #888;">${error}</h1>
</main>