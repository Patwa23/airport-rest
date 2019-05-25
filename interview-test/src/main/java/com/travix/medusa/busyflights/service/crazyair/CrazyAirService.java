package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface CrazyAirService {
    /**
     * Get a list of Carzy Air Flights
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param crazyAirRequest   Crazy Air Request
     * @return                  Return a list of flights
     */
    Collection<CrazyAirResponseDTO> getFlights(CrazyAirRequest crazyAirRequest);
}
