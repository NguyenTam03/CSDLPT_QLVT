package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.PhieuXuatModel;

public class PhieuXuatDao extends IAbstractDao<PhieuXuatModel>{

	public PhieuXuatDao() {
		init();
	}
	
	public void init() {
		String sql = "SELECT * FROM PhieuXuat";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			setColCount(Program.myReader.getMetaData().getColumnCount() - 1);
			String[] colName = new String[getColCount()];
			for (int i = 0; i < getColCount(); i++) {
				if (Program.myReader.getMetaData().getColumnName(i + 1).equals("MANV")) {
					colName[i] = "TENNV";
				}
				else if (Program.myReader.getMetaData().getColumnName(i + 1).equals("MAKHO")) {
					colName[i] = "TENKHO";
				}
				else
					colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
			}
			setColName(colName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static PhieuXuatDao getInstance() {
		return new PhieuXuatDao();
	}
	@Override
	public void insert(PhieuXuatModel t) throws SQLException {
		String sql = "INSERT INTO PhieuXuat(MAPX, NGAY, HOTENKH, MANV, MAKHO) VALUES(?, ?, ?, ?, ?)";
		Program.ExecSqlDML(sql, t.getMapx(), t.getNgay(), t.getHoTenKH(), t.getManv(), t.getMaKho());
		
	}

	@Override
	public void update(PhieuXuatModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PhieuXuatModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PhieuXuatModel> selectAll() {
		ArrayList<PhieuXuatModel> phieuXuatList = new ArrayList<PhieuXuatModel>();
		String sql = "SELECT * FROM PhieuXuat";
		Program.myReader = Program.ExecSqlDataReader(sql);
		try {
			while (Program.myReader.next()) {
				PhieuXuatModel phieuXuatModel = new PhieuXuatModel(
						Program.myReader.getString(1),
						Program.myReader.getDate(2),
						Program.myReader.getString(3),
						Program.myReader.getInt(4),
						Program.myReader.getString(5));	
				phieuXuatList.add(phieuXuatModel);
			}
			return phieuXuatList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <E> PhieuXuatModel selectById(E t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PhieuXuatModel> selectByCondition(String sql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}

}
