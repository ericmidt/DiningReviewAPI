package com.example.DiningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;

@Entity
public class DiningReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    
    private Long restaurantId;

    private Integer peanutAllergyScore;
    private Integer eggAllergyScore;
    private Integer dairyAllergyScore;
    private String commentary;


    public enum ApprovalStatus {
        PENDING, ACCEPTED, REJECTED
    }

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;


    public DiningReview() {

    }

    public DiningReview(String username, 
                        Long restaurantId, 
                        Integer peanutAllergyScore,
                        Integer eggAllergyScore,
                        Integer dairyAllergyScore,
                        String commentary,
                        ApprovalStatus approvalStatus) {
                        
                this.username = username;
                this.restaurantId = restaurantId;
                this.peanutAllergyScore = peanutAllergyScore;
                this.eggAllergyScore = eggAllergyScore;
                this.dairyAllergyScore = dairyAllergyScore;
                this.commentary = commentary;
                this.approvalStatus = approvalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getPeanutAllergyScore() {
        return peanutAllergyScore;
    }

    public void setPeanutAllergyScore(Integer peanutAllergyScore) {
        this.peanutAllergyScore = peanutAllergyScore;
    }

    public Integer getEggAllergyScore() {
        return eggAllergyScore;
    }

    public void setEggAllergyScore(Integer eggAllergyScore) {
        this.eggAllergyScore = eggAllergyScore;
    }

    public Integer getDairyAllergyScore() {
        return dairyAllergyScore;
    }

    public void setDairyAllergyScore(Integer dairyAllergyScore) {
        this.dairyAllergyScore = dairyAllergyScore;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }



}
