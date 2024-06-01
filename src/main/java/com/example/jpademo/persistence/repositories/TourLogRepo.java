package com.example.jpademo.persistence.repositories;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface TourLogRepo extends JpaRepository<TourLogEntity, Long> {
        List<TourLogEntity> findByTourId(Long tourId);

    }

