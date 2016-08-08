package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.tf.dao.OrderDao;
import cn.tf.domain.Order;
import cn.tf.domain.OrderItem;
import cn.tf.utils.C3P0Util;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	
	
	@Override
	public void save(Order order) {
		
		try {
			qr.update("insert into orders (ordernum,price,number,status,customerId) values (?,?,?,?,?)", 
					order.getOrdernum(),order.getPrice(),order.getNumber(),order.getStatus(),
					order.getCustomer()==null?null:order.getCustomer().getId());
			List<OrderItem> items = order.getItems();
			for(OrderItem item:items){
				qr.update("insert into orderitems (id,number,price,ordernum,bookid) values (?,?,?,?,?)", 
						item.getId(),item.getNumber(),item.getPrice(),order.getOrdernum(),item.getBook()==null?null:item.getBook().getId());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
