package com.soluctiontree.lgpd.helper;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*; 

public class LgpdHelper {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OperationSelector operationSelector = new OperationSelector();
                operationSelector.setVisible(true);
            }
        });
    }
}
