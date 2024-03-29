package com.example.jpademo.persistence.repositories;


import com.example.jpademo.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<TourEntity,Long> {



}
