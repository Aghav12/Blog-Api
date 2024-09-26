package com.infopeer.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infopeer.blog.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
