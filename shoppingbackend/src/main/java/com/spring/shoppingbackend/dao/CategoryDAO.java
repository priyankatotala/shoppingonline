package com.spring.shoppingbackend.dao;

import java.util.List;

import com.spring.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);

}
