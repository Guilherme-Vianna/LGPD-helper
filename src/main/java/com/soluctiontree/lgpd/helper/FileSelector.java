/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author User
 */
public class FileSelector extends JFileChooser{
    FileFilter filter;
    
    FileSelector(String prefixFile){
        this.setMultiSelectionEnabled(true);
        
        if(prefixFile == ".pdf") { 
            filter = new FileFilter() { 
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(prefixFile);
                }

                @Override
                public String getDescription() {
                    return "PDF Files";
                }   
            };
        }
        else if (prefixFile == ".docx") {
            filter = new FileFilter() { 
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(prefixFile);
                }

                @Override
                public String getDescription() {
                    return "DOCX Files";
                }   
            };
        }
        else if (prefixFile == ".img") {
            filter = new FileFilter() { 
                @Override
                public boolean accept(File file) {
                    return file.getName().toLowerCase().endsWith(".jpeg")||
                           file.getName().toLowerCase().endsWith(".jpg") ||
                           file.getName().toLowerCase().endsWith(".png");
                }

                @Override
                public String getDescription() {
                    return "Images Files";
                }   
            };
        }
        
        this.setFileFilter(filter);
    }
}
