<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-注册</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="register_con">
		<div class="l_con fl">
			<a class="reg_logo"><img src="images/logo02.png"></a>
			<div class="reg_slogan">足不出户 · 新鲜每一天</div>
			<div class="reg_banner"></div>
		</div>

		<div class="r_con fr">
			<div class="reg_title clearfix">
				<h1>用户注册</h1>
				<a href="login.jsp">登录</a>
			</div>
			<div class="reg_form clearfix">
				<form
					action="${ pageContext.request.contextPath}/UserServlet?method=register"
					method="post">
					<ul>
						<li><label>用户名:</label> <input type="text" name="username"
							id="user_name"> <span class="error_tip">提示信息</span></li>
						<li><label>密码:</label> <input type="password" name="password"
							id="pwd"> <span class="error_tip">提示信息</span></li>
						<li><label>确认密码:</label> <input type="password"
							name="cpassword" id="cpwd"> <span class="error_tip">提示信息</span>
						</li>
						<li><label>邮箱:</label> <input type="text" name="email"
							id="email"> <span class="error_tip">提示信息</span></li>
						<li><label>姓名：</label> <input type="text" name="name"
							id="name"></li>
						<li class="sexs"><label>性别：</label> <input type="radio"
							name="sex" id="sex" value="1" /> <label>男</label> <input
							type="radio" name="sex" id="sex" value="2" /> <label>女</label>
						</li>
						<li><label>电话：</label> <input type="text" name="telephone"
							id="telephone"></li>
						<li><label>生日：</label> <input type="date" name="birthday"
							id="birthday" value="1994-05-21" style="text-align: center;" /></li>

						<li class="agreement"><input type="checkbox" name="allow"
							id="allow" checked="checked"> <label>同意”天天生鲜用户使用协议“</label>
							<span class="error_tip2">提示信息</span></li>
						<li class="reg_sub"><input type="submit" value="注 册" name="">
						</li>
					</ul>
				</form>
			</div>

		</div>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>