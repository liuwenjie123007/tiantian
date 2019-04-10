<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-登录</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/login.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="login_top clearfix">
		<a href="index.jsp" class="login_logo"><img
			src="images/logo02.png"></a>
	</div>
	<div class="login_form_bg">
		<div class="login_form_wrap clearfix">
			<div class="login_banner fl"></div>
			<div class="slogan fl">日夜兼程 · 急速送达</div>
			<div class="login_form fr">
				<div class="login_title clearfix">
					<h1>用户登录</h1>
					<a href="register.jsp">立即注册</a>
				</div>
				<p style="color: red; text-align: center;">
					<%
						if (session.getAttribute("username") != null) {
							session.removeAttribute("username");
						} else {
							if (session.getAttribute("loginfalse") == null) {

							} else {
								out.println(session.getAttribute("loginfalse"));
								session.removeAttribute("loginfalse");
							}
						}
					%>
				</p>
				<div class="form_input">
					<form
						action="${ pageContext.request.contextPath}/UserServlet?method=login"
						method="post">
						<input type="text" id='username' name="username"
							class="name_input" placeholder="请输入用户名">
						<div class="user_error">输入错误</div>
						<input type="password" name="pwd" class="pass_input"
							placeholder="请输入密码">
						<div class="pwd_error">输入错误</div>
						<div class="more_input clearfix">
							<input type="checkbox" name="" id="check"> <label>记住用户名</label>
							<a href="#">忘记密码</a>
						</div>
						<input type="submit" name="" value="登录" class="input_submit"
							onclick="saveUsername()">
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>