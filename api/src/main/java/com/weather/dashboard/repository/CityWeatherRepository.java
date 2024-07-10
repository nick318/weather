package com.weather.dashboard.repository;

import com.weather.dashboard.entity.CityWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE weather", nativeQuery = true)
    void truncate();
}
