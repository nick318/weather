package com.weather.dashboard.controller;

import com.weather.dashboard.entity.CityWeatherEntity;
import com.weather.dashboard.repository.CityWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final CityWeatherRepository cityWeatherRepository;

    @GetMapping
    public Page<CityWeatherEntity> cityWeather(@RequestParam(required = false) String sortBy,
                                               @RequestParam(required = false) String sortDir,
                                               @RequestParam Integer pageSize,
                                               @RequestParam Integer pageNum) {
        if (sortBy != null && sortDir != null) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.valueOf(sortDir), sortBy));
            return cityWeatherRepository.findAll(pageable);
        }
        return cityWeatherRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
}
