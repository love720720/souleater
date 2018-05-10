<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
%>
<!DOCTYPE html>
<html>
<head>
<title>Soul Eater</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="<%=basePath%>/css/app/app.css">
</head>
<body>
	
	<script type="text/javascript" src="<%=basePath%>/js/require.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/main.js" charset="UTF-8"></script>
	<script type="text/javascript">
		require(['WeiXinUtil','App'], function(WeiXinUtil, App){
			WeiXinUtil.init();
		});
	</script>

<%-- 	<script type="text/javascript" src="<%=basePath%>/js/jquery-2.2.3.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="//res.wx.qq.com/open/js/jweixin-1.0.0.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-2.2.3.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/util/path-util.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/util/weixin-util.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=basePath%>/js/app/app.js" charset="UTF-8"></script> --%>
</body>
</html>