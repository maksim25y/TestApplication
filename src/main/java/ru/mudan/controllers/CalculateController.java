package ru.mudan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    @GetMapping
    public ResponseEntity<Object>getVacationPay(@RequestParam Long averageSalary){
        return new ResponseEntity<>(averageSalary, HttpStatus.OK);
    }
}
