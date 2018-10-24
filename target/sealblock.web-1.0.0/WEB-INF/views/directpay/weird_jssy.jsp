<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <title>快捷支付</title>
    <meta charset="UTF-8" />
    <meta content="IE=edge, chrome=1" http-equiv="X-UA-Compatible" />
    <meta content="webkit" name="renderer" />
    <meta content="" name="description" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
    <meta content="no-cache" http-equiv="pragma" />
    <meta content="no-cache" http-equiv="cache-control" />
    <meta content="0" http-equiv="expires" />
    <meta http-equiv="X-Frame-Options" value="SAMEORIGIN" />
</head>
<body>
<form action="https://epay.elecirc.net:26322/api" id="form" method="POST">
    <input name="version" type="hidden" value=""/>
    <input name="agent" type="hidden" value=""/>
    <input name="sign" type="hidden" value="" />
    <input name="amount" type="hidden" value="" />
    <input name="credit" type="hidden" value="" />
    <input name="cvn" type="hidden" value="" />
    <input name="front_url" type="hidden" value="" />
    <input name="expiry" type="hidden" value="" />
    <input name="idno" type="hidden" value="" />
    <input name="debit" type="hidden" value="" />
    <input name="mobile" type="hidden" value="" />
    <input name="name" type="hidden" value="" />
    <input name="noise" type="hidden" value="" />
    <input name="out_order_id" type="hidden" value="" />
    <input name="sumer_fee" type="hidden" value="" />
    <input name="sumer_amt" type="hidden" value="" />
</form>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		var jsonResult = ${html};
		for(var k in jsonResult) {
			$('input[name="'+k+'"]').val(jsonResult[k]);	
		}
		setTimeout(function(){$('#form').submit();}, 0);
	});
</script>
</body></html>