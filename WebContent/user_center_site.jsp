<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-用户中心</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
	</div>

	<div class="main_con clearfix">
		<div class="left_menu_con clearfix">
			<h3>用户中心</h3>
			<ul>
				<li><a href="UserServlet?method=getUserInfo">· 个人信息</a></li>
				<li><a href="OrdersServlet?method=getOrder&pageIndex=1">· 全部订单</a></li>
				<li><a href="user_center_site.jsp" class="active">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
			<h3 class="common_title2">收货地址</h3>
			<div class="site_con">
				<c:choose>
					<c:when test="${empty orders }">
						<dl>
							<dt>当前地址：</dt>
							<dd>请设置收件地址！</dd>
						</dl>
					</c:when>
					
					<c:otherwise>
					<dl>
						<dt>当前地址：</dt>
						<dd>${orders.address }（${orders.name } 收） ${orders.telephone }</dd>
					</dl>
					</c:otherwise>
				</c:choose>
				
				
			</div>
			<h3 class="common_title2">编辑地址</h3>
			<div class="site_con">
				<form action="OrdersServlet?method=addOrderInfo" method="post">
					<div class="form_group">
						<label>收件人：</label> <input type="text" name="name">
					</div>
					<div class="form_group form_group2">
						<label>详细地址：</label>
						<textarea class="site_area" name="address"></textarea>
					</div>
					<div class="form_group">
						<label>手机：</label> <input type="text" name="telephone">
					</div>

					<input type="submit" name="" value="提交" class="info_submit">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>