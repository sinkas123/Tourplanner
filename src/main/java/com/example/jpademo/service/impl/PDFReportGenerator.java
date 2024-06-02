package com.example.jpademo.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFReportGenerator {



    public static void createPDF(String filename, String content) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(content);
                contentStream.endText();
            } catch (IOException e) {
                System.err.println("Error writing to PDF: " + e.getMessage());
            }

            document.save(filename);
            System.out.println("PDF created at " + filename);

        } catch (IOException e) {
            System.err.println("Error creating PDF: " + e.getMessage());
        }
    }
}
