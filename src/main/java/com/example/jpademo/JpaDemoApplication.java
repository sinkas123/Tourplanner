package com.example.jpademo;

import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.PersonRepository;
import com.example.jpademo.persistence.repositories.TourRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class JpaDemoApplication {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TourRepository tourRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @PostConstruct
    public void initTourData() {
        // Assuming you have repositories and builders set up for Tours and LogList
        TourEntity tour = TourEntity.builder()
                .name("Vienna City Tour")
                .description("Explore historic Vienna")
                .startLocation("Vienna")
                .endLocation("Vienna")
                .transportType("Bus")
                .tourDistance(15.0)
                .estimatedTime(Duration.ofHours(3))
                .build();
        tourRepository.save(tour);

        System.out.println("Initialized tours and tour logs.");
    }

}
