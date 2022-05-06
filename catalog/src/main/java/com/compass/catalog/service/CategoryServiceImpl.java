package com.compass.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.catalog.dto.CategoryDto;
import com.compass.catalog.dto.CategoryFormDto;
import com.compass.catalog.entity.Category;
import com.compass.catalog.exception.ExceptionResponse;
import com.compass.catalog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void saveCategory(@Valid CategoryFormDto body) {
		try {
			Category category = this.modelMapper.map(body, Category.class);
			List<Category> allCategory = categoryRepository.findAll();

			if ((allCategory.size() - 1 >= 0)) {
				Category categoryMaxId = allCategory.get(allCategory.size() - 1);
				category.setId(categoryMaxId.getId() + 1);
			} else {
				category.setId(1);
			}
			this.categoryRepository.save(category);
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}

	@Override
	public List<CategoryDto> listAllCategories() {
		try {
			List<Category> categories = categoryRepository.findAll();
			List<CategoryDto> categoryDTOs = new ArrayList<>();
			categories.forEach((ca) -> {
				categoryDTOs.add(modelMapper.map(ca, CategoryDto.class));
			});

			return categoryDTOs;
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		try {
			Optional<Category> oCategory = this.categoryRepository.findById(id);
			if (oCategory.isPresent()) {
				return this.modelMapper.map(categoryRepository.findById(id), CategoryDto.class);
			} else {

				throw new ExceptionResponse(404, "Category Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}

	}

	@Override
	public CategoryDto uptadeCategory(Integer id, @Valid CategoryFormDto body) {
		try {
			Optional<Category> cat = this.categoryRepository.findById(id);

			if (cat.isPresent()) {
				cat.get().setName(body.getName());
				cat.get().setActive(body.getActive());
				this.categoryRepository.save(cat.get());
				return modelMapper.map(cat.get(), CategoryDto.class);

			} else {
				throw new ExceptionResponse(404, "Category Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}

	@Override
	public void deleteCategory(Integer id) {
		try {
			Optional<Category> oCategory = this.categoryRepository.findById(id);
			if (oCategory.isPresent()) {
				this.categoryRepository.deleteById(id);
			} else {
				throw new ExceptionResponse(404, "Category Not Found");

			}
		} catch (Exception e) {
			throw new ExceptionResponse(400, e.getMessage());

		}
	}
	
	

}
