<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页面-X-admin2.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="./css/font.css">
<link rel="stylesheet" href="./css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript" src="./js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-nav">
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so layui-form-pane" action="AdminProductSevlet?method=addProduct" method="post" enctype="multipart/form-data">
				<input class="layui-input" placeholder="产品名称" name="pname">
				<input class="layui-input" placeholder="产品价格" name="shop_price">
				<input type="file" class="layui-input" placeholder="产品图片路径" name="pimage" value="产品图片">
				<input class="layui-input" placeholder="上市时间" name="pdate">
				<input class="layui-input" placeholder="产品描述" name="pdesc">
				<input class="layui-input" placeholder="产品分类" name="cid">
				<button class="layui-btn" lay-submit="" lay-filter="sreach" type="submit">
					<i class="layui-icon"></i>增加
				</button>
			</form>
		</div>
		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon"></i>批量删除
		</button>
		<span class="x-right" style="line-height: 40px">共有数据：${pList.size() } 条</span> </xblock>
		<table class="layui-table layui-form">
			<thead>
				<tr>
					<th width="20">
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th width="70">ID</th>
					<th>产品名称</th>
					<th>价格</th>
					<th>图片路径</th>
					<th>上市时间</th>
					<th>描述</th>
					<th>分类</th>
					<th width="220">操作</th>
			</thead>
			<tbody class="x-cate">
				<c:choose>
					<c:when test="${empty pList }">
						数据为空！！
					</c:when>

					<c:otherwise>
						<c:forEach var="product" items="${pList }">
							<tr cate-id='1' fid='0'>
								<td>
									<div class="layui-unselect layui-form-checkbox"
										lay-skin="primary" data-id='2'>
										<i class="layui-icon">&#xe605;</i>
									</div>
								</td>
								<td>${product.pid }</td>
								<td>${product.pname }</td>
								<td>${product.shop_price }</td>
								<td>${product.pimage }</td>
								<td>${product.pdate }</td>
								<td>${product.pdesc }</td>
								<td>${product.cid }</td>
								<td class="td-manage">
									<button class="layui-btn layui-btn layui-btn-xs"
										onclick="x_admin_show('编辑','AdminProductSevlet?method=oneProduct&pid=${product.pid }')">
										<i class="layui-icon">&#xe642;</i>编辑
									</button>
									<a class="layui-btn-danger layui-btn layui-btn-xs"
										 href="AdminProductSevlet?method=deleteProduct&pid=${product.pid }">
										<i class="layui-icon">&#xe640;</i>删除
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>


			</tbody>
		</table>
	</div>
	<style type="text/css">
</style>
	<script>
		layui.use([ 'form' ], function() {
			form = layui.form;

		});

		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}

		function delAll(argument) {

			var data = tableCheck.getData();

			layer.confirm('确认要删除吗？' + data, function(index) {
				//捉到所有被选中的，发异步进行删除
				layer.msg('删除成功', {
					icon : 1
				});
				$(".layui-form-checked").not('.header').parents('tr').remove();
			});
		}
	</script>
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>

</html>