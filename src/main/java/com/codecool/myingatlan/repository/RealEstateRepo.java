package com.codecool.myingatlan.repository;

import com.codecool.myingatlan.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepo extends JpaRepository<RealEstate, Long> {

    RealEstate findFirstById(long id);
    RealEstate findDistinctFirstByDescriptionIsContaining(String descr);

}
