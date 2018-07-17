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
    
    <title>新增用户
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
  <%-- application/x-www-form-urlencoded 搜索不是不能上传文件，是只能上传文本格式的文件，
  multipart/form-data是将文件以二进制的形式上传，这样可以实现多种类型的文件上传 --%>
  <body>
         <form action="<%=basePath%>user/save.do" method="post" enctype="multipart/form-data" id="userform">
                <h1 id="errmsg" style="color:red;">${msg}</h1>
                <input style="display:none" type="text" name="headurl" id="headurl" value=""/>
                <table>
                     <c:if test="${! empty user.userId || user.userId>1}">
                         <tr  id="hu"><td colspan="2"><img alt="头像" src=""></td></tr>
                     </c:if>
                     <tr id="playT"><td>选择头像</td><td><input type="file" name="file" title="选择头像" onchange="uploadFile()"/></td></tr>
                     <tr><td><label> 用户名: </label></td><td><input type="text" name="username"/></td></tr>
                     <tr><td><label> 密码: </label></td><td><input type="text" name="password"/></td></tr>
                     <tr><td> <label> 年龄: </label></td><td><input placeholder="输入年龄" type="text" name="age" pattern="1[0-5]{1}[0-9]{1}|[1-9]{1}[0-9]{1}"/></td></tr>
                     <tr><td><label> 邮箱: </label> </td><td><input type="text" name="email"/></td></tr>
                     <tr><td><label> 电话: </label></td><td><input type="text" name="telephone"/></td></tr>
                     <tr><td><input type="submit" value="保存"></td><td><input type="button" value="取消"></td></tr>
                </table>
         </form>
  <script>
        function  uploadFile(){
            var fdata = new FormData($("#userform")[0]);
            $.ajax({
                url:'<%=basePath%>user/upload.do',
                contentType: false,   
                processData: false,
                dataType:"json",  
                data:fdata,
                type:'post',
                success:function(data){
                      console.log(data);
                      if(data.status){
                            $("table").remove("#hu");
                            $("#playT").before("<tr id='hu'><td colspan='2'><img width='100' height='100' alt='头像' src='"+data.Data+"'></td></tr>");
                            $("#headurl").val(data.Data);
                      }else{
                            $("#errmsg").text(data.msg);
                      }   
                },
                error:function(){
                
                }
            
            
            
            })
           
        
        }  

  </script>
  </body>
</html>
