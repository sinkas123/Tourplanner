package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepository;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.dtos.TourLogDto;
import com.example.jpademo.service.mapper.TourLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourLogService {

    private final TourLogRepository tourLogRepository;
    private final TourLogMapper tourLogMapper;
    private final TourRepository tourRepository;

    public TourLogService(TourLogRepository tourLogRepository, TourLogMapper tourLogMapper, TourRepository tourRepository) {
        this.tourLogRepository = tourLogRepository;
        this.tourLogMapper = tourLogMapper;
        this.tourRepository = tourRepository;
    }


    @Transactional
    public TourLogDto createTourLog(TourLogDto tourLogDto) {
        TourLogEntity tourLogEntity = tourLogMapper.mapToEntity(tourLogDto);

        TourLogEntity savedLog = tourLogRepository.save(tourLogEntity);
        return tourLogMapper.mapToDto(savedLog);
    }


    @Transactional(readOnly = true)
    public List<TourLogDto> findAllLogsByTour(Long tourId) {
        TourEntity tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found: " + tourId));
        return tourLogRepository.findByTourId(tour.getId()).stream()
                .map(tourLogMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteTourLog(Long id) {
        tourLogRepository.deleteById(id);
        log.info("deletedTourLog: {}", id);
    }

    @Transactional
    public TourLogDto updateTourLog(Long id, TourLogDto tourLogDto) {
        if(!tourLogRepository.existsById(id)) {
            throw new RuntimeException("TourLog not found: " + id);
        }
        TourLogEntity tourLogEntity = tourLogMapper.mapToEntity(tourLogDto);
        tourLogEntity.setId(id);
        TourLogEntity updatedLog = tourLogRepository.save(tourLogEntity);
        log.info("updatedLog: #{} to {}", id, updatedLog);
        return tourLogMapper.mapToDto(updatedLog);
    }



}
