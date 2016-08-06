package cn.tf.service.impl;

import java.util.List;
import java.util.UUID;

import cn.tf.dao.CategoryDao;
import cn.tf.dao.impl.CategoryDaoImpl;
import cn.tf.domain.Category;
import cn.tf.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

	private CategoryDao categoryDao=new CategoryDaoImpl();
	
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

}
