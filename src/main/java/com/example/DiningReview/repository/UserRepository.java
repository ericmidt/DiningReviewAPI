package com.example.DiningReview.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.DiningReview.model.DiningUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<DiningUser, Long> {
    Optional<DiningUser> getByUsername(String username);
    Optional<DiningUser> findById(Long id);
    
}


