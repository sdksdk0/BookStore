package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Book;

public interface  BookDao {

   void addBook(Book book);
	
	int getTotalRecordsNum();
	
	List<Book> findPageBooks(int startIndex,int size);

	int getTotalRecordsNum(String categoryId);

	List<Book> findPageBooks(int startIndex, int pageSize, String categoryId);

	Book findOne(String bookId);

	
	
	
	

}
