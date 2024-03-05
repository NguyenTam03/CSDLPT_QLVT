package dao;

import java.util.ArrayList;

import model.KhoModel;

public class KhoDao implements IAbstractDao<KhoModel> {
	
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
		// TODO Auto-generated method stub
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
