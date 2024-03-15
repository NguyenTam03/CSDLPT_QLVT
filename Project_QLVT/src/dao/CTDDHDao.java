package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.CTDDHModel;

public class CTDDHDao extends IAbstractDao<CTDDHModel> {
	public CTDDHDao() {
		init();
	}

	public void init() {
		String sql = "SELECT * FROM CTDDH";
		Program.myReader = Program.ExecSqlDataReader(sql);

		try {
			setColCount(Program.myReader.getMetaData().getColumnCount() - 1);
			String[] colName = new String[getColCount()];
			for (int i = 0; i < getColCount(); i++) {
				colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
			}
			setColName(colName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static CTDDHDao getInstance() {
		return new CTDDHDao();
	}
	
	@Override
	public ArrayList<CTDDHModel> selectAll() {
		ArrayList<CTDDHModel> ctdhList = new ArrayList<>();
		String sql = "SELECT * FROM CTDDH";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				CTDDHModel datHang = new CTDDHModel(
						Program.myReader.getString(1),
						Program.myReader.getString(2),
						Program.myReader.getInt(3),
						Program.myReader.getDouble(4));
						
				ctdhList.add(datHang);
			}
			return ctdhList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
