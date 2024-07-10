package com.weather.dashboard.service;

import com.weather.dashboard.startup.CityWeatherLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyWeatherUpdater {
    private final CityWeatherLoader cityWeatherLoader;

    @Scheduled(cron = "0 0 0 * * ?", zone = "CET")
    public void update() {
        this.cityWeatherLoader.loadCities();
    }
}
