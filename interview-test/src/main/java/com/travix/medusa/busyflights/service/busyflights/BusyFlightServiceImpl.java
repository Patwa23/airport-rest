package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
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
    public Collection<BusyFlightResponseDTO> getFlights(BusyFlightsRequest busyFlightsRequest) {

        List<CrazyAirResponseDTO> crazyAirFlights = getCrazyAirRest(busyFlightsRequest);
        List<ToughJetResponseDTO> toughJetFlights = getToughJetRest(busyFlightsRequest);
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


    private String getCrazyAirUrl(String url, BusyFlightsRequest busyFlightsRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("origin", busyFlightsRequest.getOrigin())
                .queryParam("destination", busyFlightsRequest.getDestination())
                .queryParam("departureDate", busyFlightsRequest.getDepartureDate())
                .queryParam("returnDate", busyFlightsRequest.getReturnDate())
                .queryParam("passengerCount", busyFlightsRequest.getNumberOfPassengers());
        return builder.toUriString();
    }

    private String getToughJetUrl(String url, BusyFlightsRequest busyFlightsRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("from", busyFlightsRequest.getOrigin())
                .queryParam("to", busyFlightsRequest.getDestination())
                .queryParam("outboundDate", busyFlightsRequest.getDepartureDate())
                .queryParam("inboundDate", busyFlightsRequest.getReturnDate())
                .queryParam("numberOfAdults", busyFlightsRequest.getNumberOfPassengers());
        return builder.toUriString();
    }

    private List<CrazyAirResponseDTO> getCrazyAirRest(BusyFlightsRequest busyFlightsRequest) {

        if (    busyFlightsRequest.getOrigin() == null ||
                busyFlightsRequest.getDestination() == null ||
                busyFlightsRequest.getDepartureDate() == null ||
                busyFlightsRequest.getReturnDate() == null ||
                busyFlightsRequest.getNumberOfPassengers() == 0
        ) {
            throw new BusyFlightIllegalException("Invalid Request");
        }
        String url = getCrazyAirUrl(crazyAirUrl, busyFlightsRequest);

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

    private List<ToughJetResponseDTO> getToughJetRest(BusyFlightsRequest busyFlightsRequest) {

        if (    busyFlightsRequest.getOrigin() == null ||
                busyFlightsRequest.getDestination() == null ||
                busyFlightsRequest.getDepartureDate() == null ||
                busyFlightsRequest.getReturnDate() == null ||
                busyFlightsRequest.getNumberOfPassengers() == 0
        ) {
            throw new BusyFlightIllegalException("Invalid Request");
        }
        String url = getToughJetUrl(toughJetUrl, busyFlightsRequest);

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
