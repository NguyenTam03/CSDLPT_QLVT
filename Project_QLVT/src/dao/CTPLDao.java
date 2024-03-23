package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.CTPNModel;

public class CTPLDao extends IAbstractDao<CTPNModel> {
	public CTPLDao() {
		init();
	}

	public void init() {
		String sql = "SELECT * FROM CTPN";
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
	
	public static CTPLDao getInstance() {
		return new CTPLDao();
	}
	
	@Override
	public ArrayList<CTPNModel> selectAll() {
		ArrayList<CTPNModel> ctdhList = new ArrayList<>();
		String sql = "SELECT * FROM CTPN";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				CTPNModel CTPhieuNhap = new CTPNModel(
						Program.myReader.getString(1),
						Program.myReader.getString(2),
						Program.myReader.getInt(3),
						Program.myReader.getDouble(4));
						
				ctdhList.add(CTPhieuNhap);
			}
			return ctdhList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CTPNModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CTPNModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CTPNModel t) {
		// TODO Auto-generated method stub
		
	}
}
