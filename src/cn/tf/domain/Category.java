package cn.tf.domain;

import java.io.Serializable;

public class Category implements Serializable{

	private String id;   //分类号
	private String name;   //分类名
	private String des;   //描述
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
	
}
