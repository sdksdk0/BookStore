<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>
    <h3>当前位置：添加书籍</h3>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBook" method="post">
    	<table border="1" width="438">
    		<tr>
    			<td>书名：</td>
    			<td>
    				<input name="name" id="name" />
    			</td>
    		</tr>
    		<tr>
    			<td>作者：</td>
    			<td>
    				<input name="author" id="author" />
    			</td>
    		</tr>
    		<tr>
    			<td>单价：</td>
    			<td>
    				<input name="price" id="price" />元
    			</td>
    		</tr>
    		<tr>
    			<td>图片：</td>
    			<td>
    				<input type="file" name="image" />
    			</td>
    		</tr>
    		<tr>
    			<td>描述：</td>
    			<td>
    				<textarea rows="3" cols="38" name="des"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<td>所属分类：</td>
    			<td>
    				<select name="categoryId">
    					<c:forEach items="${cs}" var="c">
    						<option value="${c.id}">${c.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    	</table>
    	<input type="submit" value="添加"/>
    </form>
  </body>
</html>
