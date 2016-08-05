package cn.tf.Test;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import cn.tf.domain.Category;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;

public class TestCon {

	private BusinessService s=new BusinessServiceImpl();
	
	@Test
	public void testAdd() {
		
		Category c=new Category();
		c.setName("数据结构");
		c.setDes("数据结构是非常重要的");
		s.addCategory(c);
		
	}
	
	@Test
	public void testFildAll(){
		List<Category>   cs=s.findAllCategories();
		for(Category c:cs){
			System.out.println(c);
		}
	}
	
	
	@Test
	public void testCategoryExists(){
		boolean b=s.isCategoryExists("数据结构");
		assertTrue(b);
	}

}
