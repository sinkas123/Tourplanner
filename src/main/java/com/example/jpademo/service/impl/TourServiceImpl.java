package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.TourRepository;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.mapper.TourMapper;
import com.example.jpademo.service.mapper.TourService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private TourMapper tourMapper;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDto> findAllTours() {
        return tourRepository.findAll().stream()
                .map(tourMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly=true)
    public TourDto findTourById(Long id) {
        return tourRepository.findById(id)
                .map(tourMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Tour not found with id: " + id));
    }

    @Override
    @Transactional
    public TourDto createTour(TourDto tourDto) {
        TourEntity tourEntity = tourMapper.mapToEntity(tourDto);
        TourEntity savedEntity = tourRepository.save(tourEntity);
        return tourMapper.mapToDto(savedEntity);
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
        return tourMapper.mapToDto(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

}
