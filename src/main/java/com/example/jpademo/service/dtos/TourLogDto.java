package com.example.jpademo.service.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class TourLogDto {
    private Long id;
    private String comment;
    private Integer difficulty;
    private Integer rating;
    private Integer totalDistance;
    private LocalDateTime timestamp;
    private Long tourId;

}
