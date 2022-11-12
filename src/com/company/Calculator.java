package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator extends JFrame {
    Stack<Integer> resultStack = new Stack<>();
    List<JButton> jButtonList = new ArrayList<>();
    List<String> operations = Arrays.asList("1", "2", "3", "+", "4", "5", "6", "-",
            "7", "8", "9", "*", "return", "0", "=", "/");
    String messeges = "";
    String lastOperation = "+";
    String inputNumber = "";

    public Calculator() {
        resultStack.add(0);
        drawCalc();
    }

    private void drawCalc() {
        JFrame WindowCalc = new JFrame("Calculator");
        WindowCalc.setResizable(false);
        WindowCalc.setBounds(300, 200, 300, 300);
        WindowCalc.setLayout(new GridLayout(2, 0));

        JTextField jTextField = new JTextField(messeges, 16);
        jTextField.setEnabled(false);
        jTextField.setFont(new Font("Fira Code", Font.BOLD, 46));
        jTextField.setBackground(Color.GRAY);
        jTextField.setDisabledTextColor(Color.BLACK);
        jTextField.setSize(300, 50);
        jTextField.setVisible(true);
        WindowCalc.add(jTextField);

        for (int i = 0; i < operations.size(); i++) {
            String simbol = operations.get(i);
            JButton temp = new JButton(simbol);
            temp.setVisible(true);
            temp.addActionListener(e -> actionPerformed(e, jTextField));
            jButtonList.add(temp);
        }
        JPanel jPanel = new JPanel();
        jPanel.setSize(300, 150);
        jPanel.setVisible(true);
        jButtonList.forEach(jPanel::add);

        jPanel.setLayout(new GridLayout(4, 4));
        WindowCalc.add(jPanel);
        WindowCalc.setVisible(true);
    }

    private void Calculate() {
        int second = resultStack.pop();
        int first = resultStack.peek();
        Integer result = switch (lastOperation) {
            case "/" -> (first / second);
            case "*" -> (first * second);
            case "+" -> (first + second);
            case "-" -> (first - second);
            default -> 0;
        };
        messeges = result.toString();
        resultStack.add(result);
    }

    public void actionPerformed(ActionEvent event, JTextField jText) {
        String comand = event.getActionCommand();
        if (Character.isDigit(comand.charAt(0))) {
            String num = event.getActionCommand();
            inputNumber += num;
            messeges += num;
            jText.setText(messeges);
        } else if (comand.equals("return")) {
            resultStack.pop();
            messeges = resultStack.peek().toString();
            jText.setText(messeges);
        } else {
            resultStack.push(Integer.valueOf(inputNumber));
            Calculate();
            lastOperation = event.getActionCommand();
            if (!lastOperation.equals("=")) {
                messeges += lastOperation;
                inputNumber = "";
            } else {
                lastOperation = "+";
                inputNumber = "0";
            }
            jText.setText(messeges);
        }
    }
}
