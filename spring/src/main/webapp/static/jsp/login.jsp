<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"  />
	<title>斐讯兑奖系统(/WEB-INF/resources/html/lo)</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.default.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font.css" />
	<style type="text/css"> 
	html,body {
	    color:#b8b8b8;
	    background: url(${pageContext.request.contextPath}/static/images/login_.png); 
	    background-repeat:repeat;
	     
	}
	
	td{
	    height:35px;  
	}
	 
	input[type=text]{
	    width:230px; 
	    height:28px;
	    border:0; 
	    background: #ededed;
	    outline:none; 
	    margin-left:15px;
	    font-size:13px;
	    color:#b8b8b8;
	}
	
	input[type=text]:hover {
	    border:0;   
	    background: #ededed;
	}
	
	input[type=text]:focus {
	    border:0;
	    box-shadow:none;
	    background:#ededed;
	    color:#b8b8b8;
	}
	
	input[type=password]{
	    width:230px; 
	    height:28px;
	    border:0; 
	    background: #ededed;
	    outline:none; 
	    margin-left:15px;
	    font-size:13px;
	    color:#b8b8b8;
	}
	input[type=password]:focus {
		border:1px solid #ededed;
		box-shadow:0 0 6px #ededed;
		background: #ededed;
		color: #b8b8b8;
	}
	#serverId{
		width: 100%;
		color: #999999;
		cursor: pointer;
		font-size: 11px;
		text-decoration: underline;
	}
	</style> 

</head> 
<body>
    <div id="bar"  style="background:#f2a548; width:1%; height:6px; border-radius:0 4px 4px 0; position:absolute; top:0; display:none;"> &nbsp;</div>
    <div class="login_logo">
        <img src="" title="" />
    </div>
    <div class="login_iframe">
        <div class="login_title">
            <!-- <image src="resources/images/loginTitle.png"/> -->
        </div>

        <table class="login_tbl1">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/static/images/loginusername.png" style="margin-left:15px;"/>
                </td>
                <td>
                    <input id="account" type="text" name="account" value=""  placeholder="用户名"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="login_td1">
                    <img src="${pageContext.request.contextPath}/static/images/loginLine.png"/>
                </td>
            </tr>
            <tr>
                <td>
                   <img src="${pageContext.request.contextPath}/static/images/loginpassword.png" style="margin-left:15px;"/> 
                </td>
                <td>
                    <input id="password" type="password" name="password" value="" placeholder="密码"/> 
                </td>
            </tr>
        </table>

        <table style="width:327px; margin-top:15px; margin:0 auto;">
            <tr>
            	<td>
            		<label id="loginErr" style="color:red;font-weight:normal;padding-left:50px;display: none"></label>
            	</td>
                <td style="text-align:right;">
                    <input type="checkbox" name="rember" style="width:15px;"  id="saveUsername">
                    <span>记住用户名</span>
                </td>
            </tr>
        </table>
        <div style="text-align:center; margin-top:5px;">
            <button id="login" class="btn loginBtn" type="button">
				<span class="loginBtnspan">登录</span>
			</button> 
        </div>
    </div><br/>
</body> 
</html>