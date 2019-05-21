package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.exception.BusyFlightIllegalException;
import com.travix.medusa.busyflights.mapper.busyflights.BusyFlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightServiceImpl implements BusyFlightService{


    @Autowired
    private BusyFlightMapper busyFlightMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.resource.crazyair}")
    private String crazyAirUrl;

    @Value("${spring.resource.toughjet}")
    private String toughJetUrl;

    @Override
    public Collection<BusyFlightResponseDTO> getFlights(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {

        List<CrazyAirResponseDTO> crazyAirFlights = getCrazyAirRest(origin, destination, departureDate, returnDate, numberOfPassengers);
        List<ToughJetResponseDTO> toughJetFlights = getToughJetRest(origin, destination, departureDate, returnDate, numberOfPassengers);
        List<BusyFlightResponseDTO> busyFlightResponseDTOList = new ArrayList<>();
        busyFlightResponseDTOList.addAll(getCrazyAirFlights(crazyAirFlights));
        busyFlightResponseDTOList.addAll(getToughJetFlights(toughJetFlights));
        return busyFlightResponseDTOList;
    }

    private List<BusyFlightResponseDTO> getCrazyAirFlights(List<CrazyAirResponseDTO> flightList) {
        return flightList.stream()
                .map(flight ->  busyFlightMapper.convertCrazyAirToBusyFlightResponseDTO(flight))
                .collect(Collectors.toList());
    }

    private List<BusyFlightResponseDTO> getToughJetFlights(List<ToughJetResponseDTO> flightList) {
        return flightList.stream()
                .map(flight ->  busyFlightMapper.convertToughJetToBusyFlightResponseDTO(flight))
                .collect(Collectors.toList());
    }


    private String getCrazyAirUrl(String url, String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("departureDate", departureDate)
                .queryParam("returnDate", returnDate)
                .queryParam("passengerCount", numberOfPassengers);
        return builder.toUriString();
    }

    private String getToughJetUrl(String url, String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("from", origin)
                .queryParam("to", destination)
                .queryParam("outboundDate", departureDate)
                .queryParam("inboundDate", returnDate)
                .queryParam("numberOfAdults", numberOfPassengers);
        return builder.toUriString();
    }

    private List<CrazyAirResponseDTO> getCrazyAirRest(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {

        if (origin == null || destination == null || departureDate == null || returnDate == null || numberOfPassengers == 0) {
            throw new BusyFlightIllegalException("Invalid Request");
        }
        String url = getCrazyAirUrl(crazyAirUrl, origin, destination, departureDate, returnDate, numberOfPassengers);

        try {
            ResponseEntity<List<CrazyAirResponseDTO>> response = restTemplate
                    .exchange(url, HttpMethod.GET, null,  new ParameterizedTypeReference<List<CrazyAirResponseDTO>>() {
                    });
            if(response != null && response.hasBody()){
                return response.getBody();
            }
            return response.getBody();
        } catch (BusyFlightIllegalException e) {
            throw new BusyFlightIllegalException("Crazy Air - Airport not found");
        }
    }

    private List<ToughJetResponseDTO> getToughJetRest(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {

        if (origin == null || destination == null || departureDate == null || returnDate == null || numberOfPassengers == 0) {
            throw new BusyFlightIllegalException("Invalid Request");
        }
        String url = getToughJetUrl(toughJetUrl, origin, destination, departureDate, returnDate, numberOfPassengers);

        try {
            ResponseEntity<List<ToughJetResponseDTO>> response = restTemplate
                    .exchange(url, HttpMethod.GET, null,  new ParameterizedTypeReference<List<ToughJetResponseDTO>>() {
                    });
            if(response != null && response.hasBody()){
                return response.getBody();
            }
            return response.getBody();
        } catch (BusyFlightIllegalException e) {
            throw new BusyFlightIllegalException("Tough Jet - Airport not found");
        }
    }
}
