<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h1>您的订单信息如下</h1>
    <c:if test="${empty orders}">
    	您还没有购买任何商品
    </c:if>
    <c:if test="${!empty orders}">
    	<table border="1" width="638">
    		<tr>
    			<th>订单号</th>
    			<th>订单金额</th>
    			<th>数量</th>
    			<th>状态</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${orders}" var="o" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>${o.ordernum}</td>
	    			<td>${o.price}</td>
	    			<td>${o.number}</td>
	    			<td>
	    				<c:choose>
	    					<c:when test="${o.status==0}">
	    						未付款
	    					</c:when>
	    					<c:when test="${o.status==1}">
	    						已付款
	    					</c:when>
	    					<c:otherwise>
	    						有待开发
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			<td>
	    				<c:choose>
	    					<c:when test="${o.status==0}">
	    						<a href="${pageContext.request.contextPath}/pay.jsp?ordernum=${o.ordernum}&price=${o.price}">付款</a>
	    					</c:when>
	    					<c:when test="${o.status==1}">
	    						跟踪
	    					</c:when>
	    					<c:otherwise>
	    						有待开发
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    </c:if>
  </body>
</html>
