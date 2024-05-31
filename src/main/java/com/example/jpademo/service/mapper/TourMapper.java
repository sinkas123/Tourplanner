package com.example.jpademo.service.mapper;

import com.example.jpademo.persistence.entities.PersonEntity;
import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.service.dtos.PersonDto;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.dtos.TourLogDto;
import lombok.Builder;
import org.springframework.stereotype.Component;


@Component
public class TourMapper extends AbstractMapper<TourEntity, TourDto> {

    @Override
    public TourDto mapToDto(TourEntity source) {
        if (source == null) {
            return null;
        }
        return TourDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .startLocation(source.getStartLocation())
                .endLocation(source.getEndLocation())
                .transportType(source.getTransportType())
                .tourDistance(source.getTourDistance())
                .estimatedTime(source.getEstimatedTime())
                .routeImagePath(source.getRouteImagePath())
                .build();
    }

    public TourEntity mapToEntity(TourDto source) {
        if (source == null) {
            return null;
        }
        TourEntity entity = new TourEntity();
        entity.setId(source.getId());
        entity.setName(source.getName());
        entity.setDescription(source.getDescription());
        entity.setStartLocation(source.getStartLocation());
        entity.setEndLocation(source.getEndLocation());
        entity.setTransportType(source.getTransportType());
        entity.setTourDistance(source.getTourDistance());
        entity.setEstimatedTime(source.getEstimatedTime());
        entity.setRouteImagePath(source.getRouteImagePath());
        return entity;
    }

    // Method to convert TourLogEntity to TourLogDto
    public TourLogDto mapToTourLogDto(TourLogEntity source) {
        if (source == null) {
            return null;
        }
        return TourLogDto.builder()
                .id(source.getId())
                .details(source.getDetails())
                .timestamp(source.getTimestamp())
                .tourId(source.getTour().getId())  // Assuming the tour is not null
                .build();
    }

    // Method to convert TourLogDto to TourLogEntity
    public TourLogEntity mapToTourLogEntity(TourLogDto source, TourEntity tour) {
        if (source == null) {
            return null;
        }
        TourLogEntity entity = new TourLogEntity();
        entity.setId(source.getId());
        entity.setComment(source.getComment());
        entity.setTimestamp(source.getTimestamp());
        entity.setTour(tour);
        return entity;
    }



}
