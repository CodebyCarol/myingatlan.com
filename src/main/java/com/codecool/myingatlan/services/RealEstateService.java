package main.java.com.codecool.myingatlan.services;

import main.java.com.codecool.myingatlan.model.RealEstate;
import main.java.com.codecool.myingatlan.repository.RealEstateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class RealEstateService {

    @Autowired
    private RealEstateRepo realEstateRepo;

    public List<RealEstate> getAll() {
        return RealEstateRepo.findAll();
    }

    public void addNewRealEstate(String descr, int distr, int sqm, int price) {
        RealEstate realEstate = new RealEstate(descr, distr, sqm, price);
        realEstateRepo.save(realEstate);
    }




}
