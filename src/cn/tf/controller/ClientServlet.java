package cn.tf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

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
import cn.tf.domain.Customer;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.utils.SendMailThread;
import cn.tf.utils.WebUtil;

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
		}else if("customerRegist".equals(op)){
			customerRegist(request,response);
		}else if("activeCustomer".equals(op)){
			activeCustomer(request,response);
		}else if("login".equals(op)){
			login(request,response);
		}else if("logout".equals(op)){
			logout(request,response);
		}
		
	}


	//注销登录
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("customer");
		response.getWriter().write("注销成功！2秒后转向主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
		
	}


	//用户登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Customer customer=s.login(username, password);
		if(customer==null){
			response.getWriter().write("错误的用户名或密码，或者您的账户还没有激活！5秒后转向登陆页");
			response.setHeader("Refresh", "5;URL="+request.getContextPath()+"/login.jsp");
			return;
		}
		request.getSession().setAttribute("customer", customer);
		response.getWriter().write("登陆成功！2秒后转向主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
		
	}


	//激活邮箱
	private void activeCustomer(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String code=request.getParameter("code");
		
		Customer customer=s.findByCode(code);
		if(customer==null){
			response.getWriter().write("发生未知错误，请重新输入");
			return ;
		}
		customer.setActived(true);
		s.activeCustomer(customer);
		response.getWriter().write("激活成功！2秒后转向主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
		
	}


	//新用户注册，发送激活邮件
	private void customerRegist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//封装数据
		Customer customer=WebUtil.fillBean(request,Customer.class);
		customer.setCode(UUID.randomUUID().toString());
		//发送邮件,单独启动一个线程
		SendMailThread smt=new SendMailThread(customer);
		smt.start();
		s.registCustomer(customer);
		
		request.setAttribute("message", "注册成功，我们已经发送了一封激活邮件到您的"+customer.getEmail()+"中，请及时激活您的账户");
		request.getRequestDispatcher("/message.jsp").forward(request, response);

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
