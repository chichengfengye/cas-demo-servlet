<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>

	<form action="/login" method="post" id="login-form">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td>
				    <!-- <input type="button" id="login" onclick="test()" value="登录" /> -->
				    <input type="submit" value="登录"/>
				</td>
			</tr>

		</table>
	</form>
	<div>
	    
	  <a href="">
	     GitLogin
	  </a>
	</div>
	<script>
		function test() {
			var form = $("#login-form");
			form.action = "/login";
			form.submit();
		}
	</script>
</body>
</html>