<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>

<center>
<h3>当前位置：查询分类</h3>
    <c:if test="${empty cs}">
    	<h2>您还没有添加任何分类，<a href="${pageContext.request.contextPath}/manage/addCategory.jsp">添加</a></h2>
    </c:if>
    <c:if test="${!empty cs}">
    	<table border="1" width="438">
    		<tr>
    			<th>选择</th>
    			<th>序号</th>
    			<th>分类名称</th>
    			<th>描述</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${cs}" var="c" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>
	    				<input type="checkbox" name="ids" value="${c.id}"/>
	    			</td>
	    			<td>${vs.count}</td>
	    			<td id="name">${c.name}</td>
	    			<td>${c.des}</td>
	    			<td>
	    				[<a href="javascript:update()">修改</a>]
	    				[<a href="javascript:del(this)">删除</a>]
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
     </center>
     <script type="text/javascript" src="../js/jquery-1.11.3.js"  ></script>
     <script type="text/javascript">

			function del(){
				var name=$(this).attr("name").val();
				
			
				//var name=$("#name").text();
				alert(name);
				
			}



	</script>
     
     
  </body>
</html>
