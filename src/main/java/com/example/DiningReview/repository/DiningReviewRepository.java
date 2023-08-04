package com.example.DiningReview.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.DiningReview.model.DiningReview;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    // Finds all reviews by their approval status
    List<DiningReview> findByApprovalStatus(DiningReview.ApprovalStatus approvalStatus);
}
