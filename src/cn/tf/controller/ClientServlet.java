package cn.tf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tf.bean.Cart;
import cn.tf.bean.CartItem;
import cn.tf.commons.Page;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;

public class ClientServlet extends HttpServlet {

	private BusinessService s=new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op=request.getParameter("op");
		if("listBooks".equals(op)){
			listBooks(request,response);
		}else if("listBookByCategory".equals(op)){
			listBookByCategory(request,response);
		}else if("buyBook".equals(op)){
			buyBook(request,response);
		}else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}else if("changeNum".equals(op)){
			changeNum(request,response);
		}
		
	}


	//改变数量
	private void changeNum(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String bookId=request.getParameter("bookId");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		CartItem item=cart.getItems().get(bookId);
		item.setNumber(Integer.parseInt(request.getParameter("num")));
		
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}




	//删除购物车中的一项
	private void delOneItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String bookId=request.getParameter("bookId");
		Book book=s.findBookById(bookId);
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.getItems().remove(bookId);
		
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
		
	}

	//加入购物车
	private void buyBook(HttpServletRequest request,
			HttpServletResponse response) {
		//得到书籍
		String bookId=request.getParameter("bookId");
		Book book=s.findBookById(bookId);
		
		//购物车
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addBook2Items(book);
		//提示
		request.setAttribute("message", "购买成功！<a href='javascript:window.history.back()'>返回</a>");
		try {
			request.getRequestDispatcher("/message.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//按照分类查询书籍的分页信息
	private void listBookByCategory(HttpServletRequest request,
			HttpServletResponse response) {
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		
		//查询所有商品的分页数据
		String num=request.getParameter("num");
		String categoryId=request.getParameter("categoryId");
		Page page=s.findPage(num,categoryId);
		
		page.setUrl("/servlet/ClientServlet?op=listBookByCategory&categoryId="+categoryId);
		request.setAttribute("page", page);
		try {
			request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void listBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询所有分类
		List<Category>  cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		
		//查询所有商品的分页数据
		String num=request.getParameter("num");
		Page page=s.findPage(num);
		page.setUrl("/servlet/ClientServlet?op=listBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
