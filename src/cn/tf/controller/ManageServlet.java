package cn.tf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.domain.Category;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.utils.WebUtil;

public class ManageServlet extends HttpServlet {

	private BusinessService  s=new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String op=request.getParameter("op");
		if("checkCategory".equals(op)){
			checkCategory(request,response);
		}else if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("listCategories".equals(op)){
			listCategories(request,response);
		}else if("addBookUI".equals(op)){
			addBookUI(request,response);
		}
		
	}

	
	//添加书籍
	private void addBookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		List<Category> cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
		
		
	}

	//查询所有分类信息
	private void listCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Category> cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategories.jsp").forward(request, response);
		
	}

	//保存分类数据
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		Category category=WebUtil.fillBean(request, Category.class);
		s.addCategory(category);
		
		request.setAttribute("message", "添加成功");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
		
		
		
	}


	//检测分类名称是否可用
	private void checkCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		boolean exists=s.isCategoryExists(name);
		if(exists){
			out.write("<font color='red'>该分类已经存在</font>");
		}else{
			out.write("<font color='green'>可以使用</font>");	
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
