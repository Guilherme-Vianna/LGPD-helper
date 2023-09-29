/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.soluctiontree.lgpd.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.SwingUtilities;
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
    }
}
