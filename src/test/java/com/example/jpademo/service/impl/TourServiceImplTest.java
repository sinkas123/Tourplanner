package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.mapper.TourMapper;
import com.example.jpademo.service.mapper.TourService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TourServiceImplTest {

    @InjectMocks
    TourServiceImpl tourService;

    @Mock
    TourRepository tourRepository;

    @Mock
    TourMapper tourMapper;

    @Mock
    TourDto tourDto;

    @Mock
    TourEntity tourEntity;

    @Test
    void testConstructor() {
        TourService tourService = new TourServiceImpl(tourRepository, tourMapper);
        Assertions.assertInstanceOf(TourService.class, tourService);
    }

    @Test
    void testSaveTour() {
        Assertions.assertNull(tourService.saveTour(tourDto));
    }

    @Test
    void testFindAllTours() {
        Assertions.assertTrue(tourService.findAllTours().isEmpty());
    }

    @Test
    void testFindTourById() {
        Mockito.when(tourRepository.findById(0L)).thenReturn(Optional.of(tourEntity));
        Mockito.when(tourMapper.mapToDto(tourEntity)).thenReturn(tourDto);
        Assertions.assertEquals(tourDto, tourService.findTourById(0L));
    }

    @Test
    void testCreateTour() {
        Assertions.assertNull(tourService.createTour(tourDto));
    }

    @Test
    void testUpdateTour() {
        Mockito.when(tourRepository.existsById(0L)).thenReturn(true);
        Mockito.when(tourMapper.mapToEntity(tourDto)).thenReturn(tourEntity);
        Assertions.assertNull(tourService.updateTour(0L, tourDto));
    }

    @Test
    void testUpdateTourNotFound() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> tourService.updateTour(0L, tourDto));
        Assertions.assertEquals("Tour not found with id: 0", runtimeException.getMessage());
    }

    @Test
    void testDeleteTour() {
        tourService.deleteTour(0L);
        Mockito.verify(tourRepository).deleteById(0L);
    }

}
