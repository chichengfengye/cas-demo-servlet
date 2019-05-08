<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <script type="" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" ></script>
   <script >
        function cas(){
        	var data = {};
        	data.username="casuser";
        	data.password="Mellon";
        	data.service="http://demo.tianditu.com";
        	var url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
        	$.ajax({
        		url : url,
        		type : "post",
        		data : data,
        		dataType : 'json',
        		contentType : "application/x-www-form-urlencoded; charset=utf-8",
        		success : function(resObj) {
        			console.log(resObj);
        			$("#rt").html(resObj);
        		}
        	});
        }
   </script>
</head>
<body>

<h2>Hello World!</h2>
<button onclick="cas();">cas</button>
<div id="rt"></div>
</body>
</html>
