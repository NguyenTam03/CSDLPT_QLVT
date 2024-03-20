package dao;

import java.sql.SQLException;
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

	public abstract void insert(T t) throws SQLException;
	
	public abstract void update(T t) throws SQLException;
	
	public abstract void delete(T t) throws SQLException;
	
	public abstract ArrayList<T> selectAll();
	
	public T selectById(T t) {
		return null;
	}
	
	public ArrayList<T> selectByCondition(String condition) {
		return null;
	}


}
