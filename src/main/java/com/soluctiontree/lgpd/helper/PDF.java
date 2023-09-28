/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.kernel.pdf.PdfName;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import java.io.IOException;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
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
    public static void DetectCpf() throws FileNotFoundException, IOException 
    {
       PdfReader pdf = new PdfReader("F:\\Documento.pdf");
       PdfDocument pdfDoc = new PdfDocument(pdf); 
       int pages = pdfDoc.getNumberOfPages(); 
       
        for(int i=1; i<=pages; i++) { 
	  String pageContent = 
	  	PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
         

        String cpfPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}";
        Pattern pattern = Pattern.compile(cpfPattern);

        Matcher matcher = pattern.matcher(pageContent);

        while (matcher.find()) {
            String cpf = matcher.group();

            cpf = cpf.replaceAll("[.-]", "");

            if (isValidCPF(cpf)) {
                System.out.println("Found valid CPF: " + cpf);
            } else {
                System.out.println("Found invalid CPF: " + cpf);
            }
        }
        }
        
        String inputFilePath = "F:\\documento.pdf"; // Replace with your input PDF file path
        String outputFilePath = "F:\\output.pdf"; // Replace with your output PDF file path
        String searchWord = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}";
        
        censorPdfText(inputFilePath, outputFilePath, searchWord);
    }
    
    public static void censorPdfText(String inputFilePath, String outputFilePath, String searchWord) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(inputFilePath), new PdfWriter(outputFilePath));
        Document document = new Document(pdfDoc);

        for (int pageNum = 1; pageNum <= pdfDoc.getNumberOfPages(); pageNum++) {
            PdfPage page = pdfDoc.getPage(pageNum);

            String text = PdfTextExtractor.getTextFromPage(page);
            text = text.replaceAll(searchWord, ""); // Remove CPFs

            // Create a new page with the modified text
            PdfPage newPage = pdfDoc.addNewPage();
            document.add(new AreaBreak());
            document.add(new Paragraph(text));

            // Copy annotations (e.g., links) from the original page to the new page
            PdfArray annotations = page.getPdfObject().getAsArray(PdfName.Annots);
            if (annotations != null) {
                newPage.getPdfObject().put(PdfName.Annots, annotations.copyTo(pdfDoc));
            }
        }

        document.close();
        pdfDoc.close();
    }
     
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        boolean allDigitsSame = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allDigitsSame = false;
                break;
            }
        }
        if (allDigitsSame) {
            return false;
        }

        int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum1 += digit * weights[i];
            sum2 += digit * (weights[i] + 1);
        }

        int mod1 = sum1 % 11;
        int mod2 = sum2 % 11;

        if (mod1 < 2) {
            if (cpf.charAt(9) != '0') {
                return false;
            }
        } else if (cpf.charAt(9) - '0' != 11 - mod1) {
            return false;
        }

        if (mod2 < 2) {
            if (cpf.charAt(10) != '0') {
                return false;
            }
        } else if (cpf.charAt(10) - '0' != 11 - mod2) {
            return false;
        }

        return true;
    }
}
