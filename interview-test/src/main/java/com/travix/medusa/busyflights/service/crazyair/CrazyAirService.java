package com.travix.medusa.busyflights.service.crazyair;

import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface CrazyAirService {
    /**
     * Get a list of Carzy Air Flights
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param origin            3 letter IATA code(eg. LHR, AMS)
     * @param destination       3 letter IATA code(eg. LHR, AMS)
     * @param departureDate     ISO_LOCAL_DATE format
     * @param returnDate        ISO_LOCAL_DATE format
     * @param passengerCount    Number of passengers
     * @return                  Return a list of flights
     */
    Collection<CrazyAirResponseDTO> getFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengerCount);
}
