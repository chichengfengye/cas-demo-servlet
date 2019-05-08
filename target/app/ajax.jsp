<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home</title>
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
</head>
<body>
	<h2>ajax登录</h2>
	<div>
		<form id="loginForm" method="post" action="https://sso.tianditu.gov.cn:8443/cas/login?service=http://demo.tianditu.gov.cn:8081/home">
		    <input type="text" id="username" name="username" value="" title="请输入用户名" placeholder="用户名" class="login1form_control login1uname"/>
		    <input type="password" id="password" name="password" title="请输入密码" placeholder="密码" class="login1form_control login1pword login1m-b"/>
		    <input type="hidden" name="lt" id="lt" value=""/>
		    <input type="hidden" name="execution" id="execution" value=""/></span>
		    <input type="hidden" name="authcode" id="authcode" value=""/></span>
		    <input type="hidden" name="_eventId" id="_eventId" value="submit"/>
		    <input type="hidden" name="from" id="from" value="zj"/>
		   <!--  <input type="hidden" name="service" id="service" value="http://demo.tianditu.gov.cn:8081/home"/> -->
		    <input type="submit" name="submit" id="submit" accesskey="l" value="LOGIN" />
		</form>
	</div>
	<div>
		<input type="button" value="jsonp登录" onclick="accessLogin();" /> 
	</div>

	<script type="text/javascript">
	
        function getMessage(rt){
        	console.log(rt);
        	//debugger;
        	$("#username").val("huangbin@tianditu.com");
        	$("#password").val("123456");
        	$("#lt").val(rt.lt);
        	$("#execution").val(rt.execution);
        	$("#authcode").val("1111");
        	$("#from").val("zj");
        	$("#_eventId").val("submit");
        	$("#submit").click();
        	//$("#submit").val("LOGIN");
        	///$("#loginForm").submit();
        	/* var url = "https://sso.tianditu.gov.cn:8443/cas/login";
        	var url = "/httpLogin";
        	var data = {};
        	data.execution = rt.execution;
        	data.lt = rt.lt;
        	data.username="casuser";
        	data.password="Mellon";
        	data.submit = "LOGIN";
        	data._eventId = "submit";
        	$.ajax({  
                url: url,  
                type: 'POST',  
                async:false,  
                data:data,  
                //dataType: 'json',  
                timeout: 1000,  
                error: function(req, msg, error){
                	console.log(msg);
                	console.log(error);
                },  
                success: function(result){  
                    console.log(result);  
               }  
             });   */
        }
        function accessLogin(){
        	var url = "https://sso.tianditu.gov.cn:8443/cas/login?action=console&service=http://demo.tianditu.gov.cn:8081/home";
        	url = "https://sso.tianditu.gov.cn:8443/cas/login?action=console&service=http://demo.tianditu.gov.cn:8081/home";
        	//url = "https://sso.tianditu.gov.cn:8443/cas/login?action=console&service=http://app.tianditu.gov.cn/home";
        	$.ajax({  
                type : "get",  
                async:false,  
                url : url,  
                dataType : "jsonp",
                jsonp: "callback",
                jsonpCallback: "getMessage",
                error: function(req, msg, error){
                	console.log(msg);
                	console.log(error);
                },
                success : function(data){  
                   console.log(data);  
                }  
                  
            });  
        }
        function init(){
        	
        }
        $(document).ready(function(){
    	   // login();
    	});
    
    </script>
	<!-- <iframe id= "login_iframe" name="login_iframe" style="width: 800px;height: 500px;" 
  src="http://sso.tianditu.gov.cn:8080/cas/login?&service=http://demo.tianditu.gov.cn:8081/home"></iframe> -->
	

</body>
</html>