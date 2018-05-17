package com.codecool.myingatlan.services;

import com.codecool.myingatlan.model.RealEstate;
import com.codecool.myingatlan.repository.RealEstateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class RealEstateService {

    @Autowired
    private RealEstateRepo realEstateRepo;

    public List<RealEstate> getAll() {

        return realEstateRepo.findAll();
    }

    public void addNewRealEstate(String descr, int distr, int sqm, int price) {
        // TODO meghivni a python APIt - http get requestet küld a python apinak (localhost:5xxx/calculate/id/?...-nek)
        RealEstate realEstate = new RealEstate(descr, distr, sqm, price);
        realEstateRepo.save(realEstate);
    }

    public RealEstate getOne(Long id){
        return realEstateRepo.findFirstById(id);
    }
}
