package com.example.jpademo.service.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

@ExtendWith(MockitoExtension.class)
class TourDtoTest {

    @Mock
    Duration estimatedTime;

    @Test
    void testAllArgsConstructor() {
        TourDto tourDto = new TourDto(0L, "name", "description", "start location", "end location", "transport type", 0.0, estimatedTime, "route image path");
        assertEquals(tourDto);
    }

    @Test
    void testNoArgsConstructor() {
        TourDto tourDto = new TourDto();
        Assertions.assertInstanceOf(TourDto.class, tourDto);
    }

    @Test
    void testSetters() {
        TourDto tourDto = new TourDto();
        tourDto.setId(0L);
        tourDto.setName("name");
        tourDto.setDescription("description");
        tourDto.setStartLocation("start location");
        tourDto.setEndLocation("end location");
        tourDto.setTransportType("transport type");
        tourDto.setTourDistance(0.0);
        tourDto.setEstimatedTime(estimatedTime);
        tourDto.setRouteImagePath("route image path");
        assertEquals(tourDto);
    }

    void assertEquals(TourDto tourDto) {
        Assertions.assertEquals(0L, tourDto.getId());
        Assertions.assertEquals("name", tourDto.getName());
        Assertions.assertEquals("description", tourDto.getDescription());
        Assertions.assertEquals("start location", tourDto.getStartLocation());
        Assertions.assertEquals("end location", tourDto.getEndLocation());
        Assertions.assertEquals("transport type", tourDto.getTransportType());
        Assertions.assertEquals(0.0, tourDto.getTourDistance());
        Assertions.assertEquals(estimatedTime, tourDto.getEstimatedTime());
        Assertions.assertEquals("route image path", tourDto.getRouteImagePath());
    }

}
