package com.example.jpademo.service.mapper;

import com.example.jpademo.service.dtos.TourDto;

import java.util.List;

public interface TourService {


    List<TourDto> findAllTours();

    TourDto findTourById(Long id);

    TourDto createTour(TourDto tourDto);

    TourDto updateTour(Long id, TourDto tourDto);

    void deleteTour(Long id);

}
