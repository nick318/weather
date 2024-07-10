package com.weather.dashboard;

import com.weather.dashboard.dto.openweather.WeatherResponse;
import com.weather.dashboard.service.OpenWeatherApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestPropertySource(properties = {"api_key=key"})
class WeatherApplicationTests {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        @Primary
        public OpenWeatherApiService openWeatherApiServiceMocked() throws IOException {
            OpenWeatherApiService openWeatherApiService = Mockito.mock(OpenWeatherApiService.class);
            ClassPathResource classPathResource = new ClassPathResource("response.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Mockito.when(openWeatherApiService.getWeatherByCityId(anyInt())).thenAnswer((invocationOnMock -> {
                WeatherResponse weatherResponse = objectMapper.readValue(classPathResource.getFile(), WeatherResponse.class);
                Object id = invocationOnMock.getArgument(0);
                weatherResponse.setId(Long.valueOf(id.toString()));
                return weatherResponse;
            }));
            return openWeatherApiService;
        }
    }

    @Test
    void contextLoads() {
    }

}
