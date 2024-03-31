package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import main.Program;


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

	public abstract <E extends Object> T selectById(E t);
	

	public abstract ArrayList<T> selectByCondition(String sql, Object...objects);
	
	public void initModel() {
		try {
			colCount = Program.myReader.getMetaData().getColumnCount();
			String[] colName = new String[colCount];
			for (int i = 0; i < colCount; i++) {
				colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
			}
			this.colName = colName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
