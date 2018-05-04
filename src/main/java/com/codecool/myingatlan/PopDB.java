package com.codecool.meetup.triangels;


import com.codecool.myingatlan.model.RealEstate;
import com.codecool.myingatlan.repository.RealEstateRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopDB {

    public PopDB(RealEstateRepo realEstateRepo) {


        //CREATE CLASSES
        RealEstate realEstate1 = new RealEstate ("Ez egy klassz ház", 9, 52, 22000000);
        RealEstate realEstate2 = new RealEstate ("Ez egy klassz ház", 7, 50, 24000000);
        RealEstate realEstate3 = new RealEstate ("Ez egy klassz ház", 8, 72, 32000000);
        RealEstate realEstate4 = new RealEstate ("Ez egy klassz ház", 5, 82, 62000000);

        realEstateRepo.save(realEstate1);
        realEstateRepo.save(realEstate2);
        realEstateRepo.save(realEstate3);
        realEstateRepo.save(realEstate4);
    }



}
