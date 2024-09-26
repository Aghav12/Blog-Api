package com.infopeer.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infopeer.blog.constant.ApiConstants;
import com.infopeer.blog.constant.PathConstants;
import com.infopeer.blog.payload.ApiResponse;
import com.infopeer.blog.payload.CategoryDto;
import com.infopeer.blog.service.CategoryService;

/**
 * @author Aghav Narayan
 */
@RestController
@RequestMapping(PathConstants.CATEGORY_PATH)

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @apiNote To Create a new Category in Database
	 * @param categoryDto
	 * @return
	 */
	@PostMapping(PathConstants.CREATE_CATEGORY)
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createcategory = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createcategory, HttpStatus.CREATED);

	}

	/**
	 * @apiNote To Update Category By Id
	 * @param categoryDto
	 * @param categoryId
	 * @return
	 */

	@PutMapping(PathConstants.UPDATE_CATEGORY)
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		CategoryDto uptadeCategory = this.categoryService.uptadeCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(uptadeCategory, HttpStatus.OK);

	}

	/**
	 * @apiNote To Delete Category By Id in Database
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping(PathConstants.DELETE_CATEGORY_BY_ID)
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.CATEGORY_DELETED, true), HttpStatus.OK);

	}

	/**
	 * @apiNote To Get Category By Id
	 * @param categoryId
	 * @return
	 */
	@GetMapping(PathConstants.GET_CATEGORY_BY_ID)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}

	/**
	 * @apiNote To Get All Category From Database
	 * @return
	 */
	@GetMapping(PathConstants.GET_ALL_CATEGORY)
	public ResponseEntity<List<CategoryDto>> getCategories() {
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);

	}

}
