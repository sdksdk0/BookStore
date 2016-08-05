package cn.tf.service;

import java.util.List;

import cn.tf.domain.Category;

public interface  BusinessService {
	
	//添加分类
	void addCategory(Category category);
	
	//查询分类
	List<Category>  findAllCategories();
	
	//根据分类名称查询该分类是否可用
	boolean isCategoryExists(String categoryName);

}
