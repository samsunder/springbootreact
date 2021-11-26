package com.springreact.springbootreact.repository;

import com.springreact.springbootreact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
