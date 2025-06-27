package com.jkh.Example.service;


import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    // 사칙연산 메서드는 다음 페이지에서 구현합니다.
    public double calculate(double num1, double num2, String operation) {
        switch (operation) {
            case "add":
                return num1 + num2;
            case "subtract":
                return num1 - num2;
            case "multiply":
                return num1 * num2;
            case "divide":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}