package com.example.DiningReview.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.DiningReview.model.Restaurant;

import java.util.*;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByZipcodeAndName (String zipcode, String name);
    Optional<Restaurant> findById (Long id);
    List<Restaurant> findByZipcodeAndPeanutAllergyScoresIsNotEmptyOrderByOverallScoreDesc(String zipcode);

    List<Restaurant> findByZipcodeAndEggAllergyScoresIsNotEmptyOrderByOverallScoreDesc(String zipcode);

    List<Restaurant> findByZipcodeAndDairyAllergyScoresIsNotEmptyOrderByOverallScoreDesc(String zipcode);

}


