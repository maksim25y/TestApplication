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
        long averageSalary = 60_000L;
        int amountVacationDays = 10;
        long expected = 20_134L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(20_134,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs28() throws Exception {
        long averageSalary = 60_000L;
        int amountVacationDays = 28;
        long expected = 56_375L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(56_375,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs100_000AndAmountVacationDaysIs28() throws Exception {
        long averageSalary = 100_000L;
        int amountVacationDays = 28;
        long expected = 93_959L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(93_959,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs1_000_000AndAmountVacationDaysIs15() throws Exception {
        long averageSalary = 1_000_000L;
        int amountVacationDays = 15;
        long expected = 503_355L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(503_355,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs12_000AndAmountVacationDaysIs12() throws Exception {
        long averageSalary = 12_000L;
        int amountVacationDays = 12;
        long expected = 4_832L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(4_832,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs18_000AndAmountVacationDaysIs10() throws Exception {
        long averageSalary = 18_000L;
        int amountVacationDays = 10;
        long expected = 6_040L;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(6_040,getResultFromResponseBody(result));
    }

    private MvcResult getMvcResult(long averageSalary, int amountVacationDays) throws Exception {
        String URL_CALCULATE = "http://localhost:8080/calculate";
        return mockMvc.perform(MockMvcRequestBuilders.get(URL_CALCULATE)
                        .param("averageSalary",String.valueOf(averageSalary))
                        .param("amountVacationDays",String.valueOf(amountVacationDays)))
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
