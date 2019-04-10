<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="guest_cart fr">
		<c:choose>
			<c:when test="${empty cart }">
				<a href="cart.jsp" class="cart_name fl">我的购物车</a>
				<div class="goods_count fl" id="show_count">0</div>
			</c:when>
			<c:otherwise>
				<a href="cart.jsp" class="cart_name fl">我的购物车</a>
				<div class="goods_count fl" id="show_count">${cart.count }</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>