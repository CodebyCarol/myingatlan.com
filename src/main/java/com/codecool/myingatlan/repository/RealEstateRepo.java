package main.java.com.codecool.myingatlan.repository;

import main.java.com.codecool.myingatlan.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepo extends JpaRepository<RealEstate, Long> {

    RealEstate findFirstById(long id);

    void save(RealEstate realEstate);
}
