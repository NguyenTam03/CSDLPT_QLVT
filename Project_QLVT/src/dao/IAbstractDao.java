package dao;

import java.util.ArrayList;

public abstract class IAbstractDao<T> {
	private int colCount;
	private String[] colName;
	
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	
	public int getColCount() {
		return colCount;
	}
	
	public String[] getColName() {
		return colName;
	}

	public void setColName(String[] colName) {
		this.colName = colName;
	}

	public int insert(T t) {
		return 0;
	}
	
	public int update(T t) {
		return 0;
	}
	
	public int delete(T t) {
		return 0;
	}
	
	public ArrayList<T> selectAll() {
		return null;
	}
	
	public T selectById(T t) {
		return null;
	}
	
	public ArrayList<T> selectByCondition(String condition) {
		return null;
	}
}
