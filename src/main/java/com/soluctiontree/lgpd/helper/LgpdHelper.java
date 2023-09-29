/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.soluctiontree.lgpd.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.pdfcleanup.autosweep.ICleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.PdfAutoSweep;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;


import java.io.*;
import java.util.regex.Pattern;
/**
 *
 * @author Guilherme-Vianna
 */
public class LgpdHelper {

    public static void main(String[] args) throws FileNotFoundException, IOException{
      SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PdfFileSelector pdfFileSelector = new PdfFileSelector();
                pdfFileSelector.setVisible(true);
            }
        });
      
        String PDF = "F:\\Alice.pdf";
        String DEST = "F:\\output_redacted.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(PDF), new PdfWriter(new FileOutputStream(DEST)));
        Document doc = new Document(pdfDoc);

        ICleanupStrategy cleanupStrategy = new RegexBasedCleanupStrategy(Pattern.compile("Alice", Pattern.CASE_INSENSITIVE)).setRedactionColor(ColorConstants.PINK);
        PdfAutoSweep autoSweep = new PdfAutoSweep(cleanupStrategy);
        autoSweep.cleanUp(pdfDoc);

        doc.close();
    }
}
