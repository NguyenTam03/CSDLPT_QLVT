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
	public int insert(DatHangModel t) {
		String sql = "INSERT INTO DatHang (MasoDDH, NGAY, NhaCC, MANV, MAKHO) VALUES (?, ?, ?, ?, ?)";
		return Program.ExecSqlDML(sql, t.getMaSoDDH(), t.getNgay(), t.getNhaCC(), t.getManv(), t.getMakho());
	}
	
	@Override
	public ArrayList<DatHangModel> selectAll() {
		ArrayList<DatHangModel> datHangList = new ArrayList<DatHangModel>();
		String sql = "SELECT * FROM DatHang";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				DatHangModel datHang = new DatHangModel(
						Program.myReader.getString(1),
						Program.myReader.getDate(2),
						Program.myReader.getString(3),
						Program.myReader.getInt(4),
						Program.myReader.getString(5));
						
				datHangList.add(datHang);
			}
			return datHangList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
