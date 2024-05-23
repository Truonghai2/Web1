package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.CategoryDao;
import Controll.Dao.Impl.CategoryDaoImpl;
import Controll.Dao.Impl.MovieDaoImpl;
import Controll.Entity.Category;
import Controll.Entity.Movie;
import Controll.Service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao dao;
	
	public CategoryServiceImpl() {
		dao = new CategoryDaoImpl();
	}
	
	@Override
	public Category findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public Category create(String name) {
		if (dao.findByName(name) != null) {
            throw new IllegalArgumentException("Category name already exists.");
        }
        Category category = new Category();
        category.setName(name);
        return dao.create(category);
	}

	@Override
	public Category update(Integer Id, String name) {
		return dao.update(Id,name);
	}

	@Override
	public Category delete(Integer Id) {
		Category entity = findById(Id);
		return dao.delete(entity);
	}



}
