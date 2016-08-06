package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Book;

public interface  BookDao {

	public void addBook(Book book);
	
	int getTotalRecordsNum();
	
	List<Book> findPageBooks(int startIndex,int size);
	
	
	

}
