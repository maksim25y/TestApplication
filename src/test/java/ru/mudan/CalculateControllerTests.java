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

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(20_134,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs60_000AndAmountVacationDaysIs28() throws Exception {
        int averageSalary = 60_000;
        int amountVacationDays = 28;
        int expected = 56_375;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(56_375,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs100_000AndAmountVacationDaysIs28() throws Exception {
        int averageSalary = 100_000;
        int amountVacationDays = 28;
        int expected = 93_959;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(93_959,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs1_000_000AndAmountVacationDaysIs15() throws Exception {
        int averageSalary = 1_000_000;
        int amountVacationDays = 15;
        int expected = 503_355;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(503_355,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs12_000AndAmountVacationDaysIs12() throws Exception {
        int averageSalary = 12_000;
        int amountVacationDays = 12;
        int expected = 4_832;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(4_832,getResultFromResponseBody(result));
    }
    @Test
    public void whenAverageSalaryIs18_000AndAmountVacationDaysIs10() throws Exception {
        int averageSalary = 18_000;
        int amountVacationDays = 10;
        int expected = 6_040;
        when(calculatorService.calculateVacationPay(averageSalary,amountVacationDays)).thenReturn(expected);

        MvcResult result = getMvcResult(averageSalary, amountVacationDays);
        assertEquals(6_040,getResultFromResponseBody(result));
    }

    private MvcResult getMvcResult(int averageSalary, int amountVacationDays) throws Exception {
        String UR_CACUATE = "http://localhost:8080/calculate";
        return mockMvc.perform(MockMvcRequestBuilders.get(UR_CACUATE)
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
