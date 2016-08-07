<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h3>新用户注册</h3>
    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=login" method="post">
    	<table border="1" width="600">
    		<tr>
    			<td>用户名：</td>
    			<td>
    				<input name="username"/>
    			</td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td>
    				<input type="password" name="password"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="登陆"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
