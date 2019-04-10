<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" action="AdminProductSevlet?method=modifyProduct" method="post">
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>ID
				</label>
				<div class="layui-input-inline">
					<input type="text" id="pid" name="pid" required=""
						 autocomplete="off" class="layui-input" value="${product.pid }">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>

			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>产品名称
				</label>
				<div class="layui-input-inline">
					<input type="text" id="pname" name="pname" required=""
						 autocomplete="off" class="layui-input" value="${product.pname }">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>价格
				</label>
				<div class="layui-input-inline">
					<input type="text" id="shop_price" name="shop_price" required=""
						 autocomplete="off" class="layui-input" value="${product.shop_price }">
				</div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>图片路径
				</label>
				<div class="layui-input-inline">
					<input type="text" id="pimage" name="pimage" required=""
						 autocomplete="off" class="layui-input" value="${product.pimage }">
				</div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>上市时间
				</label>
				<div class="layui-input-inline">
					<input type="text" id="pdate" name="pdate" required=""
						 autocomplete="off" class="layui-input" value="${product.pdate }">
				</div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>描述
				</label>
				<div class="layui-input-inline">
					<input type="text" id="pdesc" name="pdesc" required=""
						 autocomplete="off" class="layui-input" value="${product.pdesc }">
				</div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> <span
					class="x-red">*</span>分类
				</label>
				<div class="layui-input-inline">
					<input type="text" id="cid" name="cid" required=""
						 autocomplete="off" class="layui-input" value="${product.cid }">
				</div>
			</div>
			<div class="layui-form-item">
				<label  class="layui-form-label"> </label>
				<input type="submit" class="layui-btn" value="修改">
			</div>
      </form>
    </div>
    <script>
      layui.use(['form','layer'], function(){
          $ = layui.jquery;
        var form = layui.form
        ,layer = layui.layer;
      
        //自定义验证规则
        form.verify({
          nikename: function(value){
            if(value.length < 5){
              return '昵称至少得5个字符啊';
            }
          }
          ,pass: [/(.+){6,12}$/, '密码必须6到12位']
          ,repass: function(value){
              if($('#L_pass').val()!=$('#L_repass').val()){
                  return '两次密码不一致';
              }
          }
        });

        //监听提交
        form.on('submit(add)', function(data){
          console.log(data);
          //发异步，把数据提交给php
          layer.alert("增加成功", {icon: 6},function () {
              // 获得frame索引
              var index = parent.layer.getFrameIndex(window.name);
              //关闭当前frame
              parent.layer.close(index);
          });
          return false;
        });
        
        
      });
  </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>