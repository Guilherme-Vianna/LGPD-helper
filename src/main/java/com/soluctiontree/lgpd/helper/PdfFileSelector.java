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

        selectButton = new JButton("Select PDF Documents");
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
        fileChooser.setMultiSelectionEnabled(true); // Allow multiple file selection
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(".pdf") || file.isDirectory();
            }

            public String getDescription() {
                return "PDF Files (*.pdf)";
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.add(selectButton);

        String[] operationOptions = {"Redate CPF", "Redate Regex"};
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
            File[] selectedFiles = fileChooser.getSelectedFiles(); // Get selected files
            if (selectedFiles != null && selectedFiles.length > 0) {
                // Get the selected operation
                String selectedOperation = (String) operationComboBox.getSelectedItem();

                for (File selectedFile : selectedFiles) {
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected PDF File: " + filePath);

                    if ("Redate CPF".equals(selectedOperation)) {
                        PDF.RedateCPF(filePath, filePath + "_redate.pdf"); // Append "_redate" before the extension
                    } else if ("Redate Regex".equals(selectedOperation)) {
                        // Get the Regex input from the text field
                        String regex = regexTextField.getText();
                        PDF.RedateRegex(filePath, filePath + "_redate.pdf", regex); // Append "_redate" before the extension
                    }
                }

                // Show a message when the operations are complete
                JOptionPane.showMessageDialog(this, "Operations completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PdfFileSelector frame = new PdfFileSelector();
            frame.setVisible(true);
        });
    }
}
