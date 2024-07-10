package com.weather.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AvailableCity(Integer id, String name) {
}
