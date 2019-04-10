package com.msr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.bean.Category;
import com.msr.bean.Product;
import com.msr.dao.CategoryDao;
import com.msr.dao.ProductDao;
import com.msr.dao.impl.CategoryDaoImpl;
import com.msr.dao.impl.ProductDaoImpl;
import com.msr.utils.PageUtils;

/**
 * Servlet implementation class ProductServlet
 */

@WebServlet(urlPatterns = "/ProductServlet", initParams = { @WebInitParam(name = "er", value = "10") })

public class ProductServlet extends BaseServlet {
	ProductDao pd = null;
	CategoryDao cd = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		pd = new ProductDaoImpl();
		cd = new CategoryDaoImpl();
	}

	public String findByCid(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("findbyCid琚惎鐢�");
		// 鎵�瑕佷娇鐢ㄧ殑鍙冩暩
		String cid = request.getParameter("cid");
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int everySize = 10;
		int totalPage = PageUtils.getTotalPage(cid, everySize);

		// 鍒ゆ柇浼犲叆鐨勫綋鍓嶉〉鏁版槸鍚﹀悎娉曪紝鑻ヤ笉鍚堟硶杩涜淇敼
		if (pageIndex < 1) {
			pageIndex = 1;
		} else if (pageIndex > totalPage) {
			pageIndex = totalPage;
		}

		// List.jsp 涓渶瑕佷娇鐢ㄧ殑鐨勪富鏁告摎
		List<Product> pl = pd.findByCidPage(cid, pageIndex, everySize);
		request.setAttribute("pl", pl);

		// 瑷疆涓�鍊嬫柊鍝佹帹钖︽暩鎿�
		List<Product> pl1 = pd.OrderbyDate(cid);
		request.setAttribute("pl1", pl1);

		// 瑷疆涓�鍊媍ategory椤�
		Category category = cd.findByCid(cid);
		request.setAttribute("category", category);

		// 璁剧疆鍟嗗搧椤垫�婚〉鏁�
		request.setAttribute("totalPage", totalPage);

		// 璁剧疆褰撳墠椤垫暟
		request.setAttribute("pageIndex", pageIndex);
		return "list.jsp";
	}

	public String getOneP(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=====getOneP鎵ц====");
		String pid = request.getParameter("pid");
		Product product = pd.getone(pid);
		request.setAttribute("product", product);

		List<Product> pl = pd.OrderbyDate(product.getCid());
		request.setAttribute("pl", pl);

		Category category = cd.findByCid(product.getCid());
		request.setAttribute("category", category);
		return "detail.jsp";
	}
}
