package com.touristBot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "city_book")
public class CityBookEntity implements Serializable {

    private static final long serialVersionUID = 6582894433025277790L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_book_id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

}
