package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import main.Program;
import model.CTPXModel;

public class CTPXDao extends IAbstractDao<CTPXModel> {
	public CTPXDao() {
		init();
	}

	private void init() {
		String sql = "SELECT * FROM CTPX";
		Program.myReader = Program.ExecSqlDataReader(sql);
		initModel();
		getColName().remove(getColCount() - 1);
	}

	public static CTPXDao getInstace() {
		return new CTPXDao();
	}

	@Override
	public void insert(CTPXModel t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CTPXModel t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CTPXModel t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<CTPXModel> selectAll() {
		ArrayList<CTPXModel> ctpxList = new ArrayList<CTPXModel>();
		String sql = "SELECT * FROM CTPX";
		Program.myReader = Program.ExecSqlDataReader(sql);

		try {
			while (Program.myReader.next()) {
				CTPXModel ctpxModel = new CTPXModel(Program.myReader.getString(1), Program.myReader.getString(2),
						Program.myReader.getInt(3), Program.myReader.getFloat(4));
				ctpxList.add(ctpxModel);
			}
			return ctpxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<CTPXModel> selectAllByMaPX(String mapx) {
		ArrayList<CTPXModel> ctpxList = new ArrayList<CTPXModel>();
		String sql = "SELECT * FROM CTPX WHERE MAPX = ?";
		Program.myReader = Program.ExecSqlDataReader(sql, mapx);

		try {
			while (Program.myReader.next()) {
				CTPXModel ctpxModel = new CTPXModel(Program.myReader.getString(1), Program.myReader.getString(2),
						Program.myReader.getInt(3), Program.myReader.getFloat(4));
				ctpxList.add(ctpxModel);
			}
			return ctpxList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <E> CTPXModel selectById(E t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CTPXModel> selectByCondition(String sql, Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}

}
