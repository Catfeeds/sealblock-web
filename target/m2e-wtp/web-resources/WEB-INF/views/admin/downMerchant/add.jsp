<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>

<script type="text/javascript" src="<c:url value='/resources/js/common/jquery.md5.js' />"></script>
<form action="<c:url value="/admin/downMerchant/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
		<c:if test="${not empty item.id }">
		<div class="ms-row ms-border" style="margin: 0 0.5%;">
			<div class="ms-header">开通货币</div>
			<c:if test="${not empty item.id }">
				<table class="list-wapper" style="margin-bottom: 5px;">
					<tr class="list-header">
						<td width="110px">名称</td>
						<td width="100px">交易费率(‰)</td>
						<td width="100px">单笔费用</td>
						
						<!-- <td class="special">单笔最小金额(元)</td>
						<td class="special">单笔最大金额(元)</td>
						<td class="special">日累计限额(元)</td> -->
						<!-- <td>状态</td> -->
					</tr>
					<c:forEach var="item" items="${products}" varStatus="status">
						<c:set var="inputflg" value="required" />
						<c:set var="checkflg" value="checked='checked'" />
						<c:if test="${not empty item.status and item.status eq 'PENDING' }">
							<c:set var="inputflg" value="readonly='readonly' " />
							<c:set var="checkflg" value="" />
						</c:if>
						<tr class="list-item">
							<td width="110px">
								<input type="hidden" name="coinArr" value="${item.coin }">
								<input type="checkbox" ${checkflg }  class="input-check" style='width: 20px;' name="selCoinArr" id="product${item.coin }" value="${item.coin }">
								<label for="product${item.coin }"><c:out value="${item.coinName }" /><span style='font-weight:normal; color: #999;'>(<c:out value="${item.coinDescription }" />)</span></label>
							</td>
							<td width="100px">
								<input type="text" ${inputflg } class="input-box" placeholder="单位：千分之" name="feeRatioArr" value="${item.feeRatio}" />
							</td>
							<td width="100px">
								<input type="text" ${inputflg } class="input-box" name="addFeeAmtArr" value="${item.addFeeAmt}" />
							</td>
							<%-- <td class="special">
								<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="minPerAmtArr" value="${item.minPerAmt}" />
							</td>
							<td class="special">
								<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="maxPerAmtArr" value="${item.maxPerAmt}" />
							</td class="special">
							<td class="special">
								<input type="text" ${inputflg } class="input-box" placeholder="空或者0，则不限额" name="maxTotalAmtArr" value="${item.maxTotalAmt}" />
							</td> --%>
							<%-- <td>
								<select name="status" required ${inputflg }  class="input-box">
									<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未激活</option>
									<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>正常</option>
									<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停</option>
								</select>
							</td> --%>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<br />
		</c:if>
		<div class="ms-col-5 ms-border">
			<div class="ms-header">基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">机构号:</td>
					<td class="liner-box-right">
						<input type="hidden" class="input-box" name="mchNo" value="${item.mchNo}" />
						<input type="text" class="input-box" placeholder="机构编号" value="${item.mchNo}" disabled />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">所属代理商:</td>
					<td class="liner-box-right">
						<select name="agentNo" class="input-box">
							<option value="">--请选择--</option>
							
							<c:forEach items="${agents}" var="agent">
								<option value="${agent.agentNo}" <c:if test="${item.agentNo eq agent.agentNo}">selected</c:if> >${agent.name} - ${agent.agentNo}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">类别:</td>
					<td class="liner-box-right">
						<utils:enum mode="sel" name="mercCategory" needDefaultValue="请选择" cssName="input-box " value="${item.mercCategory}"  extraAttr="required='required' " key="EnumMercCategory" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">机构名称:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构名称" required="required" name="name" value="${item.name}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">T0授信比:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="授信比例" name="creditRatio" value="${item.creditRatio}" />%
					</td>
				</tr>
				<tr>
					<td class="liner-box-left ">结算日期:</td>
					<td class="liner-box-right">
						<input type="hidden" name="settleDate" value="${item.settleDate}" />
						<input type="text" class="input-box" placeholder="结算日期" name="settleDate" disabled value="${item.settleDate}" />
					</td>
				</tr>
			
			</table>
		</div>
		<div class="ms-col-5 ms-border">
			<div class="ms-header">配置信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left required">状态:</td>
					<td class="liner-box-right">
						<select name="status" class="input-box">
							<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未使用</option>
							<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>使用中</option>
							<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停使用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">收支明细:</td>
					<td class="liner-box-right">
						<select name="enableBalChgFlg" class="input-box">
							<option value="false" <c:if test="${empty item.enableBalChgFlg or not item.enableBalChgFlg}">selected</c:if>>关闭</option>
							<option value="true" <c:if test="${not empty item.enableBalChgFlg and item.enableBalChgFlg}">selected</c:if>>打开</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">异步通知:</td>
					<td class="liner-box-right">
						<select name="enableNotify" class="input-box">
							<option value="false" <c:if test="${empty item.enableNotify or not item.enableNotify}">selected</c:if>>不启用</option>
							<option value="true" <c:if test="${not empty item.enableNotify and item.enableNotify}">selected</c:if>>启用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">信任IPs:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="逗号分割" name="limitIps" value="${item.limitIps}" />
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">加密Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="加密Key" name="encKey" value="${item.encKey}" />
						<a href="javascript:void(0);" class="btn btn-orange" onclick="genkey();">自动生成</a>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left required">签名Key:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" style="width: 180px;" placeholder="签名Key" name="signKey" value="${item.signKey}" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="liner-box-one-line">
		<input type="submit" id="confirmBtn" value="提交" class="btn" />
	</div>
	<br/><br/><br/>
</form>
<script type="text/javascript">
	function genkey () {
		var d = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$%^&*()#@!_+=<>.,/?";
		var r = Math.random() * 80;
		var s = 30;
		while((s--)>0){
			c = Math.round(Math.random() * 80);
			r += d.substring(c, c + 1);
		}
		var k = $.md5(r.substring(0, 20)).toUpperCase();
		$('input[name=encKey]').val(k.substring(0, 16));
		$('input[name=signKey]').val(k.substring(16));
		$('input[name=payKey]').val($.md5(r.substring(20)).toUpperCase().substring(1, 17));
	}
	$(function () {
		$('.selectpicker').selectpicker({
	        'selectedText': 'cat'
	    });
		
		$('input[name=selCoinArr]').click(function() {
			var inputs = $(this).closest('tr').find('.input-box');
			if($(this).is(":checked")) {
				inputs.removeAttr('readonly').attr("required", "required");
			}
			else {
				inputs.attr('readonly', 'readonly').removeAttr("required");
			}
		});
	});
</script>