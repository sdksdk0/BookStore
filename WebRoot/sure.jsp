<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>title</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

  </head>
  
  <body>
    <form action="https://www.yeepay.com/app-merchant-proxy/node" method="post">
    	<input type="hidden" name="p0_Cmd" value="${p0_Cmd}"/>
    	<input type="hidden" name="p1_MerId" value="${p1_MerId}"/>
    	<input type="hidden" name="p2_Order" value="${p2_Order}"/>
    	<input type="hidden" name="p3_Amt" value="${p3_Amt}"/>
    	<input type="hidden" name="p4_Cur" value="${p4_Cur}"/>
    	<input type="hidden" name="p5_Pid" value="${p5_Pid}"/>
    	<input type="hidden" name="p6_Pcat" value="${p6_Pcat}"/>
    	<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc}"/>
    	<input type="hidden" name="p8_Url" value="${p8_Url}"/>
    	<input type="hidden" name="pa_MP" value="${pa_MP}"/>
    	<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse}"/>
    	<input type="hidden" name="hmac" value="${hmac}"/>
    	<input type="hidden" name="p9_SAF" value="${p9_SAF}"/>
    	<input type="hidden" name="pd_FrpId" value="${pd_FrpId}"/>
    </form>
    <script type="text/javascript">
    	document.forms[0].submit();
    </script>
  </body>
</html>
