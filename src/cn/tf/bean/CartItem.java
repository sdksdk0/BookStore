package cn.tf.bean;

import java.io.Serializable;

import cn.tf.domain.Book;

//购物项
public class CartItem implements Serializable{
	
	private Book book;//该项对应的书籍
	private float price;//小计：单价*数量
	private int number;//数量
	
	public CartItem(Book book){
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPrice() {
		return book.getPrice()*number;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

}
