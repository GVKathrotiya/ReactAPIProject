package com.example.react.demoReact;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	/*
	 * @RequestMapping("/hello") public void helloWorld() {
	 * System.out.println("..........Hello....."); }
	 */
	
	@Autowired 
	CategoryService catService;
	
	@GetMapping("/categories")
	public List<Category> listAllCategories(){
		return catService.listAll();
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable int id) {
	    try {
	    	Category cat = catService.getCat(id);
	        return new ResponseEntity<Category>(cat, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@PostMapping("/saveCategory")
	public void addCategory(@RequestBody Category category) {
		catService.save(category);
	}
	
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<?> updateCaegory(@RequestBody Category category,@PathVariable int id){
		try {
			Category existCat = catService.getCat(id);
			catService.save(existCat);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public void delete(@PathVariable Integer id) {
		catService.delete(id);
	}
}
