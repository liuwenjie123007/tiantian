<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				<li><a href="user_center_order.jsp" class="active">· 全部订单</a></li>
				<li><a href="user_center_site.jsp">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
				<h3 class="common_title2">全部订单</h3>
			
				<c:choose>
					<c:when test="${empty oList1 }">
						数据为空！！
					</c:when>
					<c:otherwise>
						<c:forEach var="orders" items="${oList1 }">
							<ul class="order_list_th w978 clearfix">
								<li class="col01">${orders.orderTime }</li>
								<li class="col02" style="width: 300px">订单号：${orders.oid }</li>
								<li class="col02 stress">已支付</li>		
							</ul>
			
							<table class="order_list_table w980">
								<tbody>
									<tr>
							<td width="55%">
								<c:forEach var="oi" items="${orders.oiList }">
									<ul class="order_goods_list clearfix">					
									<li class="col01"><img src="${oi.product.pimage }"></li>
									<li class="col02">${oi.product.pname }<em>${oi.product.shop_price }</em></li>	
									<li class="col03">${oi.quantity }</li>
									<li class="col04">${oi.total }元</li>	
								</ul>
								</c:forEach>
							</td>
							<td width="15%">${orders.total }元</td>
							<td width="15%">已付款</td>
							<td width="15%"><a href="#" class="oper_btn">查看物流</a></td>
							</tr>
							</tbody>
						</table>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
				

				<div class="pagenation">
					<a href="OrdersServlet?method=getOrder&pageIndex=${pageIndex-1 } ">上一页</a>
					<c:forEach begin="0" end="${totalPage-1 }" varStatus="i">
						<a href="OrdersServlet?method=getOrder&pageIndex=${i.index+1 }">${i.index+1 }</a>
					</c:forEach>
					<a href="OrdersServlet?method=getOrder&pageIndex=${pageIndex+1 } ">下一页></a>
				</div>
		</div>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>