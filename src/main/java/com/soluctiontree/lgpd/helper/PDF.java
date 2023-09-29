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

import java.io.IOException;

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
    }
    
    public static void RedateCEP(String path, String output) throws IOException{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);
        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("[0-9]{2}.[0-9]{3}-[0-9]{3}"));
        PdfAutoSweepTools autoSweep = new PdfAutoSweepTools(cleanupStrategy);
        autoSweep.highlight(pdfDoc);
        doc.close();
    }
    
     public static void RedateCPF(String path, String output) throws IOException{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);
        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}"));
        PdfAutoSweepTools autoSweep = new PdfAutoSweepTools(cleanupStrategy);
        autoSweep.highlight(pdfDoc);
        doc.close();
    }
}
