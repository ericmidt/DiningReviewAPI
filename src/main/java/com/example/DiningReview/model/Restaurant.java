package com.example.DiningReview.model;

import java.util.*;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String typeOfFood;
    private String address;
    private String zipcode;
    private boolean delivery;

    @ElementCollection
    private List<Integer> peanutAllergyScores = new ArrayList<>();
    
    @ElementCollection
    private List<Integer> eggAllergyScores = new ArrayList<>();
    
    @ElementCollection
    private List<Integer> dairyAllergyScores = new ArrayList<>();

    private int overallScore;

    public Restaurant () {

    }

    public Restaurant (String name, String typeOfFood, String address, String zipcode, boolean delivery,
            int peanutAllergyScore, int eggAllergyScore, int dairyAllergyScore) {
                this.name = name;
                this.typeOfFood = typeOfFood;
                this.address = address;
                this.zipcode = zipcode;
                this.delivery = delivery;
                this.peanutAllergyScores.add(peanutAllergyScore);
                this.eggAllergyScores.add(eggAllergyScore);
                this.dairyAllergyScores.add(dairyAllergyScore);
                updateOverallScore();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    private void updateOverallScore() {
        this.overallScore = (calculateAverageScore(this.peanutAllergyScores)
                            + calculateAverageScore(this.eggAllergyScores)
                            + calculateAverageScore(this.dairyAllergyScores)) / 3;
    }

    public int getOverallScore() {
        updateOverallScore();
        return overallScore;
    }

    public int calculateAverageScore(List<Integer> scores) {
        int sum = 0;

        for (int score : scores) {
            sum += score;
        }

        if (scores.size() > 0) {
            return sum / scores.size();
        } else {
            return 0;
        }
    }

}
