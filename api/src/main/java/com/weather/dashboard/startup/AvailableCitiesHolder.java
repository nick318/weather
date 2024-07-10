package com.weather.dashboard.startup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.dashboard.dto.AvailableCity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
public class AvailableCitiesHolder {
    private List<AvailableCity> availableCities;

    public AvailableCitiesHolder() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = getClass().getResourceAsStream("/cities.json"); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            this.availableCities = mapper.readValue(
                    reader,
                    new TypeReference<List<AvailableCity>>() {
                    }
            );
        }
    }

    public List<AvailableCity> getAvailableCities() {
        return this.availableCities;
    }
}
