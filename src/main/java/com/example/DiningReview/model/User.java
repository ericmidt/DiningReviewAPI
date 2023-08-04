package com.example.DiningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String city;
    private String state;
    private String zipcode;
    private boolean peanutAllergy;
    private boolean eggAllergy;
    private boolean dairyAllergy;


    public User() {

    }

    public User(String username,
                String city,
                String state,
                String zipcode,
                boolean peanutAllergy,
                boolean eggAllergy,
                boolean dairyAllergy) 
    {

                    this.username = username;
                    this.city = city;
                    this.state = state;
                    this.zipcode = zipcode;
                    this.peanutAllergy = peanutAllergy;
                    this.eggAllergy = eggAllergy;
                    this.dairyAllergy = dairyAllergy;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isPeanutAllergy() {
        return peanutAllergy;
    }

    public void setPeanutAllergy(boolean peanutAllergy) {
        this.peanutAllergy = peanutAllergy;
    }

    public boolean isEggAllergy() {
        return eggAllergy;
    }

    public void setEggAllergy(boolean eggAllergy) {
        this.eggAllergy = eggAllergy;
    }

    public boolean isDairyAllergy() {
        return dairyAllergy;
    }

    public void setDairyAllergy(boolean dairyAllergy) {
        this.dairyAllergy = dairyAllergy;
    }

    

    
}
