package cn.tf.domain;

import java.io.Serializable;

public class Book implements Serializable {

	private String id;
	private String name;
	private String author;
	private String price;
	private String path; // image的存放路径
	private String filename; // image的文件名
	private String des;
	
	private  Category category;
	//private String categoryId;  //书籍所属的分类id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Book(Category category) {
		this.category = category;
	}

	public Book() {
		super();
	}
	
	
	
	
	

}
