package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.KhoModel;

public class KhoDao implements IAbstractDao<KhoModel> {
	
	private int columCount;
	private String[] columsName;
	
	public KhoDao() {
		init();
	}
	
	
	private void init() {
		String sql = "SELECT * FROM Kho";
		Program.myReader = Program.ExecSqlDataReader(sql);
		try {
			columCount = Program.myReader.getMetaData().getColumnCount() - 1;
			columsName = new String[columCount];
			for (int i = 0; i < columCount; i++) {
				columsName[i] = Program.myReader.getMetaData().getColumnName(i + 1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getColumCount() {
		return columCount;
	}

	public String[] getColumsName() {
		return columsName;
	}

	public void setColumCount(int columCount) {
		this.columCount = columCount;
	}

	public void setColumsName(String[] columsName) {
		this.columsName = columsName;
	}

	public static KhoDao getInstance() {
		return new KhoDao();
	}

	@Override
	public int insert(KhoModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(KhoModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(KhoModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<KhoModel> selectAll() {
		ArrayList<KhoModel> dsKho = new ArrayList<KhoModel>();
		String sql = "SELECT * FROM dbo.Danh_Sach_Kho";
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
