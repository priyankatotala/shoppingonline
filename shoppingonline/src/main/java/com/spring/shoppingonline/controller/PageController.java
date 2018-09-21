package com.spring.shoppingonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.shoppingbackend.dao.CategoryDAO;
import com.spring.shoppingbackend.dto.Category;

@Controller
public class PageController {
	
	
	/*	@RequestMapping(value = {"/test"})
  		public ModelAndView test(@RequestParam(value="Greeting", required=false)String greeting) {
		if(greeting == null) {
			greeting = "hello there";
		}		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	
	}
	
	@RequestMapping(value = {"/test/{greeting}"})
	public ModelAndView test(@PathVariable("greeting")String greeting) {
		if(greeting == null) {
			greeting = "hello there";
		}		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	
	} */
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		// passing the list od categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome", "true");
		return mv;
	}
	@RequestMapping(value = {"/about"})
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", "true");
		return mv;
	}
	@RequestMapping(value = {"/contact"})
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", "true");
		return mv;
	}
	
	// method to load all the products 
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		// passing the list od categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts", "true");
		return mv;
	}
	
	// method to load products  based on category
		@RequestMapping(value = {"/show/category/{id}/products"})
		public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
			ModelAndView mv = new ModelAndView("page");
			// categoryDAO to fetch a single category
			Category category = null;
			category = categoryDAO.get(id);
			
			mv.addObject("title", category.getName());
			
			// passing the list of categories
			mv.addObject("categories", categoryDAO.list());
			
			// passing the single category object
			mv.addObject("category", category);
			
			mv.addObject("userClickCategoryProducts", "true");
			return mv;
		}
}
