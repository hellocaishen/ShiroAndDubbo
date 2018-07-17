<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>武霞公司后台登录系统
    </title>
	<link rel="stylesheet" type="text/css" href="page/css/style.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="page/js/jquery-1.7.2.js"></script>
    <script type="application/x-javascript">
                     addEventListener("load", function() {
                           setTimeout(hideURLbar, 0); }, false); 
                           function hideURLbar(){ 
                               window.scrollTo(0,1); 
                     }
     </script>
	<!-- //For-Mobile-Apps-and-Meta-Tags -->
  </head>
  
  <body>
     <h1>武霞公司后台登录系统</h1>
    <div class="container w3">
        <h2>现在登录</h2>
		 <h3 style="color:#f00;">${message }</h3>    
          <form action="${basePath}/login.do" method="post">
			<div class="username">
				<span class="username" style="height:19px">用户:</span>
				<input type="text" name="username" value="${user.username}" class="name" placeholder="" required="">
				<div class="clear"></div>
			</div>
			<div class="password-agileits">
				<span class="username"style="height:19px">密码:</span>
				<input type="password" name="password" value="${user.password}"  class="password" placeholder="" required="">
				<div class="clear"></div>
			</div>
			<div class="password-agileits">
				<span class="username"style="height:19px">验证码:</span>
				<input type="text" name="yzm" value="" class="yzm" placeholder="" required="">
				<img  alt="验证码"  src="<%=basePath%>image/cata.jpg" height="40" width="28%" >
				<div class="clear"></div>
			</div>
			<div class="rem-for-agile">
				<input type="checkbox" name="remember" class="remember">记住我
				<input type="hidden" name="rememberMe" value="false">
                 <br>
				<a href="#">忘记了密码</a><br>
			</div>
			<div class="login-w3">
					<input type="submit" class="login" value="Login">
			</div>
			<div class="clear"></div>
		</form>
	</div>
	<div class="footer-w3l">
		<p> 武霞公司后台登录系统</p>
	</div>
          
  </body>
  
 <script type="text/javascript">
    $(function(){
        //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
        // window.onload =function loadTopWindow(){
		 //  if (window.top!=null && window.top.document.URL!=document.URL){
			//   window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了
		 //  }
	     // }
       window.onload =function loadTopWindow(){
            if (window.top!=null && window.top.document.URL!=document.URL){
                var urlStr = document.URL;
                 //可以自己根据需求截取
                var endIndex = urlStr.indexOf('xxxxx') + 5;
                urlStr = urlStr.substring(0, endIndex);
                window.top.location= urlStr + "/timeout.jsp";  // 跳转到提示页
               // window.top.location = "<=basePath%>/timeout.do";
                //alert(document.URL);
                //alert(urlStr);
                //window.top.location= document.URL; // 不跳转到首页
            }
        }
        //刷新验证码
          $("#yzm").on("click",function(){
             var nowtime = new Date().getTime();
             this.src="<%=basePath%>image/cata.jpg?nowtime="+nowtime;
          })
          //存放
          $("input[type=checkbox]").on("click",function(){
              var bool = this.checked; 
              $("input[name=rememberMe]").val(bool);
          })
    })
   /*  TestObjectController.prototype = function(){
    
        function init(){
               alert('${basePath}');
        }
    
        init:init
    } */
 
 </script>
</html>
