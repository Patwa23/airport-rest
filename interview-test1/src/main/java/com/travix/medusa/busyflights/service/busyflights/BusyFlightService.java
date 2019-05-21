package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;

import java.util.Collection;

public interface BusyFlightService {
    /**
     * Get an aggregated result of flights from both CrazyAir and ToughJet etc
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param origin                3 letter IATA code(eg. LHR, AMS)
     * @param destination           3 letter IATA code(eg. LHR, AMS)
     * @param departureDate         ISO_LOCAL_DATE format
     * @param returnDate            ISO_LOCAL_DATE format
     * @param numberOfPassengers    Maximum 4 passengers
     * @return                      Return an aggregated result of flights from both CrazyAir and ToughJet etc
     */
    Collection<BusyFlightResponseDTO> getFlights(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers);
}
