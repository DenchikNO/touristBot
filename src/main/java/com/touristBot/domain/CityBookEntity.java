package com.touristBot.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "city_book")
public class CityBookEntity implements Serializable {

    private static final long serialVersionUID = 6582894433025277790L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_book_id")
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

}
