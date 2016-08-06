package cn.tf.service;

import java.util.List;

import cn.tf.commons.Page;
import cn.tf.domain.Book;
import cn.tf.domain.Category;

public interface  BusinessService {
	
	//添加分类
	void addCategory(Category category);
	
	//查询分类
	List<Category>  findAllCategories();
	
	//根据分类名称查询该分类是否可用
	boolean isCategoryExists(String categoryName);
	
	
	void delCategory(String categoryName);

	
	//添加书籍信息
	void addBook(Book book);

	
	//根据分类id查询分类信息
	Category findCategoryById(String categoryId);
	
	

	//根据用户要查看的页码，返回封装了所有分页信息的page对象
	Page findPage(String num);

	
	//根据用户要查看的页码和种类，返回封装了所有分页信息的page对象
	Page findPage(String num, String categoryId);

	
	Book findBookById(String bookId);
	
	
	
	

}
