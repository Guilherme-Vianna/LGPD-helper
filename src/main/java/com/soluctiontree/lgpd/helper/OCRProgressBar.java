/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.soluctiontree.lgpd.helper;

/**
 *
 * @author User
 */
public class OCRProgressBar {
    private int totalSteps;
    private int currentStep;

    public OCRProgressBar(int totalSteps) {
        this.totalSteps = totalSteps;
        this.currentStep = 0;
    }

    public void increment() {
        currentStep++;
        int percentComplete = (int) ((double) currentStep / totalSteps * 100);
        System.out.print("\rProgress: [" + repeat("#", percentComplete / 2) + repeat(" ", 50 - percentComplete / 2) + "] " + percentComplete + "%");
        if (currentStep == totalSteps) {
            System.out.println(); // Move to a new line when finished
        }
    }

    private String repeat(String str, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }
}
