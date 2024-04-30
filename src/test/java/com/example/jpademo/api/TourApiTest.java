package com.example.jpademo.api;

import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.mapper.TourService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class TourApiTest {

    @InjectMocks
    TourApi tourApi;

    @Mock
    TourService tourService;

    @Mock
    TourDto tourDto;

    @Test
    void testConstructor() {
        TourApi tourApi = new TourApi(tourService);
        Assertions.assertInstanceOf(TourApi.class, tourApi);
    }

    @Test
    void testGetAllTours() {
        ResponseEntity<List<TourDto>> response = tourApi.getAllTours();
        Assertions.assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetTourById() {
        assertNullBodyStatusOK(tourApi.getTourById(0L));
    }

    @Test
    void testCreateTour() {
        assertNullBodyStatusOK(tourApi.createTour(tourDto));
    }

    @Test
    void testUpdateTour() {
        assertNullBodyStatusOK(tourApi.updateTour(0L, tourDto));
    }

    @Test
    void testDeleteTour() {
        assertNullBodyStatusOK(tourApi.deleteTour(0L));
    }

    void assertNullBodyStatusOK(ResponseEntity<?> response) {
        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
