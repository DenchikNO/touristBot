package com.touristBot.controller;

import com.touristBot.domain.CityBookEntity;
import com.touristBot.repository.CityBookRepository;
import com.touristBot.service.CityBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TouristBotController {

    @Autowired
    private CityBookService cityBookService;

    @Autowired
    private CityBookRepository cityBookRepository;

    @GetMapping
    public String controller() {
        return cityBookService.takeCityBookList().toString();
    }

    @GetMapping("{id}")
    public String takeCityById(@PathVariable int id) {
        return cityBookService.takeCityById(id).toString();
    }

    @PostMapping("/delete/{id}")
    public void deleteCityById(@PathVariable int id) {
        cityBookService.deleteById(id);
    }

    @PostMapping
    public void addCity(@RequestBody CityBookEntity cityBookEntity) {
        cityBookRepository.save(cityBookEntity);
    }

    @PostMapping("/edit/{id}")
    public CityBookEntity editCity(@PathVariable int id, @RequestBody CityBookEntity cityBookEntity) {
        CityBookEntity cityDb = cityBookService.takeCityById(id);
        if (cityBookEntity.getCity() != null) {
            cityDb.setCity(cityBookEntity.getCity());
        }
        if (cityBookEntity.getDescription() != null) {
            cityDb.setDescription(cityBookEntity.getDescription());
        }
        cityBookRepository.save(cityDb);
        return cityDb;
    }

}
