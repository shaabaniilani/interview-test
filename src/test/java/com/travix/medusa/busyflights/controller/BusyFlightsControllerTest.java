package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolationException;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BusyFlightsController.class)
public class BusyFlightsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BusyFlightsService busyFlightsService;

    @Test
    public void testOK() throws Exception {
        when(busyFlightsService.searchFlights(any())).thenReturn(Collections.emptyList());
        mvc.perform(MockMvcRequestBuilders.get("/flights").param("origin", "ABC").param("destination", "XYZ").param("departureDate","2019-01-15").param("returnDate","2019-01-29").param("numberOfPassengers", "20"))
                .andExpect(status().isOk());
    }

    @Test
    public void testOriginValidation() throws Exception {
        when(busyFlightsService.searchFlights(any())).thenThrow(new ConstraintViolationException(null));
        mvc.perform(MockMvcRequestBuilders.get("/flights").param("origin", "ABCCC").param("destination", "XYZ").param("departureDate","2019-01-15").param("returnDate","2019-01-29").param("numberOfPassengers", "20"))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.get("/flights").param("destination", "XYZ").param("departureDate","2019-01-15").param("returnDate","2019-01-29").param("numberOfPassengers", "20"))
                .andExpect(status().is4xxClientError());
    }
}
