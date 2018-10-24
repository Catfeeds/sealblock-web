<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty error}">
	<script>
	$(function(){
		alert("${error}");
	});
	</script>
</c:if>
<script>
	$(function() {
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$('.datetime-picker').timepicker({ 'timeFormat': 'H:i:s' });
		$("select[name='dateCate']").change(function(){
			$(".custom-d-cate").css("visibility", ($(this).val() == 'CUSTOM' ? "visible" : "hidden"));
		}).change();  
	});
</script>
<form class="search-form clear" action="<c:url value="/admin/downNoCardInvoice/index" />" method="post" style="min-height: 64px;">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="mchNo">机构号：</label>
		<input type="text" class="input-box search-input-box" placeholder="商户号" name="mchNo" value="${search.mchNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="transNo">平台流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="流水号" name="transNo" value="${search.transNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="tradeNo">下游流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="下游流水号" name="tradeNo" value="${search.tradeNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="channel">公链平台：</label>
		<utils:enum mode="sel" name="channel" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.channel}" key="EnumDownNoCardChannel" />
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="status">交易状态：</label>
		<utils:enum mode="sel" name="status" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.status}" key="EnumOrderStatus" />
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="dateCate">订单日期：</label>
		<utils:enum mode="sel" name="dateCate" needDefaultValue="全部" cssName="input-box search-input-box" value="${search.dateCate}" key="EnumDateCate" />
	</div>
 	<div class="ms-col-fix-100 clear">
		<label for="startDate" class="custom-d-cate">From：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>">
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="endDate" class="custom-d-cate">To：</label>
		<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>">
	</div>
	<div class="ms-col-fix-100 clear" style="width: 400px;">
		<label for="keywords">受理时间：</label>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="开始时间" name="startTime" value="${search.startTime}"></input>
		<span style="float:left;">~</span>
		<input type="text" class="input-box search-input-box datetime-picker " placeholder="结束时间" name="endTime" value="${search.endTime}"></input>
		<script>
		</script>
	</div>

<!-- Entity没有statusDesc字段 -->
<%--  <div class="ms-col-fix-100 clear">
	<label for="statusDesc">状态描述：</label>
	<input type="text" class="input-box search-input-box" placeholder="状态描述" name="statusDesc" value="${search.statusDesc}"></input>
</div>  --%>
	
	  <div class="ms-col-fix-300 clear" >
		<utils:hideColumn defaultVal="清算额,代理分润,平台分润,机构流水号" />
	</div>
	
	 <input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<!-- <div class="ms-col-fix-all clear"> -->
	<input type="submit" value="搜索" class="btn"></input>
	<!-- </div> -->
</form>
<form action="<c:url value="/admin/downNoCardInvoice/export" />" target="_blank" method="post" class="export-form">
	<input type="submit" value="导出" class="btn btn-orange export-btn" ></input>
</form>
<%--  <form action="<c:url value="/admin/downNoCardInvoice/checkTotal" />" method="post" style="position: absolute; right: 2.5%; top: 50px;">
	<input type="submit" value="同步" class="btn btn-orange" ></input>
</form> --%>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构</td>
		<td>货币</td>
		<td>公链</td>
		<td>流水号</td>
		<td>标题</td>
		<td align="right">交易额</td>
		<td align="right">手续费</td>
		<td align="right">清算额</td>
		<td align="right">代理分润</td>
		<td align="right">平台分润</td>
		<td>机构流水号</td>
		<td>受理时间</td>
		<td>交易状态</td>
		<td>状态时间</td>
		<td>结算状态</td>
		<td>操作</td>
	</tr>
	
	<c:set var="rowNum" value="0" />
	
	<c:set var="price" value="0" />
	<c:set var="merchantFee" value="0" />
	<c:set var="downRealPrice" value="0" />
	<c:set var="agtProfitAmt" value="0" />
	<c:set var="profit" value="0" />
	
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td><c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)</td>
			<td>${item.coin}</td>
			<td><utils:enum mode="lbl" value="${item.channel}" key="EnumDownNoCardChannel" /></td>
			<td><c:out value="${item.transNo}" /></td>
			<td class="longtext-hide" style="max-width: 120px;"><c:out value="${item.subject}" /></td>
			<td align="right">
				${item.price}&nbsp;
			</td>
			<td align="right">
				${item.merchantFee}&nbsp;
			</td>
			<td align="right">
				${item.downRealPrice}&nbsp;
			</td>
			<td align="right">
				${item.agtProfitAmt}&nbsp;
			</td>
			<td align="right">
				${item.profit}&nbsp;
			</td>
			<td><c:out value="${item.tradeNo}" /></td>
			<td>
				<fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
			</td>
			<td class="longtext-hide" style="max-width: 120px;">
				<label style="weight: bold;"><utils:enum mode="lbl" value="${item.status}" key="EnumOrderStatus" /></label> - <c:out value="${item.statusDesc}" />
			</td>
			<td>
				<fmt:formatDate value="${item.statusChgDate}" pattern="MM/dd HH:mm:ss"/>
			</td>
			<td >
				<label style="weight: bold;"><utils:enum mode="lbl" value="${item.fundStatus}" key="EnumOrderStatus" /></label>
			</td>
			<td>
				<a href="<c:url value="/admin/downNoCardInvoice/detail?id=${item.id}" />" class="choose-link operate-detail">详情</a>
				<%-- <a href="<c:url value="/admin/downNoCardInvoice/check/${item.id}" />" class="choose-link operate-check">同步</a> --%>
				<c:if test="${item.status eq 'SUCCESS' or item.status eq 'FAIL'}"><a href="<c:url value="/admin/downNoCardInvoice/notify/${item.id}" />" class="choose-link operate-check">通知</a></c:if>
			</td>
			<c:set var="rowNum" value="${rowNum + 1 }" />
			<c:set var="price" value="${price + item.price }" />
			<c:set var="merchantFee" value="${merchantFee + item.merchantFee }" />
			<c:set var="downRealPrice" value="${downRealPrice + item.downRealPrice }" />
			<c:set var="agtProfitAmt" value="${agtProfitAmt + item.agtProfitAmt }" />
			<c:set var="profit" value="${profit + item.profit }" />
		</tr>
	</c:forEach>
	
	<c:if test="${rowNum gt 0 }">
		<tr class="list-item" style="font-weight: bold;">
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><label style="font-weight: bold; text-align: right;">合计（本页）</label></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td align='right'><label style="font-weight: bold; ">
				${price}</label>
			</td>
			<td align='right'><label style="font-weight: bold; ">
				${merchantFee}</label>
			</td>
			<td align='right'><label style="font-weight: bold; ">
				${downRealPrice}</label>
			</td>
			<td align='right'><label style="font-weight: bold; color: blue;">
				${agtProfitAmt}</label>
			</td>
			<td align='right'><label style="font-weight: bold; color: blue;">
				${profit}</label>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:if>
</table>
<div style="border: none; text-align: right;width: 98%;margin: 0px auto; color: #1C1C1E; font-size: 0.9rem;">
	<utils:pager></utils:pager>
</div>