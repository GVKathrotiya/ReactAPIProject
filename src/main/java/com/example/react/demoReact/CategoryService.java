package com.example.react.demoReact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryRepository catRepo;

	public List<Category> listAll() {
		return catRepo.findAll();
	}

	public void save(Category cat) {
		catRepo.save(cat);
	}

	public Category getCat(int id) {
		return catRepo.findById(id);
	}

	public void delete(Integer id) {
		Category cat = catRepo.findById(id);
		catRepo.delete(cat);
	}
}
