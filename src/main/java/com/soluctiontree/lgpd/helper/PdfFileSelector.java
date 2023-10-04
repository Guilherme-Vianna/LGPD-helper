package com.soluctiontree.lgpd.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfFileSelector extends JFrame {
    private JButton selectButton;
    private JFileChooser fileChooser;
    private JComboBox<String> operationComboBox;
    private JTextField regexTextField; // Text field for Regex input

    public PdfFileSelector() {
        setTitle("LGPD Helper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250); // Increased height to accommodate the text field

        selectButton = new JButton("Select PDF and DOCX Documents"); // Update the button text
        selectButton.setFont(new Font("Arial", Font.PLAIN, 16));
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
        fileChooser.setMultiSelectionEnabled(true);

        // Update the file filter to accept both PDF and DOCX files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                String fileName = file.getName().toLowerCase();
                return fileName.endsWith(".pdf") || fileName.endsWith(".docx") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") ||fileName.endsWith(".jpg") ||file.isDirectory();
            }

            public String getDescription() {
                return "PDF and DOCX Files (*.pdf, *.docx)";
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.add(selectButton);

        String[] operationOptions = {"Redate CPF", "Redate Regex", "PDF To Image", "OCR Image"};
        operationComboBox = new JComboBox<>(operationOptions);
        operationComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(operationComboBox);

        // Add a label and text field for Regex input
        JLabel regexLabel = new JLabel("Regular Expression:");
        regexLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(regexLabel);

        regexTextField = new JTextField(20);
        regexTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(regexTextField);

        add(panel, BorderLayout.CENTER);

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    private void showFileChooser() throws IOException {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            if (selectedFiles != null && selectedFiles.length > 0) {
                String selectedOperation = (String) operationComboBox.getSelectedItem();
                
                if ("OCR Image".equals(selectedOperation)) {
                    PDF.OCRImage(selectedFiles);
                }
                
                for (File selectedFile : selectedFiles) {
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected File: " + filePath);

                    String fileExtension = getFileExtension(filePath);
                    
                    if ("Redate CPF".equals(selectedOperation)) {
                        if ("pdf".equalsIgnoreCase(fileExtension)) {
                            PDF.RedateCPF(filePath, filePath + "_redate.pdf");
                        } else if ("docx".equalsIgnoreCase(fileExtension)) {
                            PDF.RedateCPFDocx(filePath); 
                        }
                    } else if ("Redate Regex".equals(selectedOperation)) {
                        String regex = regexTextField.getText();
                        if ("pdf".equalsIgnoreCase(fileExtension)) {
                            PDF.RedateRegex(filePath, filePath + "_redate.pdf", regex);
                        } else if ("docx".equalsIgnoreCase(fileExtension)) {
                        }
                    } else if ("PDF To Image".equals(selectedOperation)) { 
                        PDF.PDFToImage(filePath);
                    }
                }

                JOptionPane.showMessageDialog(this, "Operations completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return filePath.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PdfFileSelector frame = new PdfFileSelector();
            frame.setVisible(true);
        });
    }
}
