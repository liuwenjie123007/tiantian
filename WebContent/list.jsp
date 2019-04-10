<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-商品列表</title>
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
					<li><a href="ProductServlet?method=findByCid&cid=1&pageIndex=1"
						class="fruit">新鲜水果</a></li>
					<li><a href="ProductServlet?method=findByCid&cid=2&pageIndex=1"
						class="seafood">海鲜水产</a></li>
					<li><a href="ProductServlet?method=findByCid&cid=3&pageIndex=1"
						class="meet">猪牛羊肉</a></li>
					<li><a href="ProductServlet?method=findByCid&cid=4&pageIndex=1"
						class="egg">禽类蛋品</a></li>
					<li><a href="ProductServlet?method=findByCid&cid=5&pageIndex=1"
						class="vegetables">新鲜蔬菜</a></li>
					<li><a href="ProductServlet?method=findByCid&cid=6&pageIndex=1"
						class="ice">速冻食品</a></li>
				</ul>
			</div>
			<ul class="navlist fl">
				<li><a href="index.jsp">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<div class="breadcrumb">
		<a href="index.jsp">全部分类</a> <span>></span> <a href="#">${category.cname }</a>
	</div>

	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_goods">
				<h3>新品推荐</h3>
				<c:choose>
					<c:when test="${empty pl1 }">
							pl数据为空！！
						</c:when>
					<c:otherwise>
						<c:forEach var="product" items="${pl1 }" end="3">
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
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<div class="sort_bar">
				<a href="#" class="active">默认</a> <a href="#">价格</a> <a href="#">人气</a>
			</div>

			<ul class="goods_type_list clearfix">
				<c:choose>
					<c:when test="${empty pl }">
						数据为空！！
					</c:when>
					<c:otherwise>
						<c:forEach var="product" items="${pl }">
							<li><a
								href="ProductServlet?method=getOneP&pid=${product.pid }"><img
									src="${product.pimage }"></a>
								<h4>
									<a href="ProductServlet?method=getOneP&pid=${product.pid }">${product.pname }</a>
								</h4>
								<div class="operate">
									<span class="prize">￥${product.shop_price }</span> <span
										class="unit"></span> <a href="CartServlet?method=addProductInList&pid=${product.pid }&pageIndex=${pageIndex }&cid=${pl[0].cid }" class="add_goods"
										title="加入购物车"></a>
								</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>

			<div class="pagenation">
				<a href="ProductServlet?method=findByCid&cid=${pl[0].cid }&pageIndex=${pageIndex-1 } ">上一页</a>

				<c:forEach begin="0" end="${totalPage-1 }" varStatus="i">
					<a href="ProductServlet?method=findByCid&cid=${pl[0].cid }&pageIndex=${i.index+1 }">${i.index+1 }</a>
				</c:forEach>

				<a href="ProductServlet?method=findByCid&cid=${pl[0].cid }&pageIndex=${pageIndex+1 } ">下一页></a>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>