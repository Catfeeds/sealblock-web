<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>

<link href="<%=resourceUrl%>/resources/js/bootstrap/bootstrap-select.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/js/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=resourceUrl%>/resources/js/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/bootstrap/bootstrap.min.js"></script>

<style>
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
</style>
<form action="<c:url value="/admin/passwayRouteMerchant/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">公链:</td>
			<td class="liner-box-right">
				<utils:enum mode="sel" name="channel" extraAttr="required='required'" needDefaultValue="请选择" cssName="input-box " value="${item.channel}" key="EnumDownNoCardChannel" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币简称(全局唯一):</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" required="required" placeholder="币简称" name="coin" value="${item.coin}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">币名称:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" required="required" placeholder="账户编号" name="coinName" value="${item.coinName}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态:</td>
			<td class="liner-box-right">
				<utils:enum mode="sel" name="status" extraAttr="required='required'" needDefaultValue="请选择" cssName="input-box" value="${item.status}" key="EnumDownUserStatus" />
			</td>
		</tr>
		<c:if test="${not empty item.createDate }">
			<c:if test="${not empty item.signKeyMd5 }">
			<tr style="">
				<td class="liner-box-left ">原签名密钥:</td>
				<td class="liner-box-right">
					<label style="color: red; font-weight: bold;">${item.signKeyMd5}</label>&nbsp;<label style="color: blue; ">（仅显示明文密钥的MD5值）</label>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="liner-box-left required">签名Key（明文）:</td>
				<td class="liner-box-right">
					<input type="hidden" name="signKey" value="${item.signKey }" />
					<input type="text" class="input-box" placeholder="留空表示不修改" name="signKeyPlain" />
				</td>
			</tr>
			
			<c:if test="${not empty item.encKeyMd5 }">
			<tr style="">
				<td class="liner-box-left ">原加密密钥:</td>
				<td class="liner-box-right">
					<label style="color: red; font-weight: bold;">${item.encKeyMd5}</label>&nbsp;<label style="color: blue; ">（仅显示明文密钥的MD5值）</label>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="liner-box-left required">加密Key（明文）:</td>
				<td class="liner-box-right">
					<input type="hidden" name="encKey" value="${item.encKey }" />
					<input type="text" class="input-box" placeholder="加密Key（明文）" name="encKeyPlain" />
				</td>
			</tr>
		</c:if>
		<c:if test="${empty item.createDate }">
			<tr>
				<td class="liner-box-left required">签名Key（明文）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" required="required" placeholder="签名Key（明文）" name="signKeyPlain" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left required">加密Key（明文）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" required="required" placeholder="加密Key（明文）" name="encKeyPlain" />
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left ">服务时间:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box search-input-box datetime-picker " placeholder="开始时间" name="startTime" value="<utils:dateFormat value="${item.startTime}" />"></input>
				<label >~</label>
				<input type="text" class="input-box search-input-box datetime-picker " placeholder="结束时间" name="endTime" value="<utils:dateFormat value="${item.endTime}" />"></input>
				<script>
					$(function() {
						$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
					});
				</script>
				<p style="color: red;">默认为00:00:00表示不启用时间条件</p>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">价格精度（位数）：</td>
			<td class="liner-box-right">
				<input class="input-box" style="width: 60px;" maxlength="3" required type="text" placeholder="" name="priceScale" value="${item.priceScale}">
				<p style="color: red;">精确到小数点后第几位</p>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔最低额：</td>
			<td class="liner-box-right">
				<input class="input-box" style="width: 120px;" maxlength="10" required type="text" placeholder="" name="minPerAmt" value="${item.minPerAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔最高额：</td>
			<td class="liner-box-right">
				<input class="input-box" style="width: 120px;" maxlength="10" required type="text" placeholder="" name="minPerAmt" value="${item.maxPerAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left ">额外配置:</td>
			<td class="liner-box-right">
				<textarea class="input-box" style="height: 80px;" name="extra">${item.extra }</textarea>
			</td>
		</tr>
		<%-- <tr>
			<td class="liner-box-left ">备注:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="备注  " name="remark" value="${item.remark}" />
			</td>
		</tr> --%>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" id="confirmBtn" />
			</td>
		</tr>
	</table>
</form>