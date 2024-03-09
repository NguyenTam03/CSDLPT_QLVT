package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import main.Program;
import model.VattuModel;

public class VatTuDao extends IAbstractDao<VattuModel>{
	public VatTuDao() {
		init();
	}
	private void init() {
		String sql = "SELECT * FROM Vattu";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			setColCount(Program.myReader.getMetaData().getColumnCount() - 1);
			String[] colName = new String[getColCount()];
			for (int i = 0; i < getColCount(); i++) {
				colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
				
			}
			setColName(colName);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static VatTuDao getInstance() {
		return new VatTuDao();
	}

	
	@Override
	public int insert(VattuModel t) {
		// TODO Auto-generated method stub
		return super.insert(t);
	}

	@Override
	public int update(VattuModel t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public int delete(VattuModel t) {
		// TODO Auto-generated method stub
		return super.delete(t);
	}

	@Override
	public ArrayList<VattuModel> selectAll() {
		ArrayList<VattuModel> dsVatTu = new ArrayList<VattuModel>();
		String sql = "SELECT * FROM Vattu";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				VattuModel vattu = new VattuModel(
						Program.myReader.getString(1),
						Program.myReader.getString(2), 
						Program.myReader.getString(3), 
						Program.myReader.getInt(4));
				dsVatTu.add(vattu);
			}
			return dsVatTu;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VattuModel selectById(VattuModel t) {
		// TODO Auto-generated method stub
		return super.selectById(t);
	}

	@Override
	public ArrayList<VattuModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return super.selectByCondition(condition);
	}
	

}
