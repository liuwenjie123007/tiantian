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
				<li><a href="UserServlet?method=getUserInfo" class="active">· 个人信息</a></li>
				<li><a href="OrdersServlet?method=getOrder&pageIndex=1">· 全部订单</a></li>
				<li><a href="user_center_site.jsp">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
			<div class="info_con clearfix">
				<h3 class="common_title2">基本信息</h3>
				<ul class="user_info_list">
					<li><span>用户名：</span>${user.name }</li>
					<li><span>联系方式：</span>${user.telephone }</li>
					<li><span>联系地址：</span>${orders.address }</li>
				</ul>
			</div>

			<h3 class="common_title2">最近浏览</h3>
			<div class="has_view_list">
				<ul class="goods_type_list clearfix">
					<li><a href="detail.jsp"><img
							src="images/goods/goods003.jpg"></a>
						<h4>
							<a href="detail.jsp">大兴大棚草莓</a>
						</h4>
						<div class="operate">
							<span class="prize">￥16.80</span> <span class="unit">16.80/500g</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div></li>

					<li><a href="#"><img src="images/goods/goods004.jpg"></a>
						<h4>
							<a href="#">吐鲁番梨光杏</a>
						</h4>
						<div class="operate">
							<span class="prize">￥5.50</span> <span class="unit">5.50/500g</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div></li>

					<li><a href="#"><img src="images/goods/goods005.jpg"></a>
						<h4>
							<a href="#">黄肉桃</a>
						</h4>
						<div class="operate">
							<span class="prize">￥10.00</span> <span class="unit">10.00/500g</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div></li>

					<li><a href="#"><img src="images/goods/goods006.jpg"></a>
						<h4>
							<a href="#">进口西梅</a>
						</h4>
						<div class="operate">
							<span class="prize">￥28.80</span> <span class="unit">28.8/500g</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div></li>

					<li><a href="#"><img src="images/goods/goods007.jpg"></a>
						<h4>
							<a href="#">香梨</a>
						</h4>
						<div class="operate">
							<span class="prize">￥6.45</span> <span class="unit">6.45/500g</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div></li>
				</ul>
			</div>
		</div>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>