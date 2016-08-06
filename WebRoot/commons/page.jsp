<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
    	第${page.pageNum}页/共${page.totalPageSize}页&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath}${page.url}&num=1">首页</a>&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.prePageNum}">上一页</a>&nbsp;&nbsp;
    	                    <!--   page.setUrl("/servlet/ManageServlet?op=listBooks"); -->
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.nextPageNum}">下一页</a>&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.totalPageSize}">尾页</a>&nbsp;&nbsp;
    	<select id="jump" onchange="jump(this)">
    		<c:forEach begin="1" end="${page.totalPageSize}" var="n">
    		   
    			<option value="${n}" ${page.pageNum==n?'selected="selected"':''}>${n}</option>
    		</c:forEach>
    	</select>
    	&nbsp;&nbsp;
    	<input type="text" size="3" id="num"/><input type="button" value="跳转" onclick="jump1()"/>
    	<script type="text/javascript">
    		function jump(selectObj){
    			window.location.href="${pageContext.request.contextPath}${page.url}&num="+selectObj.value;
    		}
    		function jump1(){
    			//得到用户输入的页码
				var num = document.getElementById("num").value;
				//alert(num);
				//验证必须是个自然整数
				if(!/^[1-9][0-9]*$/.test(num)){
					alert("请输入正确的页码?");
					return;
				}
				//页码有没有超出范围
				if(num>${page.totalPageSize}){
					alert("页码超出范围");
					return;
				}
				window.location.href="${pageContext.request.contextPath}${page.url}&num="+num;
			}
    	</script>
    </div>