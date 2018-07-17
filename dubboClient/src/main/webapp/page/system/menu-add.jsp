<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>新增菜单
    </title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
         <form action="<%=basePath%>system/add.do" id="userform">
             <input type="hidden"  name="resourceId" value="${resource.resourceId}">
             <table>
                  <tr>
                      <td> 是否父级菜单 :</td>
                      <td><input type="radio" name="isParent" value="0" 
                                    <c:if test="${not empty resource.url}">checked</c:if> />YES
                                   <input type="radio" name="isParent" value="1" <c:if test="${empty resource.url}">checked</c:if> /> NO
                      </td>
                  </tr>
                  <tr>
                       <td>父级菜单:</td>
                       <td>
                           <select name="resource.resourceId" >
                             <c:forEach items="${map}" var="parmenu">
                                <option value="${parmenu.key}">${parmenu.value}</option>
                             </c:forEach>
                           </select>
                       </td>
                   </tr>
                  <tr><td>菜单url:</td><td><input type="text" name="url" value="${resource.url}"/></td></tr>
                  <tr><td>操作名称:</td><td><input type="text" name="operationName" value="${resource.operationName}"></td></tr>
                  <tr><td>资源名称:</td><td><input type="text" name="name" value="${resource.name}"></td></tr>
                  <tr><td><input type="submit" value="保存"/></td><td><input type="button" value="取消"></td></tr>
             </table>
         </form>
  <script >
       
  </script>
  </body>
</html>
