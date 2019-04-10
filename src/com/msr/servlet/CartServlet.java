package com.msr.servlet;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.bean.Cart;
import com.msr.bean.CartItem;
import com.msr.bean.Product;
import com.msr.dao.ProductDao;
import com.msr.dao.impl.ProductDaoImpl;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	ProductDao pd = null;
	
	@Override
	public void init() throws ServletException {
		pd=new ProductDaoImpl();
	}
	
	
	public String empty(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("========CartServlet===========");
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		cart.clearCart();
		
		req.getSession().setAttribute("cart", cart);
		
		
		return "cart.jsp";
	}
	
	public String modify(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("========CartServlet===========");
		String pid = req.getParameter("pid");
		int num = Integer.parseInt(req.getParameter("num"));
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		
		CartItem ci = cart.getMap().get(pid);
		
		cart.modify(ci, num);
		
		req.getSession().setAttribute("cart", cart);
		
		return "cart.jsp";
	}
	
	public String addProduct(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("========CartServlet===========");
		String pid = req.getParameter("pid");
		Product product = pd.getone(pid);
		
		CartItem ci = new CartItem(product, 1, 0);
		
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
		}
		
		cart.addCartItem(ci);
		
		req.getSession().setAttribute("cart", cart);
		
		return "cart.jsp";
	}
	
	public String remove(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("========CartServlet===========");
		String pid = req.getParameter("pid");
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		req.getSession().setAttribute("cart", cart);
		
		
		return "cart.jsp";
	}
	
	public String addProductInList(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("========CartServlet===========");
		String cid = req.getParameter("cid");
		String pageIndex = req.getParameter("pageIndex");
		
		String pid = req.getParameter("pid");
		Product product = pd.getone(pid);
		
		CartItem ci = new CartItem(product, 1, 0);
		
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
		}
		
		cart.addCartItem(ci);
		
		req.getSession().setAttribute("cart", cart);
		
		return "ProductServlet?method=findByCid&cid="+cid+"&pageIndex="+pageIndex;
	}
}
