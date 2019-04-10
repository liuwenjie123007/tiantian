<%@page import="com.msr.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-商品详情</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="search_con fl">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
		<jsp:include page="smallcart.jsp"></jsp:include>
	</div>

	<div class="navbar_con">
		<div class="navbar clearfix">
			<div class="subnav_con fl">
				<h1>全部商品分类</h1>
				<span></span>
				<ul class="subnav">
					<li><a
						href="ProductServlet?method=findByCid&cid=1&pageIndex=1"
						class="fruit">新鲜水果</a></li>
					<li><a
						href="ProductServlet?method=findByCid&cid=2&pageIndex=1"
						class="seafood">海鲜水产</a></li>
					<li><a
						href="ProductServlet?method=findByCid&cid=3&pageIndex=1"
						class="meet">猪牛羊肉</a></li>
					<li><a
						href="ProductServlet?method=findByCid&cid=4&pageIndex=1"
						class="egg">禽类蛋品</a></li>
					<li><a
						href="ProductServlet?method=findByCid&cid=5&pageIndex=1"
						class="vegetables">新鲜蔬菜</a></li>
					<li><a
						href="ProductServlet?method=findByCid&cid=6&pageIndex=1"
						class="ice">速冻食品</a></li>
				</ul>
			</div>
			<ul class="navlist fl">
				<li><a href="">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<div class="breadcrumb">
		<a href="index.jsp">全部分类</a> <span>></span> <a
			href="ProductServlet?method=findByCid&cid=${category.cid }&pageIndex=1">${category.cname }</a>
		<span>></span> <a href="#">商品详情</a>
	</div>

	<form action="CartServlet?method=addProduct&pid=${product.pid }"
		method="post">
		<div class="goods_detail_con clearfix">
			<div class="goods_detail_pic fl">
				<c:choose>
					<c:when test="${empty product }">
					数据为空！！！
				</c:when>
					<c:otherwise>
						<img src="${product.pimage }" style="width: 350px; height: 350px;">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="goods_detail_list fr">
				<c:choose>
					<c:when test="${empty product }">
					数据为空！！！
				</c:when>
					<c:otherwise>
						<h3>${product.pname }</h3>
						<p>${product.pdesc }</p>
					</c:otherwise>
				</c:choose>
				<div class="prize_bar">
					<span class="show_pirze">¥<em> <c:choose>
								<c:when test="${empty product }">
					数据为空！！！
					</c:when>
								<c:otherwise>
								${product.shop_price }
							</c:otherwise>
							</c:choose>
					</em></span> <span class="show_unit"></span>
				</div>
				<div class="goods_num clearfix">
					<div class="num_name fl">数 量：</div>
					<div class="num_add fl">
						<input type="text" class="num_show fl" value="1"> <a
							href="javascript:;" class="add fr">+</a> <a href="javascript:;"
							class="minus fr">-</a>
					</div>
				</div>
				<div class="total">
					<c:choose>
						<c:when test="${empty product }">
					数据为空！！！
				</c:when>
						<c:otherwise>
						总价：   <em> ${product.shop_price } 元</em>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="operate_btn">
					<input type="button" class="buy_btn" value="立即购买" /> <input
						type="submit" class="add_cart" id="add_cart" value="加入购物车" />
				</div>
			</div>
		</div>
	</form>
	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_goods">
				<h3>新品推荐</h3>
				<ul>
					<c:choose>
						<c:when test="${empty pl }">
							pl数据为空！！
						</c:when>

						<c:otherwise>
							<c:forEach var="product" items="${pl }" end="1">
								<li><a
									href="ProductServlet?method=getOneP&pid=${product.pid }"><img
										src="${product.pimage }"></a>
									<h4>
										<a href="ProductServlet?method=getOneP&pid=${product.pid }">${product.pname }</a>
									</h4>
									<div class="prize">￥${product.shop_price }</div></li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<ul class="detail_tab clearfix">
				<li class="active">商品介绍</li>
				<li>评论</li>
			</ul>

			<div class="tab_content">
				<dl>
					<c:choose>
						<c:when test="${empty product }">
					数据为空！！！
				</c:when>
						<c:otherwise>
							<dt>商品详情：</dt>
							<dd>${product.pdesc }</dd>
						</c:otherwise>
					</c:choose>

				</dl>
			</div>

		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>

	<div class="add_jump"></div>



</body>
</html>