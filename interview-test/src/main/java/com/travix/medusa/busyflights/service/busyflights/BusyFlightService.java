package com.travix.medusa.busyflights.service.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;

import java.util.Collection;

public interface BusyFlightService {
    /**
     * Get an aggregated result of flights from both CrazyAir and ToughJet etc
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param busyFlightsRequest    Busy Flight Request
     * @return                      Return an aggregated result of flights from both CrazyAir and ToughJet etc
     */
    Collection<BusyFlightResponseDTO> getFlights(BusyFlightsRequest busyFlightsRequest);
}
