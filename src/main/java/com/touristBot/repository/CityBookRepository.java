package com.touristBot.repository;

import com.touristBot.domain.CityBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityBookRepository extends JpaRepository<CityBookEntity, Long> {

    CityBookEntity findByCity(String name);

    CityBookEntity findById(int id);

    List<CityBookEntity> findAll();

    void deleteById(int id);

}
