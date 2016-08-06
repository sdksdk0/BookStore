package cn.tf.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.tf.commons.Page;
import cn.tf.domain.Book;
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
		}else if("delCategory".equals(op)){
			delCategory(request,response);
		}else if("addBook".equals(op)){
			addBook(request,response);
		}else if("listBooks".equals(op)){
			listBooks(request,response);
		}
		
	}

	//分页显示书籍
	private void listBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String num=request.getParameter("num");
		Page page=s.findPage(num);
		page.setUrl("/servlet/ManageServlet?op=listBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
		
		
		
	}

	//保存书籍
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException();
		}
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		List<FileItem>  items=new ArrayList<FileItem>();
		
		try {
			items=sfu.parseRequest(request);
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		}
		Book book=new Book();
		for (FileItem item : items) {
			if(item.isFormField()){
				String fieldName=item.getFieldName();
				String fieldValue=item.getString("UTF-8");
				
				try {
					BeanUtils.setProperty(book, fieldName, fieldValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//单独处理categoryId
				if("categoryId".equals(fieldName)){
					Category category = s.findCategoryById(fieldValue);
					book.setCategory(category);
				}
				
			}else{
				String filename = item.getName();
				
				String extensionName = FilenameUtils.getExtension(filename);
				
				filename = UUID.randomUUID()+"."+extensionName;
				book.setFilename(filename);
				
				
				String rootDirectory = getServletContext().getRealPath("../images");
				String childPath = getStoreDirecotry(rootDirectory);
				book.setPath(childPath);
				
				//文件上传
				try {
					item.write(new File(rootDirectory+File.separator+childPath+File.separator+filename));
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		
		s.addBook(book);
		request.setAttribute("message", "添加书籍成功");
		try {
			request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}


	private String getStoreDirecotry(String rootDirectory) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String path = df.format(now);
		File file = new File(rootDirectory,path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}


	//删除书籍
	private void delCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		
		s.delCategory(name);
		
		request.setAttribute("message", "删除成功");
		request.getRequestDispatcher("/manage/message.jsp?d="+new Date().getTime()).forward(request, response);

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
