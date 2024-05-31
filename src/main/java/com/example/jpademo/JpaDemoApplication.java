package com.example.jpademo;

import com.example.jpademo.persistence.entities.PersonEntity;
import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.PersonRepository;
import com.example.jpademo.persistence.repositories.TourLogRepo;
import com.example.jpademo.persistence.repositories.TourRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class JpaDemoApplication {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourLogRepo tourLogRepo;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }


    void initFakeData() {
        PersonEntity p = PersonEntity.builder()
                .name("Markus")
                .email("holzerm@technikum-wien.at")
                .age(32)
                .build();

        personRepository.save(p);

        personRepository.save(PersonEntity.builder()
                .name("Anna")
                .age(33)
                .email("anna@technikum-wien.at")
                .build());

        System.out.printf("%d rows in table person\n", personRepository.count());
        personRepository.findAll().forEach(System.out::println);
    }

    @PostConstruct
    public void initTourData() {
        // Assuming you have repositories and builders set up for Tours and TourLogs
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

        // Example of adding a tour log
        TourLogEntity tourLog = TourLogEntity.builder()
                .details("Beautiful weather")
                .timestamp(LocalDateTime.now())
                .tour(tour)
                .build();
        tourLogRepo.save(tourLog);

        System.out.println("Initialized tours and tour logs.");
    }

}
