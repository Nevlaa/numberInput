package com.ScottHorsburgh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Assessment_D extends JFrame{
    /* Class instance Variable */
    private JPanel rootPanel2;
    private JPanel mainPanel2;
    private JTextField sumOfOddNumbersTF;
    private JLabel oddNumbersBetweenTwoNumbersLbl;
    private JLabel firstNumberLbl;
    private JLabel oddNumbersBetweenNumbersLbl;
    private JTextArea printOddNumbersTxt;
    private JLabel sumOddNumsBetweenNumbersLbl;
    private JButton calculateBtn;
    private JButton clearBtn;
    private JTextField firstNumberTF;
    private JTextField secondNumberTF;
    private JLabel secondNumberLbl;
    private JButton nextBtn;
    private JButton previousBtn;
    private int counter;

    /**
     * Default Assessment_D Constructor.
     */
    public Assessment_D() {
        //Method to create and start Assessment_D frame
        createAssessmentGui();
        //Initial counter value to check if calculate and clear buttons clicked more then once
        counter = 0;
        //Checks if the Enter key has been pressed and if so calculate button is used
        enterKeyStroke();

        //<<< Action listeners for all buttons in this frame >>>
        calculateBtn.addActionListener(e -> { setCalculateBtn(); });
        clearBtn.addActionListener(e -> { setClearBtn(); });
        nextBtn.addActionListener(e -> { setNextBtn(); });
        previousBtn.addActionListener(e -> { setPreviousBtn(); });
    }

    /**
     * Method when calculate button has been clicked and
     * takes input from user, if button is clicked more then once it sets enable to false, sets fields visible false
     * Checks if text field is empty of not a number/ if so shows exception for user.
     */
    private void setCalculateBtn(){
        try{
            int num1 = Integer.parseInt(firstNumberTF.getText());
            int num2 = Integer.parseInt(secondNumberTF.getText());
            setOddNumbersBetweenTwoNumbers(num1, num2);
            //Counter for button clicks and checks if clicked more then once
            counter++;
            if(counter >= 1){
                calculateBtn.setEnabled(false);
            }
        }catch (NumberFormatException nfe){
            firstNumberTF.setText("");
            secondNumberTF.setText("");
            //Method to set all fields visible to false
            setFieldsFalseVisibility();
            JOptionPane.showMessageDialog( null,
                    "Enter Values In TextField", "Invalid TextFields", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Method to set all fields visible to true
        setFieldsTrueVisibility();
    }

    /**
     * Method that watches for enter keystroke and if pressed it uses calculate button.
     */
    private void enterKeyStroke(){
        calculateBtn.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    setCalculateBtn();
                }
            }
        });
    }

    /**
     * Method for the clear button that sets all text fields empty
     * If clear button clicked it sets calculate button enable to true.
     */
    private void setClearBtn(){
        setFieldsFalseVisibility();
        setFieldsToEmptyStrings();
        counter++;
        if(counter >= 1){
            calculateBtn.setEnabled(true);
        }
    }

    /**
     * Method for the previous button that closes this frame
     * opens up previous Assessment_A_B_C frame.
     */
    private void setPreviousBtn(){
        new Assessment_A_B_C();
        dispose();
    }

    /**
     * Method for the next button that close this frame and
     * opens up Assessment_E frame that follows.
     */
    private void setNextBtn(){
        new Assessment_E();
        dispose();
    }

    /**
     * Method creates Assessment_D GUI.
     */
    private void createAssessmentGui() {
        add(mainPanel2);
        setTitle("Assessment_D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        rootPane.setDefaultButton(calculateBtn);
        styles();
    }

    /**
     * Method sets text fields and text area to empty strings.
     */
    private void setFieldsToEmptyStrings(){
        firstNumberTF.setText("");
        secondNumberTF.setText("");
        printOddNumbersTxt.setText("");
        sumOfOddNumbersTF.setText("");
    }

    /**
     * Method sets all labels, text fields and text area visible to true.
     */
    private void setFieldsTrueVisibility(){
        printOddNumbersTxt.setVisible(true);
        oddNumbersBetweenNumbersLbl.setVisible(true);
        sumOddNumsBetweenNumbersLbl.setVisible(true);
        sumOfOddNumbersTF.setVisible(true);
    }

    /**
     * Method sets all labels, text fields and text area visible to false.
     */
    private void setFieldsFalseVisibility(){
        printOddNumbersTxt.setVisible(false);
        oddNumbersBetweenNumbersLbl.setVisible(false);
        sumOddNumsBetweenNumbersLbl.setVisible(false);
        sumOfOddNumbersTF.setVisible(false);
    }

    /**
     * Method takes two numbers from user, stores in List and finds all odd numbers between the two numbers
     * and outputs all odd numbers stored from list to text area.
     * @param num1 first number from input ( firstNumberTF ) text field.
     * @param num2 second number from input ( secondNumberTF) text field.
     */
    private void setOddNumbersBetweenTwoNumbers(int num1, int num2){
        int sumOdd = 0;
        int i;
        List<Integer> odds = new ArrayList<>();
        for (i = Math.min(num1, num2); i <= Math.max(num1, num2); i++){
            if(i % 2 != 0){
                odds.add(i);
                sumOdd += i;
            }
        }
        //Takes all numbers from LIST and stores in formattedNumbers variable as a string with " , " joined between
        //Example of print out to the text area =>  3 , 5 , 9 , 11
        String formattedOddNumbers = odds.stream().map(Object::toString).collect(Collectors.joining(" , "));
        printOddNumbersTxt.append(formattedOddNumbers);
        oddNumbersBetweenNumbersLbl.setText("Odd numbers between " + num1 + " and " + num2 + " : ");
        sumOddNumsBetweenNumbersLbl.setText("Sum of odd numbers between "  + num1 + " and " + num2 + " : ");
        sumOfOddNumbersTF.setText("" + sumOdd);
    }

    /**
     * Method that styles all buttons, panel, frame, text fields and text area.
     */
    private void styles(){
        /*=============  Background colors for frame, panel, buttons, text fields and text area ================*/
        Color panelColor = new java.awt.Color(220, 220, 220);
        Color btnColor = new java.awt.Color(210, 210, 210);
        Color textFieldColor = new java.awt.Color(230,230,230);
        Color btnColorText = new java.awt.Color(85,85,85);
        /*=============== Background for main panel =================*/
        mainPanel2.setBackground(panelColor);
        /*============== Style settings for text in buttons ==================*/
        //Adds underline to initial letter on each button
        calculateBtn.setMnemonic(KeyEvent.VK_C);
        clearBtn.setMnemonic(KeyEvent.VK_C);
        previousBtn.setMnemonic(KeyEvent.VK_C);
        nextBtn.setMnemonic(KeyEvent.VK_C);
        /*============ Style settings for button background ============*/
        nextBtn.setBackground(btnColor);
        clearBtn.setBackground(btnColor);
        previousBtn.setBackground(btnColor);
        calculateBtn.setBackground(btnColor);
        /*============ Style settings for button foreground ============*/
        nextBtn.setForeground(btnColorText);
        clearBtn.setForeground(btnColorText);
        previousBtn.setForeground(btnColorText);
        calculateBtn.setForeground(btnColorText);
        /*================ Style for TextFields / TextArea background ===========*/
        firstNumberTF.setBackground(textFieldColor);
        secondNumberTF.setBackground(textFieldColor);
        printOddNumbersTxt.setBackground(textFieldColor);
        sumOfOddNumbersTF.setBackground(textFieldColor);
    }
}
