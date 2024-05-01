package com.example.jpademo;

import com.example.jpademo.persistence.entities.PersonEntity;
import com.example.jpademo.persistence.repositories.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaDemoApplication {

    private static final Log logger = LogFactory.getLog(JpaDemoApplication.class);

    private final PersonRepository personRepository;

    @Autowired
    public JpaDemoApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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

        logger.info(String.format("%d rows in table person%n", personRepository.count()));
        personRepository.findAll().forEach(logger::info);
    }

}
