package com.example.DiningReview.model;

import com.example.DiningReview.model.DiningReview.ApprovalStatus;

public class AdminReviewAction {

    public AdminReviewAction() {

    }

    public void processReview(DiningReview review) {
        if (review.getCommentary() == null) {
            review.setApprovalStatus(ApprovalStatus.REJECTED);
        } else {
            review.setApprovalStatus(ApprovalStatus.ACCEPTED);
        }
    }
}
