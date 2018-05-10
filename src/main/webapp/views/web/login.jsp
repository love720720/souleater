<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
%>
<!DOCTYPE html>
<html>
<head>
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="alternate icon" type="image/png" href="<%=basePath%>/images/favicon.png">
<link rel="stylesheet" href="<%=basePath%>/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/amazeui.datatables.min.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/app.css"/>
<style>
  .header {
    text-align: center;
  }
  .header h1 {
    font-size: 200%;
    color: #333;
    margin-top: 30px;
  }
  .header p {
    font-size: 14px;
  }
</style>
</head>
<body class="theme-black">
	<div class="am-g tpl-g">
		<div class="tpl-login">
			<div class="tpl-login-content">
				<div class="tpl-login-logo"></div>
				<div class="am-form tpl-form-line-form">
					<div class="am-form-group">
						<input type="text" class="tpl-form-input" id="userName" placeholder="请输入账号"/>
					</div>
					<div class="am-form-group">
						<input type="password" class="tpl-form-input" id="password" placeholder="请输入密码"/>
					</div>
					<div class="am-form-group tpl-login-remember-me">
						<input id="remember-me" type="checkbox">
						<label for="remember-me"> 记住密码 </label>
					</div>
					<div class="am-form-group">
						<button type="button" class="am-btn am-btn-primary am-btn-block tpl-btn-bg-color-success tpl-login-btn">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>/js/jquery-2.2.3.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/js/amazeui.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/js/amazeui.dialog.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/js/app.js" type="text/javascript"></script>
	<script src="<%=basePath%>/js/util/checkUtil.js" type="text/javascript"></script>
	<script src="<%=basePath%>/js/login/login.js" type="text/javascript"></script>
</body>
</html>