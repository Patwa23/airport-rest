package com.travix.medusa.busyflights.controller.toughjet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.service.toughjet.ToughJetService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Tough Jet Controller Unit Test")
class ToughJetControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToughJetService toughJetService;

    @InjectMocks
    private ToughJetController toughJetController;

    @Test
    @DisplayName("Get a tough jet flight list")
    void getToughJetFlights() throws Exception {

        List<ToughJetResponseDTO> mockToughJetResponseDTO = Arrays.asList(
                new ToughJetResponseDTO("KLM", 300, 200, 100, "AMS", "DEL", "2018-09-25T08:13:14.743Z", "2018-09-27T08:13:14.743Z"));

        ToughJetRequest toughJetRequest = new ToughJetRequest("AMS","DEL", LocalDate.parse("2018-09-25"),LocalDate.parse("2018-09-27"),1);

        when(toughJetService.getFlights(any())).thenReturn(mockToughJetResponseDTO);

        MvcResult result = mockMvc.perform(get("/toughjet/flights")
                .param("from", "AMS")
                .param("to", "DEL")
                .param("outboundDate", "2018-09-25")
                .param("inboundDate", "2018-09-27")
                .param("numberOfAdults", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();
        verify(toughJetService,times(1)).getFlights(toughJetRequest);
        verifyNoMoreInteractions(toughJetService);

        List<ToughJetResponseDTO> expectedToughJetResponseDTOList = Arrays.asList(
                new ToughJetResponseDTO("KLM", 300, 200, 100, "AMS", "DEL", "2018-09-25T08:13:14.743Z", "2018-09-27T08:13:14.743Z"));

        ObjectMapper objectMapper = new ObjectMapper();
        String expected = objectMapper.writeValueAsString(expectedToughJetResponseDTOList);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}