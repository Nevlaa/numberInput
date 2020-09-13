package com.ScottHorsburgh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Assessment_A_B_C {
    /* Class instance Variable */
    private JPanel rootPanel;
    private JPanel mainPanel;
    private JLabel sumOfEvenNumbersLbl;
    private JLabel sumOfSquaredLbl;
    private JLabel powerOfTwoLbl;
    private JTextField evenNumbersTF;
    private JTextField powerOfTwoTF;
    private JTextField firstNumberTF;
    private JTextField secondNumberTF;
    private JTextField sumOfSquaredTF;
    private JButton nextBtn;
    private JTextArea sumSquaredTxt;
    private JTextArea powerOfTxt;
    private JTextArea sumEvenTxt;
    private JButton clearBtn;
    private int sumEven;
    private int sumSquared;
    private double powerOfNumber;

    //Initializing object for JFrame Class
    JFrame frame = new JFrame("Assessment A_B_C");

    /**
     * Default Assessment_A_B_C constructor.
     */
    public Assessment_A_B_C() {
        this.sumEven = 0;
        this.sumSquared = 0;
        this.powerOfNumber = 0;
        //Method creates and starts Assessment_A_B_C GUI
        createAssessmentGui();
        //Method outputs vales to text fields
        outputFirstThreeTextFields();

        /* <<< All buttons Action Listeners below >>> */
        nextBtn.addActionListener(e -> { nextButtonActionListener(); });
    }

    /**
     * Method to create Assessment_A_B_C GUI.
     */
    private void createAssessmentGui() {
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        createFormStyling();
    }

    /**
     * Method sets text in text fields and outputs values.
     */
    private void outputFirstThreeTextFields() {
        setSumOfEvenNumbers();
        setSumSquaredNumbers();
        setPowerOfNumbers();
    }

    /**
     * Method for the next button when it is clicked and
     * closes this frame and opens up Assessment_D frame.
     */
    private void nextButtonActionListener(){
        new Assessment_D();
        frame.dispose();
    }

    /**
     * method that finds the sum of all even numbers between 2 and 100.
     */
    private void setSumOfEvenNumbers(){
        List<Integer> odds = new ArrayList<>();
        for (int i = 2; i <= 100; i += 2){
            odds.add(i);
            sumEven += i;
        }
        String formattedOddNumbers = odds.stream().map(Object::toString).collect(Collectors.joining(" , "));
        sumEvenTxt.append("" + formattedOddNumbers);
        evenNumbersTF.setText("" + sumEven);
    }

    /**
     * Method that finds all powers of 2 from 20 up to 220.
     * output for power of number.
     */
    private void setPowerOfNumbers(){
        List<Double> odds = new ArrayList<>();
        for(double i = 2; i <= 20; i++){
            odds.add(i);
            powerOfNumber = Math.pow(i,2);
        }
        String formattedOddNumbers = odds.stream().map(Object::toString).collect(Collectors.joining(" , "));
        powerOfTxt.append("" + formattedOddNumbers);
        powerOfTwoTF.setText("  " + powerOfNumber);
    }

    /**
     * Method that finds the sum of all squares between 1 and 100.
     */
    private void setSumSquaredNumbers(){
        List<Integer> odds = new ArrayList<>();
        for (int i = 1; i <= 100; i *= 2){
            odds.add(i);
            sumSquared += i;
        }
        String formattedOddNumbers = odds.stream().map(Object::toString).collect(Collectors.joining(" , "));
        sumSquaredTxt.append("" + formattedOddNumbers);
        sumOfSquaredTF.setText("  " + sumSquared);
    }

    /**
     * Method creates styles for Assessment_A_B_C frame and
     * backgrounds for frame, panel, buttons and text fields.
     */
    private void createFormStyling() {
        /*=============  Backgrounds for frame, panel and buttons ================*/
        Color panelColor = new java.awt.Color(220, 220, 220);
        Color btnColor = new java.awt.Color(210, 210, 210);
        Color btnColorText = new java.awt.Color(85,85,85);
        Color textFieldColor = new java.awt.Color(230,230,230);
        /*=============== Background for main panel =================*/
        mainPanel.setBackground(panelColor);
        /*============ Style settings for button background ============*/
        nextBtn.setBackground(btnColor);
        nextBtn.setForeground(btnColorText);
        /*================ Style for TextFields / TextArea background ===========*/
        sumOfSquaredTF.setBackground(textFieldColor);
        evenNumbersTF.setBackground(textFieldColor);
        powerOfTwoTF.setBackground(textFieldColor);
        /*============== Style settings for text in buttons ==================*/
        //Adds underline to initial letter on each button
        nextBtn.setMnemonic(KeyEvent.VK_C);
    }
}
