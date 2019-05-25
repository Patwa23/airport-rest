package com.travix.medusa.busyflights.controller.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.controller.crazyair.CrazyAirController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.service.busyflights.BusyFlightService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Busy Flight Controller Unit Test")
class BusyFlightControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusyFlightService busyFlightService;

    @InjectMocks
    private BusyFlightController busyFlightController;

    @Test
    @DisplayName("Get an aggregated result of flights from both CrazyAir and ToughJet etc")
    void getBusyFlights() throws Exception {

        List<BusyFlightResponseDTO> mockBusyFlightResponseDTO = Arrays.asList(
                new BusyFlightResponseDTO("Emirates","CrazyAir", 650,"AMS", "DEL", "2018-09-25T10:13:14.743", "2018-09-27T10:13:14.743"),
                new BusyFlightResponseDTO("KLM", "CrazyAir", 1000, "AMS", "DEL", "2018-09-25T12:18:14.743", "2018-09-27T18:45:20.743"),
                new BusyFlightResponseDTO("KLM", "ToughJet", 300, "AMS", "DEL", "2018-09-25T08:13:14.743", "2018-09-27T08:13:14.743")
                );

        BusyFlightsRequest busyFlightsRequest= new BusyFlightsRequest("AMS","DEL", LocalDate.parse("2018-09-25"),LocalDate.parse("2018-09-27"),3);

        when(busyFlightService.getFlights(any())).thenReturn(mockBusyFlightResponseDTO);

        MvcResult result = mockMvc.perform(get("/busyflight/flights")
                .param("origin", "AMS")
                .param("destination", "DEL")
                .param("departureDate", "2018-09-25")
                .param("returnDate", "2018-09-27")
                .param("numberOfPassengers", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();
        verify(busyFlightService,times(1)).getFlights(busyFlightsRequest);
        verifyNoMoreInteractions(busyFlightService);

        List<BusyFlightResponseDTO> expectedBusyFlightResponseDTO = Arrays.asList(
                new BusyFlightResponseDTO("Emirates","CrazyAir", 650,"AMS", "DEL", "2018-09-25T10:13:14.743", "2018-09-27T10:13:14.743"),
                new BusyFlightResponseDTO("KLM", "CrazyAir", 1000, "AMS", "DEL", "2018-09-25T12:18:14.743", "2018-09-27T18:45:20.743"),
                new BusyFlightResponseDTO("KLM", "ToughJet", 300, "AMS", "DEL", "2018-09-25T08:13:14.743", "2018-09-27T08:13:14.743")
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String expected = objectMapper.writeValueAsString(expectedBusyFlightResponseDTO);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}