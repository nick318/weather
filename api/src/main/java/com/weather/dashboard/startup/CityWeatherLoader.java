package com.weather.dashboard.startup;

import com.weather.dashboard.dto.AvailableCity;
import com.weather.dashboard.dto.openweather.WeatherResponse;
import com.weather.dashboard.entity.CityWeatherEntity;
import com.weather.dashboard.repository.CityWeatherRepository;
import com.weather.dashboard.service.OpenWeatherApiService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityWeatherLoader implements ApplicationListener<ApplicationStartedEvent> {
    private final CityWeatherRepository cityWeatherRepository;
    private final AvailableCitiesHolder availableCitiesHolder;
    private final OpenWeatherApiService openWeatherApiService;
    private final WeatherMapper weatherMapper;

    @Transactional
    @SneakyThrows
    public void loadCities() {
        cityWeatherRepository.truncate();
        List<AvailableCity> availableCities = availableCitiesHolder.getAvailableCities();

        CompletableFuture<List<WeatherResponse>> weatherListFuture = CompletableFuture.supplyAsync(() -> {
            return availableCities
                    .stream()
                    .parallel()
                    .map(availableCity -> openWeatherApiService.getWeatherByCityId(availableCity.id()))
                    .collect(Collectors.toList());
        }, new ForkJoinPool(100));

        List<WeatherResponse> weatherResponses = weatherListFuture.get(30, TimeUnit.SECONDS);
        List<CityWeatherEntity> entities = weatherResponses
                .stream()
                .map(weatherMapper::mapToEntity)
                .toList();
        cityWeatherRepository.saveAll(entities);
    }

    @Transactional
    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        this.loadCities();
    }
}
