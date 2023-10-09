package com.soluctiontree.lgpd.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationSelector extends JFrame {
    private final JButton rDocx = new JButton("Censurar DOCX");
    private final JButton rPDF = new JButton("Censurar PDF");
    private final JButton oImg = new JButton("PDF para Imagem");
    private final JButton rRegex = new JButton("Censurar Regex");
    private final JButton pdfOcr = new JButton("OCR Imagem");
    private Container container = null;
    private JPanel painel = null;
    private JProgressBar progressBar;

    
    public OperationSelector() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Escolha a sua Operação");
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        container = getContentPane();
        container.setLayout(null);

        painel = new JPanel();
        painel.add(rDocx);
        painel.add(rPDF);
        painel.add(oImg);
        painel.add(rRegex);
        painel.add(pdfOcr);
        painel.setBounds(150, 0, 500, 500);
        
        container.add(painel);
        
        progressBar = new JProgressBar(0, 100); 
        progressBar.setVisible(true);
        progressBar.setBounds(150, 400, 500, 20); 
        container.add(progressBar);
        
        rDocx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Openning File Selector");
                    
                    FileSelector fileSelector = new FileSelector(".docx");
                    int returnValue = fileSelector.showOpenDialog(null);

                    if (returnValue == FileSelector.APPROVE_OPTION) {
                        File[] selectedFiles = fileSelector.getSelectedFiles();
                        JOptionPane.showMessageDialog(null, "Operacao iniciada! Aguarde a mensagem de termino");
                        
                        progressBar.setMaximum(selectedFiles.length);
                        
                        for (int i = 0; i <= selectedFiles.length; i++) {
                            PDF.RedateCPF(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        JOptionPane.showMessageDialog(null, "Operacao concluida!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OperationSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        rPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Openning File Selector");
                    
                    FileSelector fileSelector = new FileSelector(".pdf");
                    int returnValue = fileSelector.showOpenDialog(null);

                    if (returnValue == FileSelector.APPROVE_OPTION) {
                        File[] selectedFiles = fileSelector.getSelectedFiles();
                        JOptionPane.showMessageDialog(null, "Operacao iniciada! Aguarde a mensagem de termino");
                        
                        progressBar.setMaximum(selectedFiles.length);

                        for (int i = 0; i <= selectedFiles.length; i++) {
                            PDF.RedateCPF(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        JOptionPane.showMessageDialog(null, "Operacao concluida!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OperationSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        oImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Openning File Selector");
                    FileSelector fileSelector = new FileSelector(".pdf");
                    int returnValue = fileSelector.showOpenDialog(null);

                    if (returnValue == FileSelector.APPROVE_OPTION) {
                        File[] selectedFiles = fileSelector.getSelectedFiles();
                        JOptionPane.showMessageDialog(null, "Operacao iniciada! Aguarde a mensagem de termino");
                        
                        progressBar.setMaximum(selectedFiles.length);
                        
                        for (int i = 0; i <= selectedFiles.length; i++) {
                            PDF.PDFToImage(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        JOptionPane.showMessageDialog(null, "Operacao concluida!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OperationSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        rRegex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Openning File Selector");
                    FileSelector fileSelector = new FileSelector(".docx");
                    int returnValue = fileSelector.showOpenDialog(null);

                    if (returnValue == FileSelector.APPROVE_OPTION) {
                        File[] selectedFiles = fileSelector.getSelectedFiles();
                        JOptionPane.showMessageDialog(null, "Operacao iniciada! Aguarde a mensagem de termino");

                        progressBar.setMaximum(selectedFiles.length);
                        
                        for (int i = 0; i <= selectedFiles.length; i++) {
                            PDF.RedateCPF(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        JOptionPane.showMessageDialog(null, "Operacao concluida!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OperationSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        pdfOcr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Openning File Selector");
                    FileSelector fileSelector = new FileSelector(".img");
                    int returnValue = fileSelector.showOpenDialog(null);

                    if (returnValue == FileSelector.APPROVE_OPTION) {
                        File[] selectedFiles = fileSelector.getSelectedFiles();
                        JOptionPane.showMessageDialog(null, "Operacao iniciada! Pode demorar um pouco!");
                        PDF.OCRImage(selectedFiles, selectedFiles[0].getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "Operacao concluida!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OperationSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperationSelector frame = new OperationSelector();
            frame.setVisible(true);
        });
    }
}