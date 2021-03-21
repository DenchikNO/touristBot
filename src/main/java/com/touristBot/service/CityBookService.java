package com.touristBot.service;

import com.touristBot.domain.CityBookEntity;
import com.touristBot.repository.CityBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityBookService {

    @Autowired
    private CityBookRepository cityBookRepository;

    public List<CityBookEntity> takeCityBookList() {
        return cityBookRepository.findAll();
    }

    public CityBookEntity takeCityByName(String name) {
        return cityBookRepository.findByCity(name);
    }

    public CityBookEntity takeCityById(int id) {
        return cityBookRepository.findById(id);
    }

    public void deleteById(int id) {
        cityBookRepository.deleteById(id);
    }

}
