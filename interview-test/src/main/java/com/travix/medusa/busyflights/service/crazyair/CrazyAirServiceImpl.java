package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.mapper.crazyair.CrazyAirMapper;
import com.travix.medusa.busyflights.repository.crazyair.CrazyAirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CrazyAirServiceImpl implements CrazyAirService {

    @Autowired
    private CrazyAirRepository crazyAirRepository;

    @Autowired
    private CrazyAirMapper crazyAirMapper;

    @Override
    public Collection<CrazyAirResponseDTO> getFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengerCount) {

        Collection<CrazyAirResponse> flightList = crazyAirRepository.getFlights(origin, destination, departureDate, returnDate, passengerCount);
        return flightList.stream()
                .map(flight -> crazyAirMapper.convertToCrazyAirResponseDTO(flight))
                .collect(Collectors.toList());
    }
}
