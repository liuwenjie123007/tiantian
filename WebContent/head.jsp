<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎来到天天生鲜!</div>
			<div class="fr">
				<div class="login_info fl">
					欢迎您：<em>张 山</em>
				</div>
				<div class="login_btn fl">
					<c:choose>
						<c:when test="${empty username }">
							<a href='login.jsp'>登录</a>
							<span>|</span>
							<a href='register.jsp'>注册</a>
						</c:when>
						<c:otherwise>
							 [${username }] <a href='UserServlet?method=logout'> 注销</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="user_link fl">
					<span>|</span> <a href="user_center_info.jsp">用户中心</a> <span>|</span>
					<a href="cart.jsp">我的购物车</a> <span>|</span> <a
						href="OrdersServlet?method=getOrder&pageIndex=1">我的订单</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>