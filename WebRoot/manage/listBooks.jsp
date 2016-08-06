<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>
<h3>当前位置：查询书籍</h3>
    <c:if test="${empty page.records}">
    	<h2>您还没有添加任何书籍，<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBookUI">添加</a></h2>
    </c:if>
    <c:if test="${!empty page.records}">
    	<table border="1" width="900">
    		<tr>
    			<th>图片</th>
    			<th>书名</th>
    			<th>作者</th>
    			<th>售价</th>
    			<th>描述</th>
    			<th>所属分类</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${page.records}" var="b" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>
	    				<img src="${pageContext.request.contextPath}/../images/${b.path}/${b.filename}"/>
	    			</td>
	    			<td>${b.name}</td>
	    			<td>${b.author}</td>
	    			<td>${b.price}</td>
	    			<td>${b.des}</td>
	    			<td>${b.category.name}</td>
	    			<td>
	    				[<a href="javascript:alert('略')">修改</a>]
	    				[<a href="javascript:alert('略')">删除</a>]
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    	<%@ include file="/commons/page.jsp"%>
    </c:if>
  </body>
</html>
