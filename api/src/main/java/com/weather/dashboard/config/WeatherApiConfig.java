package com.weather.dashboard.config;

import com.weather.dashboard.service.OpenWeatherApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApiConfig {
    @Value("${api_key}")
    private String apiKey;

    @Bean
    public OpenWeatherApiService openWeatherApiService(RestTemplate restTemplate) {
        return new OpenWeatherApiService(restTemplate, apiKey);
    }
}
