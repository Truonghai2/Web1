package Controll.Service;

import java.util.List;

import Controll.Entity.Category;

public interface CategoryService {
	
	public Category findById(int id);

    public List<Category> findAll();
    
    Category create(String name);

    Category update(Integer id, String name);

    Category delete(Integer id);

	

}
