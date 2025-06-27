package com.jkh.Example.controller;


import com.jkh.Example.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    @GetMapping("/calculator")
    public String showCal(){
        return "calculator";
    }

    // 계산 처리 메서드는 다음 페이지에서 구현합니다.
    @PostMapping("/calculator")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model
    ) {
        System.out.println("num1:" + num1 + " num2:" + num2);
        try {
            double result = calculatorService.calculate(num1, num2, operation);
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "calculator";
    }
}