package ru.mudan.services;

import org.springframework.stereotype.Service;
import ru.mudan.dto.DateDTO;

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

    public Integer calculateVacationPayWithDates(int averageSalary, int amountVacationDays, DateDTO dateDTO) {
        try{
            LocalDate start = LocalDate.parse(dateDTO.getStartDate());
            LocalDate end = LocalDate.parse(dateDTO.getEndDate());
            int weekendDaysCount = getWeekendDays(start, end);
            return calculateVacationPay(averageSalary,amountVacationDays-weekendDaysCount);
        }catch (Exception e){
            return null;
        }
    }
    private int getWeekendDays(LocalDate start, LocalDate end) {
        return (int) start.datesUntil(end.plusDays(1))
                .filter(date ->
                        (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                                date.getDayOfWeek() == DayOfWeek.SUNDAY) &&
                                holidays.stream().noneMatch(holiday ->
                                        holiday.getMonthValue() == date.getMonthValue() &&
                                                holiday.getDayOfMonth() == date.getDayOfMonth()) ||
                                holidays.stream().anyMatch(holiday ->
                                        holiday.getMonthValue() == date.getMonthValue() &&
                                                holiday.getDayOfMonth() == date.getDayOfMonth())
                )
                .count();
    }
}
