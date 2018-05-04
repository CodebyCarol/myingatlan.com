package com.codecool.myingatlan.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;



@Entity
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int district;
    private int squaremeter;
    private int price;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RealEstate> realEstates = new ArrayList<>();

    public RealEstate(String description, int district, int sqm, int price) {
        this.description = description;
        this.district = district;
        this.squaremeter = sqm;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
}
