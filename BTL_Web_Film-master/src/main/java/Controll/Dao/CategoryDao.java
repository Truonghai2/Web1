package Controll.Dao;

import java.util.List;

import Controll.Entity.Category;

public interface CategoryDao {
	Category findById(int id);
	
    List<Category> findAll();
    
    Category findByName(String name);
    
    Category create(Category entity);

	Category update(Integer id, String name);

	Category delete(Category entity);
}
