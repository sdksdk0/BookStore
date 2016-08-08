package cn.tf.utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

//订单生成器
public class OrderNumUtil {
	
	public static String genOrderNum() {
		
		//得到当前的日期,用日期去查找是否有对应记录
		
	    QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(now);
		
		StringBuffer sb=new StringBuffer(date.replace("-", ""));
		
		
		//没有对应记录，则插入当前日期和数字1
		
	    //有对应记录，则去除数字，返回数字加1，同时更新数据库
		try {
			Integer num=(Integer) qr.query("select num from ordernum where prefix=? ", new ScalarHandler(1),date);
			if(num==null){
				num=new Integer(1);
				qr.update("insert into ordernum(prefix, num) values(?,?)",date,num);
				
			}else{
				num=num+1;
				qr.update("update  ordernum  set num=? where prefix=?  ",num,date);
			}
			
			int numLength=num.toString().length();
			for(int i=0;i<11-numLength;i++){
				sb.append("0");
			}
			sb.append(num);
			return sb.toString();
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
	
}
