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
input+.error, .show-msg {
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
.orderPayCon .show-arrow {
    top: 1.1rem;
}
.orderPayCon .hide-arrow {
    top: 3rem;
}
</style>
<header class="pay-bar">
	<div class="tc">订单支付</div>
	<div class=" aui-pull-right" style="position: absolute;right: 1rem;top: 1.2rem;"><img src="<%=resourceUrl%>/resources/mf/img/ico_logo.png" width="100px"></div>
</header>
<main class="MyContent bottom0 orderPayCon">
	<form action="<c:url value="/directpay/confirm"/>" id="submit-form" method="post">
	<input type="hidden" name="id" value="${inv.id}"/>
	<input type="hidden" name="preOrderId" value=""/>
	<div class="OrderDeatilBox">
		<%-- <p>商户名称：<span class="color_999" id="merchant-name">${mch.name}</span></p> --%>
		<p>结算卡号：<span id="card-info" class="color_red">${inv.cardNoFormat}</span></p>
		<div class="show-arrow" id="show-arrow"><img src="<%=resourceUrl%>/resources/mf/img/ico_arrow_down.png"></div>
		<div class="MoreDetailBox" id="MoreDetailBox">
			<div class="hide-arrow" id="hide-arrow"><img src="<%=resourceUrl%>/resources/mf/img/ico_arrow_up.png"></div>
			<p>结算卡持卡人：<span class="color_999" id="acc-name">${inv.accNameFormat}</span></p>
			<p>身份证号：<span class="color_999" id="bank-name">${inv.accIdCardFormat}</span></p>
		</div>
	</div>
		
	<div class="aui-toast" style="display:none" id="loading">
		<div class="aui-toast-loading"></div>
		<div class="aui-toast-content">处理中</div>
	</div>
	
	<div class="CardPayBox" style="margin-top:1.5rem;">
		<p class="CardNum fix">
			<span class="Cardtext fl">金额</span>
			<span class="input-wapper fl">
				<input type="text" class="card-input" placeholder="请输入金额" value="${inv.price}" readonly>
				<span id="regcode-msg" class="show-msg" style="background-color: #4f4fd8; color: #fff; display: none;">短信已发送</span>
			</span>
		</p>
		<p class="CardNum fix btNone">
			<span class="Cardtext fl">支付卡</span>
			<span class="input-wapper fl" >
				<input type="text" class="card-input" placeholder="请输入支付卡号" value="${inv.payCardNoFormat}" readonly>
			</span>
		</p>
		<p class="CardNum fix btNone" <c:if test="${hideMobileFlg eq 'Y' }">style="display:none;"</c:if>>
			<span class="Cardtext fl">手机号</span>
			<span class="input-wapper fl" >
				<input type="hidden"  name="userToken" id="userToken" value="${inv.userToken}">
				<input type="text" class="card-input" placeholder="预留手机号" name="userTokenMask" id="userTokenMask" value="${inv.userTokenFormat}">
			</span>
		</p>
		<p class="CardNum fix btNone">
			<span class="Cardtext fl">验证码</span>
			<span class="input-wapper fl" >
				<input type="text" class="card-input card-phone" placeholder="验证码" name="regcode" >
				<input type="button" class="regcode-btn bg_red" onclick="sendCode(this);" value="获取验证码">
			</span>
		</p>
		<div class="fix conBtnBox"><div class="ml10 mr10"><input id="pay-submit" type="button" class="pay-btn bg_red" value="确认付款"></div></div>
	</div>
	<div class="MyHeight"></div>
</form>
	
</main>
<script>
$("#show-arrow").click(function () {
	$("#MoreDetailBox").slideDown(200);
	$("#show-arrow").hide();
});
$("#hide-arrow").click(function () {
	$("#MoreDetailBox").slideUp(200);
	$("#show-arrow").show();
});

var isLock = false;
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
});
var vali = $("#submit-form").validate({
	errorElement: "span",
	rules: {
		userToken: {
			required: true,
			pattern:"1[0-9]{2}[0-9\*]{4}[0-9]{4}"
		},
		regcode: {
			required: true,
			pattern: "[0-9]{6}"
		}
	},
	messages: {
		userTokenMask: {
			required: "手机号不能为空",
			pattern:"手机号不正确"
		},
		regcode: {
			required: "验证码不能为空",
			pattern: "验证码格式不正确"
		}
	}
});

$(function(){
	$("#pay-submit").click(function(){
		if ($(this).is(".bg_red")) {
			if($('#userTokenMask').val().indexOf("****") == -1) {
				$('#userToken').val($('#userTokenMask').val());
			}
			if (vali.form()) {
				var preOrderId = $("input[name=preOrderId]").val();
				if (preOrderId) {
					$(this).removeClass("bg_red").css("color","#FF5E4E");
					$("#submit-form").submit();
				} else {
					_WOO.modal.alert("验证码不匹配,请退出重新支付");
				}
			} else {
				setTimeout(function(){
					$("input+.error").slideUp(200);
				},1500);
				
			}
		}
	});
});

var timer = null;
function sendCode(el) {
	var $this = $(el);

	if($('#userTokenMask').val().indexOf("****") == -1) {
		$('#userToken').val($('#userTokenMask').val());
	}
	
	if ($this.val() == '获取验证码' && vali.element($("input[name=userToken]"))) {
		var left = 30;
		$this.val(left--);
		$this.removeClass("bg_red").css("color","#FF5E4E");
		sendSms($("input[name=userToken]").val());
		if (timer) {
			clearInterval(timer);
			timer = null;
		}
		timer = setInterval(function(){
			if (left > 0) {
				$this.val(left--);
				$this.removeClass("bg_red").css("color","#FF5E4E");
			} else {
				$this.val('获取验证码');
				$this.addClass("bg_red").css("color","#FFF");
				if (timer) {
					clearInterval(timer);
					timer = null;
				}
			}
		}, 1000);
	}
}

function sendSms(phone) {
	_WOO.com2.send({
		url : "/directpay/regcode",
		data: {
			id:'${inv.id}',
			userToken: phone
		}
	}).done(function(ret){
		if (ret.state != 'Successful') {
			if (ret.message && ret.message.length > 4) {
				_WOO.modal.alert(ret.message);
			} else {
				_WOO.modal.alert("获取短信失败");
			}
		}
		else {
			$("#regcode-msg").show();
			setTimeout(function() {
				$("#regcode-msg").fadeOut(600);
			}, 300);
			$("input[name=preOrderId]").val(ret.payload);
		}
	});
}
</script>