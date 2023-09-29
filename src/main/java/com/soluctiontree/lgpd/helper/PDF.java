/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.pdfcleanup.autosweep.ICleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.PdfAutoSweep;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import java.io.IOException;

/**
 *
 * @author User
 */
public class PDF {
    public static void RedateWord(String path, String output) throws IOException{
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(path), new PdfWriter(new FileOutputStream(output)));
        Document doc = new Document(pdfDoc);

        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("159.183.907-66", Pattern.CASE_INSENSITIVE));
        PdfAutoSweep autoSweep = new PdfAutoSweep(cleanupStrategy);
        autoSweep.cleanUp(pdfDoc);

        doc.close();
    }
}
