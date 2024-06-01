package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.repositories.TourLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private TourLogRepository tourLogRepository;

    public void generateReport() {
        String reportContent = "This is a sample report content for a tour log.";
        PDFReportGenerator.createPDF("C:\\Users\\sindi\\OneDrive\\Dokumente\\TourReport.pdf", reportContent);


    }
}
