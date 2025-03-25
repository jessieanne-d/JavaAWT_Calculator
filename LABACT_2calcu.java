/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labact_2;

/**
 *
 * @author frances claire
 */
import java.awt.*;
import java.awt.event.*;
public class LABACT_2calcu {
    
    private Frame frame;
    private TextField textField;
    private String currentInput = "";
    private double firstNum = 0;
    private String operator = "";

    public LABACT_2calcu() {
        frame = new Frame("Calculator");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(222, 194, 203));
        frame.setForeground(Color.WHITE);

        textField = new TextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setBackground(new Color(249, 202, 201));
        frame.add(textField, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(240, 202, 203));
        
        String[] buttons = 
                            {"C", "^", ".", "+", 
                            "1", "2", "3", "-", 
                            "4", "5", "6", "*", 
                            "7", "8", "9", "/", 
                            "%", "0", "="};

        for (String b : buttons) {
            Button button = new Button(b);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setForeground(Color.BLACK);
            button.setBackground(new Color (240,202,203));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
        
        switch (command) {
            case "=" -> calculate();
            case "+", "-", "*", "/", "^" -> {
                if (!currentInput.isEmpty()) {
                    firstNum = Double.parseDouble(currentInput);
                    operator = command;
                    currentInput = "";
                    textField.setText("");
                }
            }
            case "C" -> {
                currentInput = "";
                firstNum = 0;
                operator = "";
                textField.setText("");
            }
            case "%" -> {
                if (!currentInput.isEmpty()) {
                    currentInput = String.valueOf(Double.parseDouble(currentInput) / 100);
                    textField.setText(currentInput);
                }
            }
            default -> {
                currentInput += command;
                textField.setText(currentInput);
            }
        }
    }

    private void calculate() {
        if (currentInput.isEmpty()) return;
        double secondNum = Double.parseDouble(currentInput);
        double result = switch (operator) {
            case "+" -> firstNum + secondNum;
            case "-" -> firstNum - secondNum;
            case "*" -> firstNum * secondNum;
            case "/" -> secondNum != 0 ? firstNum / secondNum : Double.NaN;
            case "^" -> Math.pow(firstNum, secondNum);
            default -> 0;
        };
        textField.setText(Double.isNaN(result) ? "Error" : String.valueOf(result));
        currentInput = Double.isNaN(result) ? "" : String.valueOf(result);
    }

    public static void main(String[] args) {
        new LABACT_2calcu();
    }
}
}
    



