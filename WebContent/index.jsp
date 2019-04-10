<%@page import="com.msr.bean.Category"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-首页</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/slide.js"></script>
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
		<div class="navbar">
			<h1 class="fl">全部商品分类</h1>
			<ul class="navlist fl">
				<li><a href="index.jsp">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<c:set var="csc"
		value="${fn:split('fruit,seafood,meet,egg,vegetables,ice',',') }"></c:set>
	<c:set var="hss"
		value="${fn:split('#model01,#model02,#model03,#model04,#model05,#model06',',') }"></c:set>
	<div class="center_con clearfix">
		<ul class="subnav fl">
			<c:choose>
				<c:when test="${empty category }">
					cList数据为空！！
				</c:when>
				<c:otherwise>
					<c:forEach var="ci" items="${category }" varStatus="i">
						<li><a href="${hss[i.index] }" class="${csc[i.index]} ">${ci.cname}</a></li>
					</c:forEach>
				</c:otherwise>
			</c:choose>


		</ul>
		<div class="slide fl">
			<ul class="slide_pics">
				<li><img src="images/slide.jpg" alt="幻灯片"></li>
				<li><img src="images/slide02.jpg" alt="幻灯片"></li>
				<li><img src="images/slide03.jpg" alt="幻灯片"></li>
				<li><img src="images/slide04.jpg" alt="幻灯片"></li>
			</ul>
			<div class="prev"></div>
			<div class="next"></div>
			<ul class="points"></ul>
		</div>
		<div class="adv fl">
			<a href="#"><img src="images/adv01.jpg"></a> <a href="#"><img
				src="images/adv02.jpg"></a>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model01">新鲜水果</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">鲜芒</a> <a href="#">加州提子</a> <a href="#">亚马逊牛油果</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=1&pageIndex=1"
				class="goods_more fr" id="fruit_more">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner01.jpg">
			</div>
			<ul class="goods_list fl">
				<c:if test="${empty images }">
					<c:redirect url="IndexServlet?method=getImage">
					</c:redirect>
				</c:if>
				<c:choose>
					<c:when test="${empty images }">
						images内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a href="ProductServlet?method=getOneP&pid=${images.get('pid')[i.index] }">${images.get("pnames")[i.index] }</a>
								</h4> <a href="ProductServlet?method=getOneP&pid=${images.get('pid')[i.index] }"><img src="${images.get('pimages')[i.index] }"></a>
								<div class='prize'>${images.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model02">海鲜水产</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">河虾</a> <a href="#">扇贝</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=2&pageIndex=1"
				class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner02.jpg">
			</div>
			<ul class="goods_list fl">
				<c:choose>
					<c:when test="${empty images1 }">
						images1内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a href="ProductServlet?method=getOneP&pid=${images1.get('pid')[i.index] }">${images1.get("pnames")[i.index] }</a>
								</h4> <a href="ProductServlet?method=getOneP&pid=${images1.get('pid')[i.index] }"><img src="${images1.get('pimages')[i.index] }"></a>
								<div class='prize'>${images1.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model03">猪牛羊肉</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">鲜芒</a> <a href="#">加州提子</a> <a href="#">亚马逊牛油果</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=3&pageIndex=1"
				class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner03.jpg">
			</div>
			<ul class="goods_list fl">
				<c:choose>
					<c:when test="${empty images2 }">
						images2内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a
										href="ProductServlet?method=getOneP&pid=${images2.get('pid')[i.index] }">${images2.get("pnames")[i.index] }</a>
								</h4> <a
								href="ProductServlet?method=getOneP&pid=${images2.get('pid')[i.index] }"><img
									src="${images2.get('pimages')[i.index] }"></a>
								<div class='prize'>${images2.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model04">禽类蛋品</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">鲜芒</a> <a href="#">加州提子</a> <a href="#">亚马逊牛油果</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=4&pageIndex=1"
				class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner04.jpg">
			</div>
			<ul class="goods_list fl">
				<c:choose>
					<c:when test="${empty images3 }">
						images3内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a
										href="ProductServlet?method=getOneP&pid=${images3.get('pid')[i.index] }">${images3.get("pnames")[i.index] }</a>
								</h4> <a
								href="ProductServlet?method=getOneP&pid=${images3.get('pid')[i.index] }"><img
									src="${images3.get('pimages')[i.index] }"></a>
								<div class='prize'>${images3.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model05">新鲜蔬菜</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">鲜芒</a> <a href="#">加州提子</a> <a href="#">亚马逊牛油果</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=5&pageIndex=1"
				class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner05.jpg">
			</div>
			<ul class="goods_list fl">
				<c:choose>
					<c:when test="${empty images4 }">
						images4内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a
										href="ProductServlet?method=getOneP&pid=${images4.get('pid')[i.index] }">${images4.get("pnames")[i.index] }</a>
								</h4> <a
								href="ProductServlet?method=getOneP&pid=${images4.get('pid')[i.index] }"><img
									src="${images4.get('pimages')[i.index] }"></a>
								<div class='prize'>${images4.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model06">速冻食品</h3>
			<div class="subtitle fl">
				<span>|</span> <a href="#">鲜芒</a> <a href="#">加州提子</a> <a href="#">亚马逊牛油果</a>
			</div>
			<a href="ProductServlet?method=findByCid&cid=6&pageIndex=1"
				class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl">
				<img src="images/banner06.jpg">
			</div>
			<ul class="goods_list fl">
				<c:choose>
					<c:when test="${empty images5 }">
						images5内容为空！！
					</c:when>
					<c:otherwise>
						<c:forEach begin="0" end="3" varStatus="i">
							<li><h4>
									<a
										href="ProductServlet?method=getOneP&pid=${images5.get('pid')[i.index] }">${images5.get("pnames")[i.index] }</a>
								</h4> <a
								href="ProductServlet?method=getOneP&pid=${images5.get('pid')[i.index] }"><img
									src="${images5.get('pimages')[i.index] }"></a>
								<div class='prize'>${images5.get("prices")[i.index] }</div></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	
	

</body>
</html>