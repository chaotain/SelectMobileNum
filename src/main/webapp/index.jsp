<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<body>
	<form action="${pageContext.request.contextPath }/selectnum" method="post">
	请输入手机号：<input type="text" name="mobilenumber">
	<input type="submit" value="搜索">
	</form>
</body>
</html>
