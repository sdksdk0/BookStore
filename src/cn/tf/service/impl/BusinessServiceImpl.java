package cn.tf.service.impl;

import java.util.List;
import java.util.UUID;

import cn.tf.commons.Page;
import cn.tf.dao.BookDao;
import cn.tf.dao.CategoryDao;
import cn.tf.dao.CustomerDao;
import cn.tf.dao.impl.BookDaoImpl;
import cn.tf.dao.impl.CategoryDaoImpl;
import cn.tf.dao.impl.CustomerDaoImpl;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Customer;
import cn.tf.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao categoryDao=new CategoryDaoImpl();
	private CustomerDao customerDao=new CustomerDaoImpl();
	
	private BookDao bookDao=new BookDaoImpl();
	@Override
	public void addCategory(Category category){// TODO Auto-generated method stub
			category.setId(UUID.randomUUID().toString());
			categoryDao.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		
		return categoryDao.findAll();
	}

	@Override
	public boolean isCategoryExists(String categoryName) {
		Category category=categoryDao.findByName(categoryName);
		return category==null?false:true;
	}

	@Override
	public void  delCategory(String categoryName) {
		categoryDao.deleteByName(categoryName);
		
	}

	@Override
	public void addBook(Book book) {
		if(book==null)
			throw new IllegalArgumentException("error");
		if(book.getCategory()==null)
			throw new IllegalArgumentException("error");
		book.setId(UUID.randomUUID().toString());
		bookDao.addBook(book);
		
	}

	@Override
	public Category findCategoryById(String categoryId) {
		
		return categoryDao.findOne(categoryId);
	}

	@Override
	public Page findPage(String num) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum();
		Page page=new Page(pageNum,totalRecordsNum);
		List<Book>  records=bookDao.findPageBooks(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);

		return page;
	}

	@Override
	public Page findPage(String num, String categoryId) {
		int pageNum =1;
		if(num!=null){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum(categoryId);
		Page page=new Page(pageNum,totalRecordsNum);
		List<Book>  records=bookDao.findPageBooks(page.getStartIndex(), page.getPageSize(),categoryId);
		page.setRecords(records);

		return page;
	}

	@Override
	public Book findBookById(String bookId) {
		
		return bookDao.findOne(bookId);

	}

	@Override
	public void registCustomer(Customer customer) {
		customer.setId(UUID.randomUUID().toString());
		customerDao.save(customer);
		
	}

	@Override
	public Customer findByCode(String code) {
		
		return customerDao.findByCode(code);
	}

	@Override
	public void activeCustomer(Customer customer) {
		if(customer==null)
			throw new RuntimeException("数据不能为空");
		if(customer.getId()==null)
			throw new RuntimeException("更新数据的主键不能为空");
		
		customerDao.update(customer);
		
	}

	@Override
	public Customer login(String username, String password) {
		
		Customer customer = customerDao.find(username,password);
		if(customer==null)
			return null;
		if(!customer.isActived())
			return null;
		return customer;
	}

}
