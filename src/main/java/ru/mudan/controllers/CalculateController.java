package ru.mudan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mudan.response.VacationPayResponse;
import ru.mudan.services.CalculatorService;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    private final CalculatorService calculatorService;
    @Autowired
    public CalculateController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public ResponseEntity<VacationPayResponse>getVacationPay(@RequestParam Integer averageSalary,
                                                @RequestParam Integer amountVacationDays){
        return new ResponseEntity<>(new VacationPayResponse(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)), HttpStatus.OK);
    }
}
