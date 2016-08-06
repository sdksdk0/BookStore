package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tf.dao.BookDao;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.utils.C3P0Util;

public class BookDaoImpl implements BookDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	@Override
	public void addBook(Book book) {
		try {
			qr.update("insert into books(id,name,author,price,path,filename,des,categoryId) values (?,?,?,?,?,?,?,?)", 
					book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getPath(),
					book.getFilename(),book.getDes(),book.getCategory()==null?null:book.getCategory().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public int getTotalRecordsNum() {
		
		try {
			Long num=(Long) qr.query("select count(id) from books ",new ScalarHandler(1));
			return num.intValue();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Book> findPageBooks(int startIndex, int size) {
	
		try {
			List<Book>  books=qr.query("select * from books limit ?,? ",new BeanListHandler<Book>(Book.class),startIndex,size);
			if(books!=null){
				for (Book book : books) {
					String sql = "select * from categorys where id=(select categoryId from books where id=?)";
					Category category = qr.query(sql, new BeanHandler<Category>(Category.class), book.getId());
					book.setCategory(category);
				}
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
