<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<input type='hidden' id='com_curr_pg' name='currentPage' value='${pager.currentPage}' />
<table>

    <tr>
         <td>每页
            <select name="pageSize" id="cus_pageSize" onchange="findPgSize()">
                <option value="20" 
                      <c:if test="${pager.pageSize=='20'}">selected="selected"</c:if>
                >20</option>
                <option value="15"
                      <c:if test="${pager.pageSize=='15'}">selected="selected"</c:if>
                >15</option>
                <option value="10"
                       <c:if test="${pager.pageSize=='10'}">selected="selected"</c:if>
                >10</option>
                <option value="5" 
                       <c:if test="${pager.pageSize==5}">selected="selected"</c:if>
                >5</option>
                 <option value="2" 
                       <c:if test="${pager.pageSize==2}">selected="selected"</c:if>
                >2</option>
            </select>
                条
         </td>
         <c:choose>
             <c:when test="${pager.totalPageSize==0}">
             			<td><a href="#" disabled>上一页</a></td>
             			<td>               0/0             </td>
                        <td><a href="#" disabled>上一页</a></td>
                        <td>          共  0 条记录                  </td>
             </c:when>
             <c:otherwise>
                        <td><a onclick="findPageInfo(${pager.currentPage},-1)">上一页</a></td>
             			<td>      共  ${pager.currentPage}/${pager.totalPageSize} 页          </td>
             			<td> <a onclick="findPageInfo(${pager.currentPage},1)">下一页</a></td>
             			<td>            共  ${pager.totalNum} 条记录                                       </td>
             </c:otherwise>
         </c:choose>
    </tr>
</table>
<script type="text/javascript">
       function  findPageInfo(currPage,num){
                 var lastPg="${pager.totalPageSize}";
                 var formObj = document.forms[0];
                 formObj.method="post";
                 if(num<0){
                      if(currPage<=1){
                            return;       
                      }else{
                            currPage += num;
                      }
                 }else{
                    if(currPage == parseInt(lastPg)){
                            return;       
                      }else{
                            currPage += num;
                      }
                 }
                // var ip = ">";
                // formObj.append(ip);
                $("#com_curr_pg").val(currPage);
                 formObj.submit();
       }
       function findPgSize(){
                 var pageSize = 0;
                 var formObj = document.forms[0];
                 formObj.method="post";
                 pageSize= $('#cus_pageSize option:selected') .val();//选中的值
          		 var ip = "<input type='hidden' name='pageSize' value=" +pageSize+" />";
                 formObj.append(ip);
                 formObj.submit();
       }
 </script>