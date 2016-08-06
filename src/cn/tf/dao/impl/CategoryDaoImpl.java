package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.tf.dao.CategoryDao;
import cn.tf.domain.Category;
import cn.tf.utils.C3P0Util;

public class CategoryDaoImpl implements CategoryDao  {

	
	private QueryRunner  qr=new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public void save(Category category) {
		try {
			qr.update("insert into categorys(id ,name,des) values(?,?,?)",
					category.getId(),category.getName(),category.getDes());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Category> findAll() {
		try {
			return	qr.query("select * from categorys", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		 
	}

	@Override
	public Category findByName(String categoryName) {
		
		try {
			return 	qr.query("select * from categorys  where name=? ", new BeanHandler<Category>(Category.class),categoryName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public  void deleteByName(String categoryName) {
		
		try {
			  qr.update("delete from categorys where name=? ", categoryName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	

}
