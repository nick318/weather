package com.weather.dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "weather")
@Getter
@Setter
@NoArgsConstructor
public class CityWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "main")
    private String main;
    @Column(name = "temp")
    private BigDecimal temp;
}
