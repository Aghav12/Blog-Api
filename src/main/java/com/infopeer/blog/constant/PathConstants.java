package com.infopeer.blog.constant;

public class PathConstants {
	
	// USER END - POINTS
		public static final String USER_PATH = "/api/users";
		
		public static final String CREATE_USER = "/";
		
		public static final String GET_USER_BY_ID = "/{UserId}";
		
		public static final String UPDATE_USER = "/{userId}";
		
		public static final String DELETE_USER_BY_ID = "/{userId}";
		
		public static final String GET_ALL_USER = "/";
		
		//CATEGORY END-POINTS
		
		public static final String CATEGORY_PATH = "/api/categories";
		
		public static final String CREATE_CATEGORY = "/";
		
		public static final String UPDATE_CATEGORY = "/{catId}";
		
		public static final String DELETE_CATEGORY_BY_ID = "/{catId}";
		
		public static final String GET_CATEGORY_BY_ID = "/{catId}";
		
		public static final String GET_ALL_CATEGORY = "/";
		
		//POST END-PONTS
		
		public static final String POST_API = "/api/";
		
		public static final String CREATE_POST = "/user/{userId}/category/{categoryId}/posts";
		
		public static final String GET_POST_BY_USER = "/user/{userId}/posts";
		
		public static final String GET_POST_BY_CATEGOTY = "/category/{categoryId}/posts";
		
		public static final String GET_ALL_POST = "/posts";
		
		public static final String GET_POST_BY_PID = "/posts/{postId}";
		
		public static final String DELETE_POST = "/posts/{postId}";
		
		public static final String UPDATE_POST = "/posts/{postId}";
		
		public static final String SEARCH_POST = "/posta/search/{keywords}";

}
