package com.travix.medusa.busyflights.repository.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.time.LocalDate;
import java.util.Collection;

public interface CrazyAirRepository {

    /**
     * Crazy Air Repository
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param crazyAirRequest   Crazy Air Request
     * @return                  List of matching Flights
     */
    Collection<CrazyAirResponse> getFlights(CrazyAirRequest crazyAirRequest);

}
