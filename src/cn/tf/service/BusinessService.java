package cn.tf.service;

import java.util.List;

import cn.tf.commons.Page;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Customer;
import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;

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
	
	//用户注册
	void registCustomer(Customer customer);
	
	
	//根据激活码获取用户信息
	Customer findByCode(String code);
	
	//激活客户的账户
	void activeCustomer(Customer customer);
	
	//根据用户名或密码登录，如果账户没有激活就返回null
	Customer login(String username,String password);

	//生成订单
	void genOrder(Order order);

	//根据订单号查找订单
	Order findOrderByNum(String ordernum);

	//更新订单信息
	void updateOrder(Order order);
	
	//更新订单状态
	void changeOrderStatus(int status,String ordernum);

	//
	List<Order> findOrdersByCustomerId(String customerId);
	
	List<OrderItem> findOrderItemByCustomerId(String ordernum);

}
