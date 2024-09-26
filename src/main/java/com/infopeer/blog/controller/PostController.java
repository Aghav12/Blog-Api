package com.infopeer.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infopeer.blog.constant.ApiConstants;
import com.infopeer.blog.constant.PathConstants;
import com.infopeer.blog.payload.ApiResponse;
import com.infopeer.blog.payload.PostDto;
import com.infopeer.blog.payload.PostResponse;
import com.infopeer.blog.service.FileService;
import com.infopeer.blog.service.PostService;

/**
 * @author Aghav Narayan
 */
@RestController
@RequestMapping(PathConstants.POST_API)

public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	/**
	 * @apiNote To create new post in database
	 * @param postDto
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	@PostMapping(PathConstants.CREATE_CATEGORY)
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto creatPost = this.postService.creatPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(creatPost, HttpStatus.CREATED);

	}

	/**
	 * @apiNote To get post by userId
	 * @param userId
	 * @return
	 */
	@GetMapping(PathConstants.GET_POST_BY_USER)
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	/**
	 * @apiNote To get post by categoryId
	 * @param categoryId
	 * @return
	 */
	@GetMapping(PathConstants.GET_POST_BY_CATEGOTY)
	public ResponseEntity<List<PostDto>> getPostByCtegory(@PathVariable Integer categoryId) {
		List<PostDto> category = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(category, HttpStatus.OK);

	}

	/**
	 * @apiNote To get all post
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	@GetMapping(PathConstants.GET_ALL_POST)
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = ApiConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApiConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = ApiConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ApiConstants.SORT, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	/**
	 * @apiNote To get post by Id
	 * @param postId
	 * @return
	 */
	@GetMapping(PathConstants.GET_POST_BY_PID)
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postDto = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	/**
	 * @apiNote To delete post
	 * @param postId
	 * @return
	 */
	@DeleteMapping(PathConstants.DELETE_POST)
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse(ApiConstants.POST_DELETED, true);

	}

	/**
	 * @apiNote To update post
	 * @param postDto
	 * @param postId
	 * @return
	 */
	@PutMapping(PathConstants.UPDATE_POST)
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	/**
	 * @apiNote to search post
	 * @param keyword
	 * @return
	 */
	@GetMapping(PathConstants.SEARCH_POST)
	public ResponseEntity<List<PostDto>> serchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploaPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postDto = this.postService.getPostById(postId);

		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
