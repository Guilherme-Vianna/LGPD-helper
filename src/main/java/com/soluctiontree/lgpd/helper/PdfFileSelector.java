/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author matav
 */
public class PdfFileSelector extends JFrame {
    private JButton selectButton;
    private JFileChooser fileChooser;

    public PdfFileSelector() {
        setTitle("PDF File Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);

        selectButton = new JButton("Select PDF File");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showFileChooser();
                } catch (IOException ex) {
                    Logger.getLogger(PdfFileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(".pdf") || file.isDirectory();
            }

            public String getDescription() {
                return "PDF Files (*.pdf)";
            }
        });

        JPanel panel = new JPanel();
        panel.add(selectButton);

        add(panel, BorderLayout.CENTER);
    }

    private void showFileChooser() throws IOException {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                String filePath = selectedFile.getAbsolutePath();
                System.out.println("Selected PDF File: " + filePath);
                PDF.DetectCpf();
            }
        }
    }
}
