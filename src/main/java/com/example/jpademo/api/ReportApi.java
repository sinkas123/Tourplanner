package com.example.jpademo.api;

import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.dtos.TourLogDto;
import com.example.jpademo.service.impl.ReportService;
import com.example.jpademo.service.impl.TourLogService;
import com.example.jpademo.service.impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "report")
public class ReportApi {

    @Autowired
    private ReportService reportService;
    @Autowired
    private TourServiceImpl tourServiceImpl;
    @Autowired
    private TourLogService tourLogService;


    @GetMapping("/tour/{tourId}")
    public boolean getTest(@PathVariable Long tourId, @RequestParam String target) throws IOException {
        TourDto tour = tourServiceImpl.findTourById(tourId);
        List<TourLogDto> logs = tourLogService.findAllLogsByTour(tourId);
        reportService.createTourReport(target, tour, logs);
            return true;
    }

    @GetMapping("/summary")
    public String getSummary(@RequestParam String target) throws IOException {
        reportService.createSummaryReport(target);
        return target;
    }


}