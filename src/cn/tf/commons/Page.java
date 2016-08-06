package cn.tf.commons;

import java.util.List;

public class Page {
	
	private List records;
	private int pageSize = 3;//每页显示的记录条数
	private int pageNum;//当前页码
	
	private int totalPageSize;//总页数
	private int startIndex;//每页开始的记录索引
	private int totalRecordsNum;//总记录条数
	
	private int prePageNum;//上一页
	private int nextPageNum;//下一页
	
	private String url;//查询分页的地址
	
	public Page(int pageNum,int totalRecordsNum){
		this.pageNum = pageNum;
		this.totalRecordsNum = totalRecordsNum;
		//计算总页数
		totalPageSize = totalRecordsNum%pageSize==0?totalRecordsNum/pageSize:totalRecordsNum/pageSize+1;
		//开始记录的索引
		startIndex = (pageNum-1)*pageSize;
		prePageNum = pageNum-1<1?1:pageNum-1;
		nextPageNum = pageNum+1>totalPageSize?totalPageSize:pageNum+1;
	}
	

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}

	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
