/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import java.io.FileOutputStream;
import java.util.regex.Pattern;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.pdfcleanup.autosweep.ICleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.PdfAutoSweepTools;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;
import java.io.File;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.pdfocr.OcrPdfCreator;
import com.itextpdf.pdfocr.tesseract4.Tesseract4LibOcrEngine;
import com.itextpdf.pdfocr.tesseract4.Tesseract4OcrEngineProperties;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class PDF {
    public static void RedateRegex(String path, String output, String regex) throws IOException{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);
        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile(regex));
        PdfAutoSweepTools autoSweep = new PdfAutoSweepTools(cleanupStrategy);
        autoSweep.highlight(pdfDoc);
        doc.close();
        
        String outputPath = output.substring(0, output.lastIndexOf('.')) + "_redate.pdf";
        File renamedFile = new File(outputPath);
        new File(output).renameTo(renamedFile);
    }
    
    public static void RedateCEP(String path, String output) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);
        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("[0-9]{2}.[0-9]{3}-[0-9]{3}"));
        PdfAutoSweepTools autoSweep = new PdfAutoSweepTools(cleanupStrategy);
        autoSweep.highlight(pdfDoc);
        doc.close();

        // Append "_redate" before the file extension
        String outputPath = output.substring(0, output.lastIndexOf('.')) + "_redate.pdf";
        File renamedFile = new File(outputPath);
        new File(output).renameTo(renamedFile);
    }

    public static void RedateCPF(String path, String output) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);
        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}"));
        PdfAutoSweepTools autoSweep = new PdfAutoSweepTools(cleanupStrategy);
        autoSweep.highlight(pdfDoc);
        doc.close();

        // Append "_redate" before the file extension
        String outputPath = output.substring(0, output.lastIndexOf('.')) + "_redate.pdf";
        File renamedFile = new File(outputPath);
        new File(output).renameTo(renamedFile);
    }
    
    public static void OCRImage() throws FileNotFoundException, IOException{
        Tesseract4OcrEngineProperties tesseract4OcrEngineProperties = new Tesseract4OcrEngineProperties();
        List LIST_IMAGES_OCR = Arrays.asList(new File("F:\\Sample_Scanned_PDF.pdf")); //replace with the image file name you have uploaded
        String OUTPUT_PDF = "F:\\Sample_Scanned_PDF_OCR.pdf";
       
        Tesseract4LibOcrEngine tesseractReader = new Tesseract4LibOcrEngine(tesseract4OcrEngineProperties);
        tesseract4OcrEngineProperties.setPathToTessData(new File("F:\\"));

        OcrPdfCreator ocrPdfCreator = new OcrPdfCreator(tesseractReader);
        try (PdfWriter writer = new PdfWriter(OUTPUT_PDF)) {
            ocrPdfCreator.createPdf(LIST_IMAGES_OCR, writer).close();
        }
    }
}
