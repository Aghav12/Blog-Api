package com.infopeer.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infopeer.blog.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
