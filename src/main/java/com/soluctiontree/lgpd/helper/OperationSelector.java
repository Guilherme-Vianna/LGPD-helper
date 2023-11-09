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
        painel.setBounds(150, 0, 500, 100);
        
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
                        progressBar.setValue(0);
                        progressBar.setMaximum(selectedFiles.length);
                        
                        for (int i = 0; i < selectedFiles.length; i++) {
                            PDF.RedateCPFAndRGDocx(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        progressBar.setValue(selectedFiles.length);
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
                        progressBar.setValue(0);
                        progressBar.setMaximum(selectedFiles.length);

                        for (int i = 0; i < selectedFiles.length; i++) {
                            PDF.RedateCPFAndRG(selectedFiles[i].getAbsolutePath());
                            progressBar.setValue(i);
                        }
                        
                        progressBar.setValue(selectedFiles.length);
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
