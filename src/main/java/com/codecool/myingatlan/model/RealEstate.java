package com.codecool.myingatlan.model;

import javax.persistence.*;


@Entity
@Table(name="realestates")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int district;
    private int squaremeter;
    private int price;


    public RealEstate(String description, int district, int sqm, int price) {
        this.description = description;
        this.district = district;
        this.squaremeter = sqm;
        this.price = price;

    }

    public RealEstate() {};


    public Long getId() {
        return id;
    }

    public String getDescription() {return description;}
}
