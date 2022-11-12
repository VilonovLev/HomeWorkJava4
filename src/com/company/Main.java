package com.company;

import java.util.*;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        // "Перевернутый" список (Задача 1)
        Logger logger = Logger.getAnonymousLogger();
        List<Integer> integerList = new LinkedList<>(Arrays.asList(23, 23, 44, 15, 344));
        logger.info(reversList(integerList).toString());

        // Заполнение очереди (Задача 2.1)
        SimpleQueue queue = new SimpleQueue();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        logger.info(queue.toString());

        // Извлечение первого (Задача 2.2)
        logger.info(String.valueOf(queue.dequeue()));
        logger.info(queue.toString());

        // Просмотр первого элемента (Задача 2.3)
        logger.info(String.valueOf(queue.first()));
        logger.info(queue.toString());

        // (Задача 3)
        Calculator сalculator = new Calculator();

        // (Зфдача 4)
        logger.info(getPostfixForm("(23+y)/(0 * (34-5)^z)"));
    }

    // Задача 1
    public static List<Integer> reversList(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }

    // Получаем постфиксную форму выражения (Задача 4)
    public static String getPostfixForm(String string) {
        string = string.replaceAll("\\s*","");
        Stack<Character> stack = new Stack();
        StringBuilder postfixExpression = new StringBuilder();
        char temp = Character.MIN_VALUE;

        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (Character.isDigit(currentChar) || Character.isLetter(currentChar)) {
                if (i < string.length() - 1 && Character.isDigit(string.charAt(i + 1)))
                    postfixExpression.append(currentChar);
                else postfixExpression.append(currentChar).append(" ");
            } else if (currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == ')') {
                while (stack.peek() != '(') {
                    postfixExpression.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else {
                while (!stack.empty() && !(stack.peek() == ('('))) {
                    int priorityCurrentChar = getPriority(currentChar);
                    temp = stack.pop();
                    if (getPriority(temp) >= priorityCurrentChar) {
                        postfixExpression.append(temp).append(" ");
                        temp = Character.MIN_VALUE;
                    }
                }
                if (!(temp == Character.MIN_VALUE))
                    stack.push(temp);
                stack.push(currentChar);
            }
        }
        while (!stack.empty()) {
            postfixExpression.append(stack.pop()).append(" ");
        }
        return postfixExpression.toString();
    }

    // Получаем приоритет оператора (Задача 4)
    public static int getPriority(char simbol) {
        final Map<Character, Integer> map = Map.of(
                '(', 0,
                '+', 1,
                '-', 1,
                '*', 2,
                '/', 2,
                '^', 3);
        return map.get(simbol);
    }

}

