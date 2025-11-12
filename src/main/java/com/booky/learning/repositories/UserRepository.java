package com.booky.learning.repositories;

import com.booky.learning.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);
}
