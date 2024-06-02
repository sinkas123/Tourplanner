package com.example.jpademo.service.impl;

import com.example.jpademo.persistence.repositories.TourLogRepository;
import com.example.jpademo.service.dtos.TourDto;
import com.example.jpademo.service.dtos.TourLogDto;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportService {

    @Autowired
    private TourLogRepository tourLogRepository;
    @Autowired
    private TourLogService tourLogService;



    /*
    public void generateReport() {
        String reportContent = "This is a sample report content for a tour log.";
        PDFReportGenerator.createPDF("C:\\Users\\sindi\\OneDrive\\Dokumente\\TourReport.pdf", reportContent);
    }
    */

    public void createTourReport(String target, TourDto tour, java.util.List<TourLogDto> logs) throws IOException {
        //String completeTarget = "../../persistence/" + target;
        String basePath = getClass().getClassLoader().getResource("").getPath(); // Get base path of resources
        System.out.println(basePath);
        String completeTarget = basePath + "../../src/main/java/com/example/jpademo/persistence/report_files/" + target;
        PdfWriter writer = new PdfWriter(completeTarget);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        //tour details
        Paragraph listHeader = new Paragraph("Tour Details")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.BLUE);
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD));
        list.add(new ListItem("Tour name: " + tour.getName()))
                .add(new ListItem("Description: " + tour.getDescription()))
                .add(new ListItem("Start: " + tour.getStartLocation()))
                .add(new ListItem("Finish: " + tour.getEndLocation()))
                .add(new ListItem("Transport Type: " + tour.getTransportType()))
                .add(new ListItem("Distance: " + tour.getTourDistance()))
                .add(new ListItem("Duration: " + tour.getEstimatedTime()))
                .add(new ListItem("Popularity: " + tour.getPopularity()))
                .add(new ListItem("Child Friendliness: " + tour.getChildFriendliness()));
        document.add(listHeader);
        document.add(list);

        //tour logs
        Paragraph tableHeader = new Paragraph("Tour logs")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.GREEN);
        document.add(tableHeader);
        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        table.addHeaderCell("Date");
        table.addHeaderCell("Duration");
        table.addHeaderCell("Distance");
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);

        for (TourLogDto log : logs){
            table.addCell(String.valueOf(log.getTimestamp()));
            table.addCell(String.valueOf(log.getTotalTime()));
            table.addCell(String.valueOf(log.getTotalDistance()));
        }
        document.add(table);
        document.close();
    }

    public static boolean createTest() throws IOException {
        PdfWriter writer = new PdfWriter("test.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph paragraph = new Paragraph("This is a test");
        document.add(paragraph);
        document.close();

        return true;
    }
}
