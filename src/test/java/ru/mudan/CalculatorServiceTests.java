package ru.mudan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mudan.services.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorServiceTests {
    @Autowired
    private CalculatorService calculatorService;
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs10ResultIs20134(){
        assertEquals(20_134,calculatorService.calculateVacationPay(60_000,10));
    }
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs28ResultIs56375(){
        assertEquals(56_375,calculatorService.calculateVacationPay(60_000,28));
    }
    @Test
    public void whenAverageSalaryIs100_000AndAmountVacationDaysIs28ResultIs93959(){
        assertEquals(93_959,calculatorService.calculateVacationPay(100_000,28));
    }
    @Test
    public void whenAverageSalaryIs1_000_000AndAmountVacationDaysIs15ResultIs503355(){
        assertEquals(503_355,calculatorService.calculateVacationPay(1_000_000,15));
    }
    @Test
    public void whenAverageSalaryIs12_000AndAmountVacationDaysIs12ResultIs4832(){
        assertEquals(4_832,calculatorService.calculateVacationPay(12_000,12));
    }
    @Test
    public void whenAverageSalaryIs18_000AndAmountVacationDaysIs10ResultIs6040(){
        assertEquals(6_040,calculatorService.calculateVacationPay(18_000,10));
    }
}
