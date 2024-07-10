package com.weather.dashboard.dto.openweather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
