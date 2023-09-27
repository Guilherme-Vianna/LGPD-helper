/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author User
 */
public class PDF {
    public static void Create(String filename) throws FileNotFoundException 
    { 
        System.out.println("Generating PDF!");
        String path = "F:\\"+ filename + ".pdf"; 
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        document.close(); 
    }
    public static void Read(String filename) throws FileNotFoundException, IOException 
    {
       PdfDocument pdfDoc = new PdfDocument(new PdfReader("F:\\Documento.pdf"));
       
       LocationTextExtractionStrategy strategy = new LocationTextExtractionStrategy();
        
       PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
       
       parser.processPageContent(pdfDoc.getPage(2));
       byte[] content = strategy.getResultantText().getBytes("UTF-8");
       try (FileOutputStream stream = new FileOutputStream("F:\\TextoDoDocumento.txt")) {
            stream.write(content);
       }
        pdfDoc.close();
    } 
}
