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
    
    <title>菜单列表
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
          #author{
              display: block;
              text-decoration: none;
              height: 25px;
              width:60px;
              text-align: center;
              border:1px solid #1E9Fef;
              margin-top:5px;
              margin-left: 20px;
              margin-bottom: 10px;
              padding-top:5px;
              cursor: pointer;
          }
	</style>
  </head>
  
  <body>
         <form action="<%=basePath%>system/possessres.do" id="userform">
             <input type="hidden" id="userId" name="userId" value="${userId}">
            <a id="author">授权</a>
            <table id="datatable"   cellspacing="0" cellpadding="0">
                    <thead>
                        <tr style="background-color:#ccc">
                             <th class="comshort">序号</th>
                             <th class="comshort">资源名称</th>
                             <th class="comshort">url</th>
                             <th>操作属性</th>
                             <th>时间</th>
                             <th>创建人</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${resources}" var="resource" varStatus="index">
                                       <c:if test="${index.count%2==0}">
                                          <tr style="background-color:#ccc">
                                       </c:if>
                                       <c:if test="${index.count%2==1}">
                                           <tr>
                                       </c:if>
                                            <%--<td class="comshort">${(pager.currentPage-1)*pager.pageSize+index.count}</td> --%>
                                            <td class="comshort"><input type="checkbox" class="resid"
                                                <c:if test="${resource.resource==null}">
                                                       pid="-1"
                                                </c:if>
                                                <c:if test="${resource.resource!=null}">
                                                             pid="${resource.resource.resourceId}"
                                                </c:if>
                                                 rid="${resource.resourceId}" >
                                            </td>
	                                        <td class="comshort">${resource.name}</td>
	                                         <td class="comshort">${resource.url}</td>
	                                         <td>${resource.operationName}</td>
	                                         <td><fmt:formatDate value="${resource.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                                         <td>${resource.createUser}</td>
                                         </tr>
                   </c:forEach>
                   </tbody>
            </table>
           <%@ include file="../common/page.jsp" %>
         </form>
  <script >
         var systemAction = function(jsons){
             var jsons = jsons;
         };
         $(function (){
            new systemAction().init();
         })
         systemAction.prototype = {
             init: function () {
                   //初始化加载页面进行数据组装
                    var datas = "${Exists}";
                    this.jsons = JSON.parse(datas);
                    //var jsons = $.extend({},this.jsons);
                   var jsons = this.jsons;
                   $("input[type=checkbox]").each(function(){
                         var _this = $(this);
                         var vid = _this.attr("rid");
                         var bool =  _this.isContanins(jsons,vid);
                         if(bool){
                              _this.attr("checked",true);
                         }
                   })

                 //引用绑定事件
                 this.resIdEvent(jsons);
                 this.authres(jsons);
             },
             resIdEvent:function(jsons){
                 $("input[type=checkbox]").click(function(){
                     var _this = $(this);
                     var pid = _this.attr("pid");
                     var vid = _this.attr("rid");
                     if($(this).attr("checked")=="checked"){
                             //选中的子、父菜单列。确认不是顶层
                             if(pid>0){
                                     //直接选中父项
                                    $("input[pid='-1']").attr("checked",true);
                                    $("input[rid="+pid+"]").attr("checked",true);
                                    //加入父级元素
                                     var bool =  _this.isContanins(jsons,pid);
                                     if(!bool){
                                         jsons.push(parseInt(pid));
                                     }
                               }
                                var bool =  _this.isContanins(jsons,vid);
                                if(!bool){
                                    jsons.push(parseInt(vid));
                                }
                         }else{
                                 //顶级
                                 if(pid<0){
                                     $("input[type=checkbox]").attr("checked",false);
                                     jsons.splice(0,jsons.length-1);
                                 }else{
                                 //有就移除，没有就不用移除.从而获取新数据
                                 //移除当前所在级子级数据
                                 $("input[pid="+vid+"]").each(function(){
                                     var _this_=$(this);
                                     var sid = _this_.attr("vid");
                                     //移除所有子项
                                     _this.removerArr(jsons,sid,1);
                                 })
                                 //移除当前级、当前父级数据
                                 var bools = false;
                                 $("input[pid="+pid+"]").each(function(){
                                     var _this_=$(this);
                                     if(_this_.attr("checked")=="checked"){
                                         bools=true;
                                     }
                                 })
                                 //移除当前项
                                 _this.removerArr(jsons,vid,1);
                                 if(!bools){
                                     $("input[rid="+pid+"]").attr("checked",false);
                                     _this.removerArr(jsons,pid,1);
                                 }
                              }
                         }

                 })
             },
             authres:function(jsons){
                      //进行授权
                     $("#author").click(function(){
                          var vas = (jsons==null) || (jsons.length==0) ? null:jsons.toString();
                          var uId = $("#userId").val();
                          <%--$.post("<%=basePath%>system/authorres.do",{"rIds":vas,"uId":uId},function(data){--%>
                                <%--if(data==1 || data =="1"){--%>
                                     <%--alert("success");--%>
                                <%--}--%>

                          <%--});--%>
                         $.ajax({
                             url: "<%=basePath%>system/authorres.do",
                             data: {"rIds":vas,"uId":uId},
                             //type: "post",
                             success: function (data) {
                                 if(data==1 || data =="1"){
                                     alert("success");
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
