<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="${PageContext.request.ContextPath}/tiantian/admin/css/font.css">
<link rel="stylesheet"
	href="${PageContext.request.ContextPath}/tiantian/admin/css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${PageContext.request.ContextPath}/tiantian/admin/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${PageContext.request.ContextPath}/tiantian/admin/js/xadmin.js"></script>
<title></title>
</head>
<body>
	<div class="x-body">

		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon"></i>批量删除
		</button>
		<button class="layui-btn"
			onclick="x_admin_show('添加用户','${PageContext.request.ContextPath}/tiantian/OrdersServlet?method=orderAddInfo&oid=${oid }')">
			<i class="layui-icon"></i>添加
		</button>
		<span class="x-right" style="line-height: 40px">共有数据：${oi.size() } 条</span> </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>项目编号</th>
					<th>产品名</th>
					<th>产品价格</th>
					<th>数量</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty oiList }">
						数据为空！！
					</c:when>
					<c:otherwise>
						<c:forEach var="oi" items="${oiList }">
							<tr>
								<td>
									<div class="layui-unselect layui-form-checkbox"
										lay-skin="primary" data-id='2'>
										<i class="layui-icon">&#xe605;</i>
									</div>
								</td>
								<td>${oi.itemid }</td>
								<td>${oi.product.pname }</td>
								<td>${oi.product.shop_price }</td>
								<td>${oi.quantity }</td>
								<td>${oi.total }</td>
								<td class="td-manage"><a title="修改"
									onclick="x_admin_show('编辑','${PageContext.request.ContextPath}/tiantian/OrdersServlet?method=getOrderitem&itemid=${oi.itemid}&oid=${oi.oders.oid}')"
									href="javascript:;"> <i class="layui-icon">&#xe63c;</i>
								</a> <a title="删除" onclick="member_del(this,'要删除的id')"
									href="${PageContext.request.ContextPath}/tiantian/OrdersServlet?method=deleteitem&itemid=${oi.itemid}&oid=${oi.oders.oid}"> <i class="layui-icon">&#xe640;</i>
								</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="page">
			<div>
				<a class="prev" href="">&lt;&lt;</a> <a class="num" href="">1</a> <span
					class="current">2</span> <a class="num" href="">3</a> <a
					class="num" href="">489</a> <a class="next" href="">&gt;&gt;</a>
			</div>
		</div>

	</div>
</body>
</html>
