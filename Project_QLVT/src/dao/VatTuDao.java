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
	public void insert(VattuModel t) {
		String sql = "INSERT INTO Vattu (MAVT, TENVT, DVT, SOLUONGTON) VALUES (?, ?, ?, ?)";
		Program.ExecSqlDML(sql, t.getMavt(), t.getTenVT(), t.getDvt(), t.getSoLuongTon());
	}

	@Override
	public void update(VattuModel t) {
		String sql = "UPDATE Vattu SET TENVT = ?, DVT = ?, SOLUONGTON = ? WHERE MAVT = ?";
		Program.ExecSqlDML(sql, t.getTenVT(), t.getDvt(), t.getSoLuongTon(), t.getMavt());
	}

	@Override
	public void delete(VattuModel t) {
		String sql = "DELETE FROM Vattu WHERE MAVT = ?";
		Program.ExecSqlDML(sql, t.getMavt());
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
		return null;
	}

	@Override
	public ArrayList<VattuModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
