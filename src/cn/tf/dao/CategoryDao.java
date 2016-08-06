package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Category;

public interface CategoryDao {

	void save(Category category);

	List<Category> findAll();

	Category findByName(String categoryName);

	void deleteByName(String categoryName);

	Category findOne(String categoryId);
	

}
