package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.KhoModel;

public class KhoDao extends IAbstractDao<KhoModel> {
	
	public KhoDao() {
		init();
	}
	
	private void init() {
		String sql = "SELECT * FROM Kho";
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

	public static KhoDao getInstance() {
		return new KhoDao();
	}

	@Override
	public int insert(KhoModel t) {
		String sql = "INSERT INTO Kho (MAKHO, TENKHO, DIACHI, MACN) VALUES (?, ?, ?, ?)";
		return Program.ExecSqlDML(sql, t.getMaKho(), t.getTenKho(), t.getDiaChi(), t.getMacn());
	}

	@Override
	public int update(KhoModel t) {
		String sql = "UPDATE Kho SET TENKHO = ?, DIACHI = ? WHERE MAKHO = ?";
		return Program.ExecSqlDML(sql, t.getTenKho(), t.getDiaChi(), t.getMaKho());
	}

	@Override
	public int delete(KhoModel t) {
		String sql = "DELETE FROM Kho WHERE MAKHO = ?";
		return Program.ExecSqlDML(sql, t.getMaKho());
	}

	@Override
	public ArrayList<KhoModel> selectAll() {
		ArrayList<KhoModel> dsKho = new ArrayList<KhoModel>();
		String sql = "SELECT * FROM dbo.Kho";
		Program.myReader = Program.ExecSqlDataReader(sql);
		
		try {
			while (Program.myReader.next()) {
				KhoModel kho = new KhoModel(
						Program.myReader.getString(1),
						Program.myReader.getString(2),
						Program.myReader.getString(3),
						Program.myReader.getString(4));
				dsKho.add(kho);
			}
			return dsKho;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public KhoModel selectById(KhoModel t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<KhoModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
