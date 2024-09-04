package ru.mudan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mudan.dto.DateDTO;
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
    public ResponseEntity<Object>getVacationPay(@RequestParam Integer averageSalary,
                                                             @RequestParam Integer amountVacationDays,
                                                             @RequestBody(required = false)DateDTO dateDTO){
        if(amountVacationDays<=0||averageSalary<0)return new ResponseEntity<>("Некорректные данные для подсчёта",HttpStatus.BAD_REQUEST);
        if(dateDTO!=null){
            Integer vacationPay = calculatorService.calculateVacationPayWithDates(averageSalary,amountVacationDays,dateDTO);
            if(vacationPay==null){
                return new ResponseEntity<>("Некорректные данные для подсчёта",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new VacationPayResponse(vacationPay), HttpStatus.OK);
        }
        return new ResponseEntity<>(new VacationPayResponse(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)), HttpStatus.OK);
    }
}
