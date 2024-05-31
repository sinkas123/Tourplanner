package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepo;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.dtos.TourLogDto;
import com.example.jpademo.service.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TourLogService {

    @Autowired
    private TourLogRepo tourLogRepo;
    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private TourRepository tourRepository;



    @Transactional
    public TourLogDto createTourLog(TourLogDto tourLogDto) {
        TourLogEntity tourLog = TourLogEntity.builder()
                .comment(tourLogDto.getComment())
                .timestamp(tourLogDto.getTimestamp())
                .tour(tourRepository.getReferenceById(tourLogDto.getTourId()))  // Get reference proxy to TourEntity
                .build();

        TourLogEntity savedLog = tourLogRepo.save(tourLog);
        return tourMapper.mapToTourLogDto(savedLog);
    }


    @Transactional(readOnly = true)
    public List<TourLogDto> findAllLogsByTour(Long tourId) {
        TourEntity tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found: " + tourId));
        return tourLogRepo.findByTour(tour).stream()
                .map(tourMapper::mapToTourLogDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteTourLog(Long id) {
        tourLogRepo.deleteById(id);
    }

    @Transactional
    public TourLogDto updateTourLog(Long id, TourLogDto tourLogDto) {
        TourLogEntity log = tourLogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour log not found: " + id));

        log.setDetails(tourLogDto.getDetails());
        log.setTimestamp(tourLogDto.getTimestamp());

        // Fetch the associated tour entity from the database
        TourEntity tour = tourRepository.findById(tourLogDto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + tourLogDto.getTourId()));

        log.setTour(tour);  // Set the fetched tour entity

        tourLogRepo.save(log);  // Save the updated log
        return tourMapper.mapToTourLogDto(log);  // Assuming you have a method to convert entity to DTO
    }



}
