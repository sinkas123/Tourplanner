package com.example.jpademo.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

@ExtendWith(MockitoExtension.class)
class TourEntityTest {

    @Mock
    Duration estimatedTime;

    @Test
    void testAllArgsConstructor() {
        TourEntity tourEntity = new TourEntity(0L, "name", "description", "start location", "end location", "transport type", 0.0, estimatedTime, "route image path");
        assertEquals(tourEntity);
    }

    @Test
    void testNoArgsConstructor() {
        TourEntity tourEntity = new TourEntity();
        Assertions.assertInstanceOf(TourEntity.class, tourEntity);
    }

    @Test
    void testSetters() {
        TourEntity tourEntity = new TourEntity();
        tourEntity.setId(0L);
        tourEntity.setName("name");
        tourEntity.setDescription("description");
        tourEntity.setStartLocation("start location");
        tourEntity.setEndLocation("end location");
        tourEntity.setTransportType("transport type");
        tourEntity.setTourDistance(0.0);
        tourEntity.setEstimatedTime(estimatedTime);
        tourEntity.setRouteImagePath("route image path");
        assertEquals(tourEntity);
    }

    void assertEquals(TourEntity tourEntity) {
        Assertions.assertEquals(0L, tourEntity.getId());
        Assertions.assertEquals("name", tourEntity.getName());
        Assertions.assertEquals("description", tourEntity.getDescription());
        Assertions.assertEquals("start location", tourEntity.getStartLocation());
        Assertions.assertEquals("end location", tourEntity.getEndLocation());
        Assertions.assertEquals("transport type", tourEntity.getTransportType());
        Assertions.assertEquals(0.0, tourEntity.getTourDistance());
        Assertions.assertEquals(estimatedTime, tourEntity.getEstimatedTime());
        Assertions.assertEquals("route image path", tourEntity.getRouteImagePath());
    }

}
