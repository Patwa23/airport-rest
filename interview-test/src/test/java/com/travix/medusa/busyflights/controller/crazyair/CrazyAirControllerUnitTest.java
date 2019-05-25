package com.travix.medusa.busyflights.controller.crazyair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Crazy Air Controller Unit Test")
class CrazyAirControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrazyAirService crazyAirService;

    @InjectMocks
    private CrazyAirController crazyAirController;

    @Test
    @DisplayName("Get a crazy air flight list")
    void getCrazyAirFlights() throws Exception {

        List<CrazyAirResponseDTO> mockCrazyAirResponseDTO = Arrays.asList(
                new CrazyAirResponseDTO("Emirates", 650, "Economy", "AMS", "DEL", "2018-09-25T10:13:14.743", "2018-09-27T10:13:14.743"),
                new CrazyAirResponseDTO("KLM", 1000, "Business", "AMS", "DEL", "2018-09-25T12:18:14.743", "2018-09-27T18:45:20.743")
        );

        CrazyAirRequest crazyAirRequest = new CrazyAirRequest("AMS","DEL", LocalDate.parse("2018-09-25"),LocalDate.parse("2018-09-27"),2);

        when(crazyAirService.getFlights(any())).thenReturn(mockCrazyAirResponseDTO);

        MvcResult result = mockMvc.perform(get("/crazyair/flights")
                .param("origin", "AMS")
                .param("destination", "DEL")
                .param("departureDate", "2018-09-25")
                .param("returnDate", "2018-09-27")
                .param("passengerCount", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
        verify(crazyAirService,times(1)).getFlights(crazyAirRequest);
        verifyNoMoreInteractions(crazyAirService);

        List<CrazyAirResponseDTO> expectedCrazyAirResponseDTOList = Arrays.asList(
                new CrazyAirResponseDTO("Emirates", 650, "Economy", "AMS", "DEL", "2018-09-25T10:13:14.743", "2018-09-27T10:13:14.743"),
                new CrazyAirResponseDTO("KLM", 1000, "Business", "AMS", "DEL", "2018-09-25T12:18:14.743", "2018-09-27T18:45:20.743")
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String expected = objectMapper.writeValueAsString(expectedCrazyAirResponseDTOList);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}