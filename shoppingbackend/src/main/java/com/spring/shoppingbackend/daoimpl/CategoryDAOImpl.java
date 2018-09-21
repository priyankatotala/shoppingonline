package com.spring.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.shoppingbackend.dao.CategoryDAO;
import com.spring.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	
	private static List<Category> categories = new ArrayList<>();
	
	static {
		
		Category category = new Category();
		// addign first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is description for TV");
		category.setImageURL("cat.png");
		
		categories.add(category);
		
		// addign second category
		category = new Category();
		
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is description for mobile");
		category.setImageURL("cat.png");
		
		categories.add(category);
		
		// addign third category
				category = new Category();
				
				category.setId(3);
				category.setName("Laptop");
				category.setDescription("This is description for Laptop");
				category.setImageURL("cat.png");
				
				categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		// Enhanced for loop
		
		for(Category category : categories) {
			if(category.getId() == id) 	return category;
		}
		return null;
}
}
