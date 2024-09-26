package com.infopeer.blog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	@Size(min = 3, max = 100, message = "categoryTitle must be min of 3 chars and max of 100 chars !!")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, max = 1000, message = "categoryDescription must be min of 10 chars and max of 1000 chars !!")
	private String categoryDescription;

}
