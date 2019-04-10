<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-提交订单</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">


<script type="text/javascript">
function sub(obj){
	var flag = document.getElementById('flag');
	if(flag.name == 'fa'){
		alert("请设置收货地址！");
		return false;
	}
}
</script>

</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;提交订单</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
	</div>

	<h3 class="common_title">确认收货地址</h3>

	<div class="common_list_con clearfix">
		<c:choose>
			<c:when test="${empty orders }">
				<dl>
					<dt>寄送到：</dt>
					<dd >
						<input type="radio" name="fa" checked="" id="flag">请设置收货地址！！
					</dd>
				</dl>
			</c:when>
			<c:otherwise>
				<dl>
					<dt>寄送到：</dt>
					<dd>
						<input type="radio" name="" checked="">${orders.address }（${orders.name } 收） ${orders.telephone }
					</dd>
				</dl>
			</c:otherwise>
		</c:choose>
		
		
		
		<a href="user_center_site.jsp" class="edit_site">编辑收货地址</a>

	</div>

	<h3 class="common_title">支付方式</h3>
	<div class="common_list_con clearfix">
		<div class="pay_style_con clearfix">
			<input type="radio" name="pay_style" checked> <label
				class="cash">货到付款</label> <input type="radio" name="pay_style">
			<label class="weixin">微信支付</label> <input type="radio"
				name="pay_style"> <label class="zhifubao"></label> <input
				type="radio" name="pay_style"> <label class="bank">银行卡支付</label>
		</div>
	</div>

	<h3 class="common_title">商品列表</h3>

	<div class="common_list_con clearfix">
		<ul class="goods_list_th clearfix">
			<li class="col01">商品名称</li>
			<li class="col02">商品单位</li>
			<li class="col03">商品价格</li>
			<li class="col04">数量</li>
			<li class="col05">小计</li>
		</ul>
		<c:forEach var="map" items="${cart.map }" varStatus="i">
			<ul class="goods_list_td clearfix">
			<li class="col01">${i.index+1 }</li>
			<li class="col02"><img src="${map.value.product.pimage}"></li>
			<li class="col03">${map.value.product.pname}</li>
			<li class="col04">500g</li>
			<li class="col05">${map.value.product.shop_price}元</li>
			<li class="col06">${map.value.num}</li>
			<li class="col07">${map.value.subTotal}元</li>
		</ul>
		</c:forEach>
	</div>

	<h3 class="common_title">总金额结算</h3>

	<div class="common_list_con clearfix">
		<div class="settle_con">
			<div class="total_goods_count">
				共<em>${cart.count }</em>件商品，总金额<b>${cart.total }元</b>
			</div>
			<div class="transit">
				运费：<b>10元</b>
			</div>
			<div class="total_pay">
				实付款：<b>${cart.total+10 }元</b>
			</div>
		</div>
	</div>

	<div class="order_submit clearfix">
		<a href="OrdersServlet?method=addOrders" id="order_btn" onclick="return sub(this)">提交订单</a>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<div class="popup_con">
		<div class="popup">
			<p>订单提交成功！</p>
		</div>

		<div class="mask"></div>
	</div>

</body>
</html>