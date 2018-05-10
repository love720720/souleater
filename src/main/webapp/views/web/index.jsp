<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
%>
<!DOCTYPE html>
<html class="no-js fixed-layout">
<head>
<title>Soul Eater</title>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta name="apple-mobile-web-app-title" content="Soul Eater"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Cache-Control" content="max-age=86400000"/>
  <link rel="icon" type="image/png" href="<%=basePath%>/images/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>/images/app-icon72x72@2x.png">
  <link rel="stylesheet" href="<%=basePath%>/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=basePath%>/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>Soul Eater</strong> <small>后台管理模板</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}">
  	<span class="am-sr-only">导航切换</span>
  	<span class="am-icon-bars"></span>
  </button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li>
      	<a href="javascript:;">
      		<span class="am-icon-envelope-o"></span>收件箱
      		<span class="am-badge am-badge-warning">5</span>
      	</a>
      </li>
      <li class="am-dropdown" data-am-dropdown>
        <a href="javascript:;" class="am-dropdown-toggle" data-am-dropdown-toggle>
          <span class="am-icon-users"></span>管理员
          <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span>资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span>设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span>退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only">
	      <a href="javascript:;" id="admin-fullscreen">
	      	<span class="am-icon-arrows-alt"></span>
	      	<span class="admin-fullText">开启全屏</span>
	      </a>
      </li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
        <li>
        	<a href="<%=basePath%>/index">
        		<span class="am-icon-home"></span>首页
        	</a>
        </li>
      </ul>
    </div>
  </div>

  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding">
        <div class="am-fl am-cf">
        	<strong class="am-text-primary am-text-lg">首页</strong>&nbsp;/&nbsp;<small>一些常用模块</small>
        </div>
      </div>
    </div>

    <footer class="admin-content-footer">
      <hr/>
      <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
    </footer>
  </div>
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="<%=basePath%>/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>/js/jquery-2.2.3.min.js"></script>
<!--<![endif]-->
<script src="<%=basePath%>/js/amazeui.min.js"></script>
<script src="<%=basePath%>/js/app.js"></script>
</body>
</html>