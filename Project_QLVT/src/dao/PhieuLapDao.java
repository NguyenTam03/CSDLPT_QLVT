package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.PhieuLapModel;

public class PhieuLapDao extends IAbstractDao<PhieuLapModel> {
	public PhieuLapDao() {
		init();
	}
	
	private void init() {
		String sql = "SELECT * FROM PhieuNhap";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		
		try {
			 setColCount(Program.myReader.getMetaData().getColumnCount() - 1);
			 String[] colName = new String[getColCount()];
			for (int i = 0; i < getColCount(); i++) {
				colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
				if(colName[i].equals("MANV")) {
					colName[i] = "HOTENNV";
				}
				else if (colName[i].equals("MAKHO")) {
					colName[i] = "TENKHO";
				}
			}
			setColName(colName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static PhieuLapDao getInstance() {
		return new PhieuLapDao();
	}
	
	@Override
	public void insert(PhieuLapModel t) throws SQLException {
		String sql = "INSERT INTO PhieuNhap (Mapn, Ngay, MasoDDH, MaNV, Makho) VALUES (?, ?, ?, ?, ?)";
		Program.ExecSqlDML(sql, t.getMapn(), t.getNgay(), t.getMaSoDDH(), t.getManv(), t.getMaKho());
	}
	
	@Override
	public ArrayList<PhieuLapModel> selectAll() {
		ArrayList<PhieuLapModel> phieuLapList = new ArrayList<PhieuLapModel>();
		String sql = "SELECT * FROM PhieuNhap";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				PhieuLapModel phieuLap = new PhieuLapModel(
						Program.myReader.getString(1),
						Program.myReader.getDate(2),
						Program.myReader.getString(3),
						Program.myReader.getInt(4),
						Program.myReader.getString(5));
						
				phieuLapList.add(phieuLap);
			}
			return phieuLapList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(PhieuLapModel t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE PhieuNhap SET Ngay = ?, MasoDDH = ?, Makho = ? WHERE Mapn = ?";
		Program.ExecSqlDML(sql, t.getNgay(), t.getMaSoDDH(), t.getMaKho(), t.getMapn());
	}

	@Override
	public void delete(PhieuLapModel t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM PhieuNhap WHERE Mapn = ?";
		Program.ExecSqlDML(sql, t.getMapn());
	}

	@Override
	public <E> PhieuLapModel selectById(E t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PhieuLapModel> selectByCondition(String sql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}
}
