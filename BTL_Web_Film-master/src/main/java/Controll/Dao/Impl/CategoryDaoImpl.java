package Controll.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import Controll.Dao.AbstactDao;
import Controll.Dao.CategoryDao;
import Controll.Entity.Category;
import Controll.Entity.Episode;
import Controll.Entity.Movie;
import Controll.Util.JPAUtil;

public class CategoryDaoImpl extends AbstactDao<Category> implements CategoryDao {

	@Override
	public Category findById(int id) {
		return super.findById(Category.class, id);
	}

	@Override
	public List<Category> findAll() {
		return super.findAll(Category.class);
	
	}

	@Override
	public Category findByName(String name) {
		String sql = "SELECT o FROM Category o WHERE o.name = ?0";
		return super.findOne(Category.class, sql, name);
	}


}
