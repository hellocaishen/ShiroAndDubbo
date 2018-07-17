<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="page/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
	  <base href="<%=basePath%>">
	  <title>武霞系统
	  </title>
	  <meta http-equiv="pragma" content="no-cache">
	  <meta http-equiv="cache-control" content="no-cache">
	  <meta http-equiv="expires" content="0">
	  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	  <meta http-equiv="description" content="This is my page">
	  <link rel="stylesheet" href="page/webLog/css/sccl.css">
	  <link rel="stylesheet" type="text/css" href="page/webLog/skin/qingxin/skin.css" id="layout-skin"/>
	  <script type="text/javascript" src="page/js/jquery-1.7.2.js"></script>
	  <script type="text/javascript" src="page/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="page/js/easyui/jquery.easyui.min.js"></script>
  </head>
  
  <body>
     <!--显示当前登录用户名-->
    	<div class="layout-admin">
		<header class="layout-header">
			<span class="header-logo">武霞后台管理平台</span> 
			<a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
			<ul class="header-bar">
				<li class="header-bar-role">
				     <img width="40" height="40"  style="border-radius:50%;" src="<shiro:principal property='headurl'/>"> 
				    <!-- <img   src=""> -->
				</li>
				<li class="header-bar-role"><a href="javascript:;">超级管理员</a></li>
				<li class="header-bar-nav">
					<a href="javascript:;"><shiro:principal property="username"/><i class="icon-font" style="margin-left:5px;">&#xe60c;</i></a>
					<ul class="header-dropdown-menu">
						<li><a href="javascript:;">个人信息</a></li>
						<li><a href="<%=basePath%>logout.do;">切换账户</a></li>
						<li><a href="<%=basePath%>logout.do;">退出</a></li>
					</ul>
				</li>
				<li class="header-bar-nav"> 
					<a href="javascript:;" title="换肤"><i class="icon-font">&#xe608;</i></a>
					<ul class="header-dropdown-menu right dropdown-skin">
						<li><a href="javascript:;" data-val="qingxin" title="清新">清新</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="molv" title="墨绿">墨绿</a></li>
						
					</ul>
				</li>
			</ul>
		</header>
		<aside class="layout-side">
			<ul class="side-menu">
			  
			</ul>
		</aside>
		
		<div class="layout-side-arrow"><div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div></div>
		
		<section class="layout-main">
			<div class="layout-main-tab">
				<button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
                <nav class="tab-nav">
                    <div class="tab-nav-content">
                        <a href="javascript:;" class="content-tab active" data-id="home.html">首页</a>
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
			</div>
			<div class="layout-main-body">
				<iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="<%=basePath%>page/weather-master/home.html" frameborder="0" data-id="home.html" seamless></iframe>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="page/webLog/lib/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="page/webLog/js/sccl.js"></script>
	<script type="text/javascript" src="page/webLog/js/sccl-util.js"></script>
	<script type="text/javascript">

	</script>
</html>
