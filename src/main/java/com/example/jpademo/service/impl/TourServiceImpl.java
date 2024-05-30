package com.example.jpademo.service.impl;

import com.example.jpademo.api.MapApi;
import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.mapper.TourMapper;
import com.example.jpademo.service.mapper.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final MapApiImpl mapApiImpl;

    public TourServiceImpl(TourRepository tourRepository, TourMapper tourMapper, MapApiImpl mapApiImpl) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
        this.mapApiImpl = mapApiImpl;
    }

    public TourDto saveTour(TourDto tourDto) {
        // Map DTO to Entity
        TourEntity tourEntity = tourMapper.mapToEntity(tourDto);

        //search tour information via OpenMapAPI
        tourEntity = mapApiImpl.searchTourInformation(tourEntity);

        // Save Entity
        TourEntity savedEntity = tourRepository.save(tourEntity);

        log.info("createdTour: {}", savedEntity);

        // Map saved Entity back to DTO
        return tourMapper.mapToDto(savedEntity);
    }

    /*
    @Override
    @Transactional
    public TourDto createTour(TourDto tourDto) {
        TourEntity tourEntity = tourMapper.mapToEntity(tourDto);

        MapApiImpl mapApiImpl = new MapApiImpl();
        tourEntity = mapApiImpl.searchTourInformation(tourEntity);

        TourEntity savedEntity = tourRepository.save(tourEntity);
        log.info("createdTour: {}", savedEntity);
        return tourMapper.mapToDto(savedEntity);
    }
    */

    @Override
    @Transactional(readOnly = true)
    public List<TourDto> findAllTours() {
        return tourRepository.findAll().stream()
                .map(tourMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TourDto findTourById(Long id) {
        return tourRepository.findById(id)
                .map(tourMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));
    }


    @Override
    @Transactional
    public TourDto updateTour(Long id, TourDto tourDto) {
        if (!tourRepository.existsById(id)) {
            throw new RuntimeException("Tour not found with id: " + id);
        }
        TourEntity tourEntity = tourMapper.mapToEntity(tourDto);
        tourEntity.setId(id);
        TourEntity updatedEntity = tourRepository.save(tourEntity);
        log.info("updatedTour: #{} to {}", id, updatedEntity);
        return tourMapper.mapToDto(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
        log.info("deletedTour: {}", id);
    }
}
