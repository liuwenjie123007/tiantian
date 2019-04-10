package com.msr.servlet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.bean.Cart;
import com.msr.bean.CartItem;
import com.msr.bean.OrderItem;
import com.msr.bean.Orders;
import com.msr.bean.Product;
import com.msr.bean.User;
import com.msr.dao.OrderItemDao;
import com.msr.dao.OrdersDao;
import com.msr.dao.ProductDao;
import com.msr.dao.UserDao;
import com.msr.dao.impl.OrderItemDaoImpl;
import com.msr.dao.impl.OrdersDaoImpl;
import com.msr.dao.impl.ProductDaoImpl;
import com.msr.dao.impl.UserDaoImpl;
import com.msr.utils.PageUtils;
import com.msr.utils.UUIDUtils;

/**
 * Servlet implementation class OrdersServlet
 */
@WebServlet("/OrdersServlet")
public class OrdersServlet extends BaseServlet {
	UserDao ud = null;
	OrdersDao od = null;
	OrderItemDao odid = null;
	ProductDao pd = null;
	

	@Override
	public void init() throws ServletException {
		ud = new UserDaoImpl();
		od = new OrdersDaoImpl();
		odid = new OrderItemDaoImpl();
		pd = new ProductDaoImpl();
		
	}
	
	public String getOrder(HttpServletRequest req, HttpServletResponse reps) {
		String username = (String)req.getSession().getAttribute("username");
		
		int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
		int everySize = 3;
		int totalPage = PageUtils.getTotalPageOrder(username, everySize);
		System.out.println(pageIndex);
		
		
		if (pageIndex < 1) {
			pageIndex = 1;
		} else if (pageIndex > totalPage) {
			pageIndex = totalPage;
		}
		
		System.out.println(pageIndex);
		
		
		List<Orders> oList1 = od.getOrderPage(username, pageIndex, everySize);
		System.out.println(oList1.size());
		for(Orders orders : oList1) {
			System.out.println(orders.getOid()+"\t"+orders.getOrderTime()+"\t"+orders.getState());
			for(OrderItem oi : orders.getOiList()) {
				System.out.println(oi.getQuantity()+"\t"+oi.getTotal()+"\t"+oi.getProduct().getPimage()+"\t"+oi.getProduct().getPname()+"\t"+oi.getProduct().getShop_price());
			}
		}
		
		
		
		req.setAttribute("oList1", oList1);
		
		// 设置商品页总页数
		req.setAttribute("totalPage", totalPage);

		// 设置当前页数
		req.setAttribute("pageIndex", pageIndex);
		
		return "user_center_order.jsp";
	}

	public String addOrderInfo(HttpServletRequest req, HttpServletResponse reps) {
		System.out.println("======OrdersServlet======");

		String address = req.getParameter("address");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String username = (String) req.getSession().getAttribute("username");

		System.out.println(address + name + telephone + username);

		User user = ud.getUserByUserName(username);

		Orders orders = new Orders(null, null, 0, 0, address, name, telephone, user);

		System.out.println(orders);
		req.getSession().setAttribute("orders", orders);

		return "user_center_site.jsp";
	}

	public String addOrders(HttpServletRequest req, HttpServletResponse reps) {
		System.out.println("======OrdersServlet======");

		String oid = UUIDUtils.getId();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter Pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String orderTime = now.format(Pattern);
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		double total = cart.getTotal();
		int state = 1;
		Orders o_orders = (Orders) req.getSession().getAttribute("orders");

		String address = o_orders.getAddress();
		String name = o_orders.getName();
		String telephone = o_orders.getTelephone();
		User user = o_orders.getUser();

		Orders orders = new Orders(oid, orderTime, total, state, address, name, telephone, user);
		od.addOrders(orders);

		List<OrderItem> oiList = new ArrayList<OrderItem>();

		Map<String, CartItem> map = cart.getMap();
		for (CartItem cartitem : map.values()) {
			String itemid = UUIDUtils.getId();
			int quantity = cartitem.getNum();
			double subtotal = cartitem.getSubTotal();
			Product product = cartitem.getProduct();
			OrderItem oi = new OrderItem(itemid, quantity, subtotal, product, orders);
			oiList.add(oi);
		}

		odid.addOrderItems(oiList);

		req.getSession().removeAttribute("cart");

		return "OrdersServlet?method=getOrder&pageIndex=1";
		
		
	}
	
	public String findAllOrders(HttpServletRequest req, HttpServletResponse reps) {
		List<Orders> oList = od.findAll();
		req.setAttribute("oList", oList);	
		return "admin/order-list.jsp";
	}
	
	public String findByOid(HttpServletRequest req, HttpServletResponse reps) {
		String oid = req.getParameter("oid");
		List<OrderItem> oiList = odid.findAllByOid(oid);
		req.setAttribute("oiList", oiList);
		req.setAttribute("oid", oid);
		return "admin/order-view.jsp";
	}
	
	public String getOrderitem(HttpServletRequest req, HttpServletResponse reps) {
		String oid = req.getParameter("oid");
		String itemid = req.getParameter("itemid");
		Orders orders = od.findByOid(oid);
		for(OrderItem oi : orders.getOiList()) {
			if(oi.getItemid().equals(itemid)) {
				oi.setOders(orders);
				req.setAttribute("orderItem", oi);
				return "admin/orderitem-edit.jsp";
			}
		}
		
		
		return "admin/orderitem-edit.jsp";
	}
	
	public String updateOrderItem(HttpServletRequest req, HttpServletResponse rep) {
		String oid = req.getParameter("oid");
		String itemid = req.getParameter("itemid");
		String pname = req.getParameter("pname");
		String shop_price = req.getParameter("shop_price");
		String quantity = req.getParameter("quantity");
		String total = req.getParameter("total");
		
		
		
		Orders orders = od.findByOid(oid);
		
		
		for(OrderItem oi : orders.getOiList()) {
			if(oi.getItemid().equals(itemid)) {
				oi.setQuantity(Integer.parseInt(quantity));
				oi.setTotal(Double.parseDouble(total));
				Product product = pd.getone(oi.getProduct().getPid());
				product.setPname(pname);
				product.setShop_price(Double.parseDouble(shop_price));
				oi.setProduct(product);
				break;
			}
		}
		double total_new = 0;
		for(OrderItem oi : orders.getOiList()) {
			total_new += oi.getTotal();
		}
		orders.setTotal(total_new);
		od.updateOrders(orders);
		
		
		return "OrdersServlet?method=findByOid&oid="+orders.getOid();
	}
	
	public String deleteitem(HttpServletRequest req, HttpServletResponse rep) {
		String oid = req.getParameter("oid");
		String itemid = req.getParameter("itemid");
		Orders orders = od.findByOid(oid);
		OrderItem oitem = null;
		for(OrderItem oi : orders.getOiList()) {
			if(oi.getItemid().equals(itemid)) {
				oitem = oi;
				break;
			}
		}
		odid.deleteItem(oitem);
		
		return "OrdersServlet?method=findByOid&oid="+orders.getOid();
	}
	
	public String orderAddInfo(HttpServletRequest req, HttpServletResponse rep) {
		System.out.println("=========orderAddInfo============");
		String oid = req.getParameter("oid");
		Orders orders = od.findByOid(oid);
		req.setAttribute("orders", orders);
		return "admin/orderitem-add.jsp";
	}
	
	public String addOrderItem(HttpServletRequest req, HttpServletResponse rep) {
		String pname = req.getParameter("pname");
		double shop_price = Double.parseDouble(req.getParameter("shop_price"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String total = req.getParameter("total");
		String oid = req.getParameter("oid");
		String itemid = UUIDUtils.getId();
		Product product = pd.findByPname(pname);
		
		
		Orders orders = od.findByOid(oid);
		System.out.println("orders:\t"+orders);
		
		OrderItem orderItem = new OrderItem(itemid, quantity, Double.parseDouble(total), product, orders);
		System.out.println("orderItem\t"+orderItem);
		System.out.println("orderItemList:\t"+orders.getOiList());
		if(orders.getOiList()==null) {
			orders.setOiList(new ArrayList<OrderItem>());
		}
		orders.getOiList().add(orderItem);
		System.out.println("orderItemListafter:\t"+orders.getOiList());
		orders.setTotal(orders.getTotal()+Double.parseDouble(total));
		od.updateOrders(orders);
		
		
		
		
		return "OrdersServlet?method=findByOid&oid="+orders.getOid();
	}
	
	public String deleteOrders(HttpServletRequest req, HttpServletResponse rep) {
		String oid = req.getParameter("oid");
		
		od.deleteOrders(oid);
		
		return "OrdersServlet?method=findAllOrders";
	}
	
}
