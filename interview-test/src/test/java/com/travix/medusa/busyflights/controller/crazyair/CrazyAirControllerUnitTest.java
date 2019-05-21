package com.travix.medusa.busyflights.controller.crazyair;

import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        when(crazyAirService.getFlights(anyString(), anyString(), any(), any(), anyInt())).thenReturn(mockCrazyAirResponseDTO);

        MvcResult result = mockMvc.perform(get("/crazyair/flights")
                .param("origin", "AMS")
                .param("destination", "DEL")
                .param("departureDate", "2018-09-25")
                .param("returnDate", "2018-09-27")
                .param("passengerCount", "2"))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[\n" +
                "  {\n" +
                "    \"airline\": \"Emirates\",\n" +
                "    \"price\": 650,\n" +
                "    \"cabinclass\": \"Economy\",\n" +
                "    \"departureAirportCode\": \"AMS\",\n" +
                "    \"destinationAirportCode\": \"DEL\",\n" +
                "    \"departureDate\": \"2018-09-25T10:13:14.743\",\n" +
                "    \"arrivalDate\": \"2018-09-27T10:13:14.743\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"airline\": \"KLM\",\n" +
                "    \"price\": 1000,\n" +
                "    \"cabinclass\": \"Business\",\n" +
                "    \"departureAirportCode\": \"AMS\",\n" +
                "    \"destinationAirportCode\": \"DEL\",\n" +
                "    \"departureDate\": \"2018-09-25T12:18:14.743\",\n" +
                "    \"arrivalDate\": \"2018-09-27T18:45:20.743\"\n" +
                "  }\n" +
                "]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}