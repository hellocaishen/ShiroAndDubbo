<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表
    </title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>page/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>page/js/cusjs.js"></script>
	<style type="text/css">
	    #datatable thead tr{
		       width:100%;
	    }
	     #datatable th, #datatable td, #datatable tr{
		       border:1px solid #000;
	     }
	      #datatable th, #datatable td{
	           width:11%;
	     }
	     .comshort{
	           width:8%;
	     }
	      #datatable{
	         border:1px solid #000;
	     }
	     
	     tr,td,th{
	          vertical-align:middle;
	          text-align:center;
	     }
	</style>
  </head>
  
  <body>
         <form action="<%=basePath%>user/list.do" id="userform">
            <table id="datatable"   cellspacing="0" cellpadding="0">
                    <thead>
                        <tr style="background-color:#ccc">
                             <th class="comshort">序号</th>
                             <th class="comshort">用户名</th>
                             <th class="comshort">年龄</th>
                             <th>电话</th>
                             <th>邮箱</th>
                             <th>时间</th>
                             <th>创建人</th>
                             <th  >当前操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user" varStatus="index">
                                       <c:if test="${index.count%2==0}">
                                          <tr style="background-color:#ccc">
                                       </c:if>
                                       <c:if test="${index.count%2==1}">
                                           <tr>
                                       </c:if>
                                            <td class="comshort">${(pager.currentPage-1)*pager.pageSize+index.count}</td> 
	                                        <td class="comshort">${user.username}</td>
	                                         <td class="comshort">${user.age}</td>
	                                         <td>${user.telephone}</td>
	                                         <td>${user.email}</td>
	                                         <td>${user.createTime}</td>
	                                         <td>${user.createUser}</td>
	                                         <td>
                                                 <shiro:hasRole  name="admin">
                                                    <a href="<%=basePath%>system/pores.do?currentPage=1&uName=${user.username}&uId=${user.userId}">授权</a>
                                                 </shiro:hasRole>
                                                 <a class="enab"  isable="${user.isable}"  uId ="${user.userId}">
                                                 <c:if test="${user.isable==0}">禁用</c:if>
                                                 <c:if test="${user.isable==1}">启用</c:if>
                                                </a>
                                                 <shiro:hasRole name="admin">
                                                     <a class="dele" uId ="${user.userId}">删除</a>
                                                 </shiro:hasRole>
                                             </td>
                                </tr>
                   </c:forEach>
                   </tbody>
            </table>
           <%@ include file="../common/page.jsp" %>
         </form>
  <script >
         var userAction = function(){};
         $(function (){
            new userAction().init();
         })
         userAction.prototype = {
             init: function () {
                 //禁用 or 启用  在绑定链接中，如果不需要跳转链接 就在函数，回调函数e
                 $(".enab").click(function (e) {
                     var _this = $(this)
                     var uid = _this.attr("uId");
                     var isable = _this.attr("isable");
                     $.ajax({
                         url: "<%=basePath%>user/disable/" + uid + ".do",
                         data: {"isable": isable},
                         type: "post",
                         success: function (data) {
                             if (data == 1) {
                                 var text = _this.text() == "禁用" ? "启用" : "禁用";
                                 _this.text(text);
                             }
                         },
                         error: function () {
                             alert("系统繁忙，请稍后再试")
                         }
                     })

                 })
                 //删除
                 $(".dele").click(function(e){
                     var  _this = $(this);
                     var par2 = _this.lotParent(_this,2);
                     var uid = _this.attr("uId");
                     $.ajax({
                         url: "<%=basePath%>user/delete/"+uid+".do",
                         type: "post",
                         success:function(data){
                             if(data==1){
                                 // var _par = _this.parent();
                                 // var par2 = $(_par).parent();
                                // $(par2).empty();
                                 setTimeout(function(){
                                     //如果有条件则需带上条件
                                     location.href="<%=basePath%>user/list.do";
                                 },1000)
                             }
                         },
                         error: function () {
                             alert("系统繁忙，请稍后再试")
                         }
                     })
                 })

             }



         }

  </script>
  </body>
</html>
