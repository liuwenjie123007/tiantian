<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>天天生鲜-购物车</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
function num_add(pid,add_a) {
	var number = add_a.nextElementSibling;
	number.value++;
	var num = number.value;
	location.href = "CartServlet?method=modify&pid="+pid+"&num="+num;
}
function num_minus(pid,minus_a) {
	var number = minus_a.parentNode.firstElementChild.nextElementSibling;

	if (number.value > 1) {
		number.value--;
		var num = number.value;
		location.href = "CartServlet?method=modify&pid="+pid+"&num="+num;
	} else {
		alert('数量不能小于1');
	}
}
function change(c) {
	if (c.value <= 0) {
		alert("数量不能小于1")
		c.value=1;
	} 
}

function modify(pid,obj){
	var num = obj.value;
	
	location.href = "CartServlet?method=modify&pid="+pid+"&num="+num;
}
</script>

</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="images/logo.png"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
	</div>

	<div class="total_count">
		全部商品<em>${cart.count }</em>件
	</div>
	<ul class="cart_list_th clearfix">
		<li class="col01">商品图片</li>
		<li class="col02">商品名称</li>
		<li class="col03">商品价格</li>
		<li class="col04">数量</li>
		<li class="col05">小计</li>
		<li class="col06">操作</li>
	</ul>
	<c:forEach var="map" items="${cart.map }">
		<ul class="cart_list_td clearfix">
			<li class="col01"><input type="checkbox" name="" checked></li>
			<li class="col02"><img src="${map.value.product.pimage }"></li>
			<li class="col03"></li>
			<li class="col04">${map.value.product.pname }</li>
			<li class="col05"><em id="price">${map.value.product.shop_price }</em>元</li>
			<li class="col06">
				<div class="num_add">
					<a href="javascript:;"
						onclick="num_add(${map.value.product.pid},this)" class="add fl">+</a>
					<input type="text" oninput="modify(${map.value.product.pid},this)"
						class="num_show fl" value="${map.value.num }" /> <a
						href="javascript:;"
						onclick="num_minus(${map.value.product.pid},this)"
						class="minus fl">-</a>
				</div>
			</li>
			<li class="col07"><em id="count1">${map.value.subTotal }</em>元</li>
			<li class="col08"><a
				href="CartServlet?method=remove&pid=${map.value.product.pid }">删除</a></li>
		</ul>
	</c:forEach>
	<ul class="settlements">
		<li class="col01"><input type="checkbox" name="" checked=""></li>
		<li class="col02">全选</li>
		<li class="col03">合计(不含运费)：<span>¥</span><em id="count">${cart.total }</em><br>共计<b>${cart.count }</b>件商品
		</li>
		<li class="col04"><a href="place_order.jsp">去结算</a></li>
		<li class="col05"><a href="CartServlet?method=empty">清空购物车</a></li>
	</ul>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>