package com.ScottHorsburgh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Assessment_E extends JFrame{
    /* Class instance Variable */
    private JPanel rootPanel3;
    private JPanel mainPanel3;
    private JLabel numberToFindOddNumsLbl;
    private JLabel exampleForInputLbl;
    private JTextField numToFindSumOfOddsTF;
    private JTextField oddNumsFromNumberTF;
    private JTextField sumOfOddNumDigitsTF;
    private JLabel oddNumsFromNumberLbl;
    private JLabel sumOfOddDigitsLbl;
    private JLabel enterANumberLbl;
    private JButton closeBtn;
    private JButton calculateBtn;
    private JButton clearBtn;
    private JButton previousBtn;
    private int numberInput;
    private String numbersAsString;
    private int sum;
    private int counter;

    /**
     * Default Assessment_E Constructor.
     */
    public Assessment_E() {
        /*Initialize default values placed in this body used in multiple places
          Value for setCalculateBtn method and clear button reset*/
        numbersAsString = "";
        //Value for clear button reset and setSumOfOddNumbers method
        sum = 0;
        //Check if calculate and clear buttons clicked more then once
        counter = 0;
        //Method to create and start Assessment_E GUI
        createAssessmentGui();
        //Method if enter key is pressed to uses CalculateBtn Action Listener
        enterKeyListener();

        /* <<< All button Action Listeners below >>> */
        calculateBtn.addActionListener(e -> { setCalculateBtn(); });
        clearBtn.addActionListener(e -> { setClearBtn(); });
        closeBtn.addActionListener(e -> dispose());
        previousBtn.addActionListener(e -> { setPreviousBtn(); });
    }

    /**
     * Method that creates and starts Assessment_E frame.
     */
    private void createAssessmentGui() {
        add(mainPanel3);
        setTitle("Assessment_E");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        rootPane.setDefaultButton(calculateBtn);
        setVisible(true);
        styles();
    }

    /**
     * Method for calculate button, checks if button clicked more then once and
     * sees if text fields are empty or not a number, sends exception message to user.
     */
    private void setCalculateBtn(){
        try {
            numberInput = Integer.parseInt(numToFindSumOfOddsTF.getText());
            //Counter for button clicks and checks if clicked more then once
            counter++;
            if (counter >= 1){
                calculateBtn.setEnabled(false);
            }
        } catch (NumberFormatException nfe) {
            //Method sets all text fields empty
            setFieldsToEmptyStrings();
            JOptionPane.showMessageDialog(null, "Enter Values In TextField", "Invalid TextFields", JOptionPane.ERROR_MESSAGE);
            calculateBtn.setEnabled(true);
            //Method sets all fields visible to false
            setFieldsVisibilityFalse();
            return;
        }
        //Takes user inputs and converts integers to string to separate numbers
        numbersAsString = String.valueOf(numberInput);
        setSumOfOddNumbers(numbersAsString);
        //Method sets all fields visible to true to show answers clicking calculateBtn
        setFieldsVisibilityTrue();
    }

    /**
     * Method for clear button that sets all fields empty and
     * resets calculate button enable to true.
     */
    private void setClearBtn(){
        setFieldsVisibilityFalse();
        setFieldsToEmptyStrings();
        numbersAsString = "";
        sum = 0;
        counter++;
        if (counter >=1 ){
            calculateBtn.setEnabled(true);
        }
    }

    /**
     * Method for previous button that closes this frame and
     * starts new Assessment_D previous frame.
     */
    private void setPreviousBtn(){
        new Assessment_D();
        dispose();
    }

    /**
     * Method for enter key to see if it is clicked and
     * if so then calculate button is invoked.
     */
    private void enterKeyListener(){
        calculateBtn.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    setCalculateBtn();
                }
            }
        });
    }

    /**
     * Method that sets all fields visible to true.
     */
    private void setFieldsVisibilityTrue(){
        oddNumsFromNumberLbl.setVisible(true);
        oddNumsFromNumberTF.setVisible(true);
        sumOfOddDigitsLbl.setVisible(true);
        sumOfOddNumDigitsTF.setVisible(true);
    }

    /**
     * Method that sets all fields visible to false.
     */
    private void setFieldsVisibilityFalse(){
        oddNumsFromNumberLbl.setVisible(false);
        oddNumsFromNumberTF.setVisible(false);
        sumOfOddDigitsLbl.setVisible(false);
        sumOfOddNumDigitsTF.setVisible(false);
    }

    /**
     * Method that sets all fields to empty fields clearing previous input from user.
     */
    private void setFieldsToEmptyStrings(){
        numToFindSumOfOddsTF.setText("");
        oddNumsFromNumberTF.setText("");
        sumOfOddNumDigitsTF.setText("");
    }

    /**
     * Method that stores input number into a list, counts the length, gets values
     * and if it is an odd number using % stores values into list then prints out
     * all odd numbers to text area and gets sum of all odd numbers.
     * @param numbers input taking in as a string to break up numbers
     *                to separate even and odd numbers
     */
    private void setSumOfOddNumbers(String numbers){
        //Initial default value to store each value
        int numberCount = 0;
        oddNumsFromNumberLbl.setText("Odd numbers in " + numbers + " are: ");
        List<Integer> odds = new ArrayList<>();
        //Loops through numbers and stores values that are odd in odds LIST
        for (int count = 0; count < numbers.length(); count++){
            numberCount = Character.getNumericValue(numbers.charAt(count));
            if (numberCount % 2 != 0){
                odds.add(numberCount);
                sum += numberCount;
            }
        }
        //Takes all numbers from LIST and stores in formattedNumbers variable as a string with " , " joined between
        //Example of print out to the text area =>  3 , 5 , 9 , 11
        String formattedOddNumbers = odds.stream().map(Object::toString).collect(Collectors.joining(" , "));
        oddNumsFromNumberTF.setText("" + formattedOddNumbers);
        sumOfOddNumDigitsTF.setText("" + sum);
    }

    /**
     * Method that styles all buttons, panel, frame, text fields and text area.
     */
    private void styles(){
        /*=============  Variables for backgrounds, foregrounds Color ================*/
        Color btnColor = new java.awt.Color(220, 220, 220);
        Color btnColorText = new java.awt.Color(85,85,85);
        /*=============  Style settings for button background  ================*/
        closeBtn.setBackground(btnColor);
        clearBtn.setBackground(btnColor);
        previousBtn.setBackground(btnColor);
        calculateBtn.setBackground(btnColor);
        /*=============  Style settings for button foreground  ================*/
        closeBtn.setForeground(btnColorText);
        clearBtn.setForeground(btnColorText);
        previousBtn.setForeground(btnColorText);
        calculateBtn.setForeground(btnColorText);
        /*============== Style settings for text in buttons ==================*/
        //Adds underline to initial letter of button
        calculateBtn.setMnemonic(KeyEvent.VK_C);
        clearBtn.setMnemonic(KeyEvent.VK_C);
        previousBtn.setMnemonic(KeyEvent.VK_C);
        closeBtn.setMnemonic(KeyEvent.VK_C);
    }

}
