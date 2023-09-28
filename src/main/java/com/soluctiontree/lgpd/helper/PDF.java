/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
