package ru.mudan.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int calculateVacationPay(int averageSalary,int amountVacationDays){
        return (int) ((averageSalary/29.8)*amountVacationDays);
    }
}
