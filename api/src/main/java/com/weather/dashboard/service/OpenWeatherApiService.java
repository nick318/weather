package com.weather.dashboard.service;

import com.weather.dashboard.dto.openweather.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public class OpenWeatherApiService {
    private final RestTemplate restTemplate;
    private final String apiKey;

    public WeatherResponse getWeatherByCityId(Integer id) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("id", id)
                .queryParam("units", "metric")
                .queryParam("appid", apiKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, WeatherResponse.class);
    }
}
