<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h1>您购物车中的商品信息</h1>
    <c:if test="${empty sessionScope.cart.items}">
    	您还没有购买任何商品
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
    	<table border="1" width=800">
    		<tr>
    			<th>书名</th>
    			<th>作者</th>
    			<th>单价</th>
    			<th>数量</th>
    			<th>小计</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>${me.value.book.name}</td>
	    			<td>${me.value.book.author}</td>
	    			<td>${me.value.book.price}</td>
	    			<td><input type="text" size="3" id="number" name="number" value="${me.value.number}" onchange="changeNumber(this,'${me.value.number}','${me.key}')"/></td>
	    			<td>${me.value.price}</td>
	    			<td>
	    				<a href="javascript:delOneItem('${me.key}')">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td align="right" colspan="6">
    				总数量：${sessionScope.cart.number}&nbsp;&nbsp;
    				总金额：${sessionScope.cart.price}&nbsp;&nbsp;
    				<a href="">去结算</a>
    			</td>
    		</tr>
    	</table>
    </c:if>
    <script type="text/javascript">
    	function delOneItem(bookId){
    		var sure = window.confirm("确定要删除吗？");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=delOneItem&bookId="+bookId;
    		}
    	}
    	function changeNumber(inputObj,oldNumber,bookId){
    		var value = inputObj.value;
    		//验证值必须是自然整数
    		if(!/^[1-9][0-9]*$/.test(value)){
//     			改为1
				//inputObj.value=1;
				alert("请输入正确的数量");
				this.focus();
				return;
    		}
    		var sure = window.confirm("确定要修改数量为"+value+"吗？");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=changeNum&bookId="+bookId+"&num="+value;
    		}else{
    			//改回原来的数量
    			inputObj.value = oldNumber;
    		}
    	}
    </script>
  </body>
</html>
