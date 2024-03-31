package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.DatHangModel;

public class DatHangDao extends IAbstractDao<DatHangModel> {
	public DatHangDao() {
		init();
	}

	private void init() {
		String sql = "SELECT * FROM DatHang";
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

	public static DatHangDao getInstance() {
		return new DatHangDao();
	}

	@Override
	public void insert(DatHangModel t) throws SQLException {
		String sql = "INSERT INTO DatHang (MasoDDH, NGAY, NhaCC, MANV, MAKHO) VALUES (?, ?, ?, ?, ?)";
		Program.ExecSqlDML(sql, t.getMaSoDDH(), t.getNgay(), t.getNhaCC(), t.getManv(), t.getMakho());
	}

	@Override
	public ArrayList<DatHangModel> selectAll() {
		ArrayList<DatHangModel> datHangList = new ArrayList<DatHangModel>();
		String sql = "SELECT * FROM DatHang";
		Program.myReader = Program.ExecSqlDataReader(sql);

		try {
			while (Program.myReader.next()) {
				DatHangModel datHang = new DatHangModel(Program.myReader.getString(1), Program.myReader.getDate(2),
						Program.myReader.getString(3), Program.myReader.getInt(4), Program.myReader.getString(5));

				datHangList.add(datHang);
			}
			return datHangList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(DatHangModel t) throws SQLException {
		String sql = "UPDATE DatHang SET NhaCC = ?, MAKHO = ? WHERE MasoDDH = ?"; 
		Program.ExecSqlDML(sql, t.getNhaCC(), t.getMakho(), t.getMaSoDDH());
	}

	@Override
	public void delete(DatHangModel t) throws SQLException {
		String sql = "DELETE FROM DatHang WHERE MasoDDH = ?";
		Program.ExecSqlDML(sql, t.getMaSoDDH());
	}
	
	@Override
	public ArrayList<DatHangModel> selectByCondition(String sql, Object...obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> DatHangModel selectById(E t) {
		// TODO Auto-generated method stub
		return null;
	}
}
