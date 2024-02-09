package com.blogapplication.repositories;

import com.blogapplication.entities.User;
import com.blogapplication.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {



}
