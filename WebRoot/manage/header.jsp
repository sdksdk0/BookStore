<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

  </head>
  
  <body>
  <center>
 <br/><br/>
    <h1>后台管理</h1>
    <br/>
    <a href="${pageContext.request.contextPath}/manage/addCategory.jsp">添加分类</a>
    <a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listCategories">查询分类</a>
    <a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBookUI">添加书籍</a>
    <a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listBooks">查询书籍</a>
    <a href="">待处理订单</a>
    <a href="">已处理订单</a>
    <br/>
     
   
</html>
