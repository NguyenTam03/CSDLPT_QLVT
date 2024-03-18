package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.Program;
import model.NhanVienModel;

public class NhanVienDao extends IAbstractDao<NhanVienModel> {
	
	public NhanVienDao() {
		init();
	}
	
	
	private void init() {
		String sql = "SELECT * FROM NhanVien";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			 setColCount(Program.myReader.getMetaData().getColumnCount() - 1);;
			 String[] colName = new String[getColCount()];
			for (int i = 0; i < getColCount(); i++) {
				colName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
			}
			setColName(colName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static NhanVienDao getInstance() {
		return new NhanVienDao();
	}

	@Override
	public int insert(NhanVienModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(NhanVienModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(NhanVienModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<NhanVienModel> selectAll() {
		ArrayList<NhanVienModel> dsNhanVien = new ArrayList<NhanVienModel>();
		String sql = "SELECT * FROM NhanVien";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				Date resultDate = null;
				if(Program.myReader.getDate(6) != null) 
				    resultDate = Program.myReader.getDate(6);
				Float luong = Program.myReader.getFloat(7);
				if (luong == null)
				    luong = (float)0;
				NhanVienModel NhanVien = new NhanVienModel(
						Program.myReader.getInt(1),
						Program.myReader.getString(2),
						Program.myReader.getString(3),
						Program.myReader.getString(4),
						Program.myReader.getString(5),
						resultDate,
						luong,
						Program.myReader.getString(8),
						Program.myReader.getBoolean(9)
						);
				dsNhanVien.add(NhanVien);
			}
			return dsNhanVien;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public NhanVienModel selectById(NhanVienModel t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<NhanVienModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
