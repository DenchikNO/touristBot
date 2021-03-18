package com.touristBot.repository;

import com.touristBot.domain.CityBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityBookRepository extends JpaRepository<CityBookEntity, Long> {
}
