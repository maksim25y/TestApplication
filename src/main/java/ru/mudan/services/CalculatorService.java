package ru.mudan.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public long calculateVacationPay(long averageSalary,int amountVacationDays){
        return (long) ((averageSalary/29.3)*amountVacationDays);
    }
}
