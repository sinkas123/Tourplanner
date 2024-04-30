package com.example.jpademo.service.mapper;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.service.dtos.TourDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TourMapperTest {

    @InjectMocks
    TourMapper tourMapper;

    @Test
    void testMapToDto() {
        TourEntity source = Mockito.mock(TourEntity.class);
        Assertions.assertNotNull(tourMapper.mapToDto(source));
    }

    @Test
    void testMapToDtoNullSource() {
        Assertions.assertNull(tourMapper.mapToDto((TourEntity) null));
    }

    @Test
    void testMapToEntity() {
        TourDto source = Mockito.mock(TourDto.class);
        Assertions.assertNotNull(tourMapper.mapToEntity(source));
    }

    @Test
    void testMapToEntityNullSource() {
        Assertions.assertNull(tourMapper.mapToEntity(null));
    }

}
