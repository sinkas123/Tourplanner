package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.entities.TourLogEntity;
import com.example.jpademo.persistence.repositories.TourLogRepo;
import com.example.jpademo.persistence.repositories.TourLogRepo;
import com.example.jpademo.service.impl.PDFReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TourLogRepo tourLogRepository;

    public void generateReport() {
        String reportContent = "This is a sample report content for a tour log.";
        PDFReportGenerator.createPDF("C:\\Users\\sindi\\OneDrive\\Dokumente\\TourReport.pdf", reportContent);


    }
}
