package com.example.DiningReview.controller;

import org.springframework.web.bind.annotation.*;
import com.example.DiningReview.repository.*;
import com.example.DiningReview.model.*;
import com.example.DiningReview.model.DiningReview.ApprovalStatus;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping
public class DiningReviewController {

    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(
        final DiningReviewRepository diningReviewRepository,
        final UserRepository userRepository,
        final RestaurantRepository restaurantRepository
    ) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public class NotFoundException extends ResponseStatusException {
        public NotFoundException(String reason) {
            super(HttpStatus.NOT_FOUND, reason);
        }
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/users")
    public DiningUser createUser(@RequestBody DiningUser user) {
        // Check if the username is already taken
        Optional<DiningUser> existingUser = userRepository.getByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }
    
        // Save the user profile
        return userRepository.save(user);
    }
    

    @PutMapping("/users/{id}")
    public DiningUser updateUser(@PathVariable Long id, @RequestBody DiningUser updatedUser) {
        DiningUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        
        // Check if display name is being modified, if not update other details
        if (!existingUser.getUsername().equals(updatedUser.getUsername())) {
            throw new IllegalArgumentException("Display name cannot be modified");
        }
    
        // Update other user details
        existingUser.setCity(updatedUser.getCity());
        existingUser.setState(updatedUser.getState());
        // ... other fields ...
    
        return userRepository.save(existingUser);
    }

    @GetMapping("/users/by-username/{username}")
    public DiningUser getUserByUsername(@PathVariable String username) {
        return userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public boolean validateUser(String username) {
        return userRepository.getByUsername(username).isPresent();
    }

    @PostMapping("/dining-reviews")
    public DiningReview submitDiningReview(@RequestBody DiningReview diningReview) {
        // Validate user existence based on username
        if (!validateUser(diningReview.getUsername())) {
            throw new NotFoundException("User not found");
        }
    
        // Save the dining review
        return diningReviewRepository.save(diningReview);
    }

    @GetMapping("/admin/reviews")
    public List<DiningReview> getAllReviews() {
        return StreamSupport.stream(diningReviewRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/admin/pending-reviews")
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByApprovalStatus(ApprovalStatus.PENDING);
    }

    @PutMapping("/admin/reviews/{id}")
    public DiningReview approveOrRejectReview(@PathVariable Long id, @RequestBody DiningReview review) {
        DiningReview existingReview = diningReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found"));
    
        existingReview.setApprovalStatus(review.getApprovalStatus());
    
        return diningReviewRepository.save(existingReview);
    }
    
    @GetMapping("/admin/users")
    public List<DiningUser> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    


    @PostMapping("/restaurants")
    public Restaurant submitRestaurant(@RequestBody Restaurant restaurant) {
        // Check if a restaurant with the same name and zip code exists
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByZipcodeAndName(restaurant.getZipcode(), restaurant.getName());
    
        if (existingRestaurant.isPresent()) {
            throw new IllegalArgumentException("Restaurant with same name and zip code already exists");
        }
    
        // Save the restaurant
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantsByZipCodeAndAllergyScore(@RequestParam String zipCode) {
        List<Restaurant> restaurantsWithAllergies = restaurantRepository
        .findByZipcodeAndPeanutAllergyScoresIsNotEmptyOrderByOverallScoreDesc(zipCode);

        restaurantsWithAllergies.addAll(restaurantRepository
                .findByZipcodeAndEggAllergyScoresIsNotEmptyOrderByOverallScoreDesc(zipCode));

        restaurantsWithAllergies.addAll(restaurantRepository
                .findByZipcodeAndDairyAllergyScoresIsNotEmptyOrderByOverallScoreDesc(zipCode));

        return restaurantsWithAllergies;
    }
}
