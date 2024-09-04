package ru.mudan;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.mudan.controllers.CalculateController;
import ru.mudan.services.CalculatorService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTests {
    @Mock
    private CalculatorService calculatorService;

    private MockMvc mockMvc;
    @InjectMocks
    private CalculateController calculateController;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(calculateController).build();
    }
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs10() throws Exception {
        int averageSalary = 60_000;
        int amountVacationDays = 10;
        int expected = 20_134;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs28() throws Exception {
        int averageSalary = 60_000;
        int amountVacationDays = 28;
        int expected = 56_375;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs100_000AndAmountVacationDaysIs28() throws Exception {
        int averageSalary = 100_000;
        int amountVacationDays = 28;
        int expected = 93_959;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs1_000_000AndAmountVacationDaysIs15() throws Exception {
        int averageSalary = 1_000_000;
        int amountVacationDays = 15;
        int expected = 503_355;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs12_000AndAmountVacationDaysIs12() throws Exception {
        int averageSalary = 12_000;
        int amountVacationDays = 12;
        int expected = 4_832;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs12_000AndAmountVacationDaysIs12WithWeekendsAndHolidays() throws Exception {
        int averageSalary = 12_000;
        int amountVacationDays = 12;
        int expected = 2_416;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs18_000AndAmountVacationDaysIs10() throws Exception {
        int averageSalary = 18_000;
        int amountVacationDays = 10;
        int expected = 6_040;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getResultMvcWithNotStartDate(averageSalary, amountVacationDays);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs18_000AndAmountVacationDaysIs10WithWeekendsAndHolidays() throws Exception {
        int averageSalary = 18_000;
        int amountVacationDays = 10;
        int expected = 3_020;
        String startDate = "2024-11-01";
        when(calculatorService.calculateVacationPayWithDates(averageSalary,amountVacationDays,startDate)).thenReturn(expected);

        MvcResult result = getResultMvcWithStartDate(averageSalary, amountVacationDays,startDate);
        assertEquals(expected,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryLessThanZero() throws Exception {
        int averageSalary = -1;
        int amountVacationDays = 10;

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/calculate")
                        .param("averageSalary",String.valueOf(averageSalary))
                        .param("amountVacationDays",String.valueOf(amountVacationDays)))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void whenAmountVacationDaysLessThanZero() throws Exception {
        int averageSalary = 10_000;
        int amountVacationDays = -10;

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/calculate")
                        .param("averageSalary",String.valueOf(averageSalary))
                        .param("amountVacationDays",String.valueOf(amountVacationDays)))
                .andExpect(status().is4xxClientError());
    }

    private MvcResult getResultMvcWithNotStartDate(int averageSalary, int amountVacationDays) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/calculate")
                        .param("averageSalary",String.valueOf(averageSalary))
                        .param("amountVacationDays",String.valueOf(amountVacationDays)))
                .andExpect(status().isOk())
                .andReturn();
    }
    private MvcResult getResultMvcWithStartDate(int averageSalary, int amountVacationDays,String startDate) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/calculate")
                        .param("averageSalary",String.valueOf(averageSalary))
                        .param("amountVacationDays",String.valueOf(amountVacationDays))
                        .param("startDate",startDate))
                .andExpect(status().isOk())
                .andReturn();
    }

    private Integer getResultFromResponseBody(MvcResult result) throws UnsupportedEncodingException {
        String responseBody = getResponseBodyAsString(result);
        return getMessageFromJSOM(responseBody);
    }
    private String getResponseBodyAsString(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    }
    private Integer getMessageFromJSOM(String responseBody){
        return JsonPath.read(responseBody, String.format("$.%s", "vacationPay"));
    }

}
