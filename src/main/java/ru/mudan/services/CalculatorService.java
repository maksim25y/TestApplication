package ru.mudan.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CalculatorService {
    List<LocalDate> holidays = Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 11, 4)
    );
    public int calculateVacationPay(int averageSalary,int amountVacationDays){
        return (int) ((averageSalary/29.8)*amountVacationDays);
    }

    public Integer calculateVacationPayWithDates(int averageSalary, int amountVacationDays, String startDate) {
        try{
            LocalDate start = LocalDate.parse(startDate);
            int weekendDaysCount = getWeekendDays(start,amountVacationDays);
            return calculateVacationPay(averageSalary,amountVacationDays-weekendDaysCount);
        }catch (Exception e){
            return null;
        }
    }
    private int getWeekendDays(LocalDate start,int amount) {
        int count = 0;
        LocalDate currentDate = start;

        while (amount > 0 && count < amount) {
            LocalDate finalCurrentDate = currentDate;
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    currentDate.getDayOfWeek() == DayOfWeek.SUNDAY ||
                    holidays.stream().anyMatch(holiday ->
                            holiday.getMonthValue() == finalCurrentDate.getMonthValue() &&
                                    holiday.getDayOfMonth() == finalCurrentDate.getDayOfMonth()
                    )
            ) {
                count++;
                amount--;
            }
            currentDate = currentDate.plusDays(1);
        }

        return count;
    }
}
