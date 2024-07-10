package com.weather.dashboard.startup;

import com.weather.dashboard.dto.openweather.Main;
import com.weather.dashboard.dto.openweather.Weather;
import com.weather.dashboard.dto.openweather.WeatherResponse;
import com.weather.dashboard.entity.CityWeatherEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherMapper {
    public CityWeatherEntity mapToEntity(WeatherResponse weatherResponse) {
        CityWeatherEntity cityWeatherEntity = new CityWeatherEntity();
        cityWeatherEntity.setCityId(weatherResponse.getId().intValue());
        cityWeatherEntity.setCityName(weatherResponse.getName());

        if (weatherResponse.getWeather() != null && !weatherResponse.getWeather().isEmpty()) {
            Weather weather = weatherResponse.getWeather().getFirst();
            cityWeatherEntity.setMain(weather.getMain());
        }

        if (weatherResponse.getMain() != null) {
            Main main = weatherResponse.getMain();
            cityWeatherEntity.setTemp(main.getTemp());
        }
        return cityWeatherEntity;
    }
}
