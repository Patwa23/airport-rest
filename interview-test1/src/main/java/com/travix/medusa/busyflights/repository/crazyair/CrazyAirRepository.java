package com.travix.medusa.busyflights.repository.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.time.LocalDate;
import java.util.Collection;

public interface CrazyAirRepository {

    /**
     * Crazy Air Repository
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param origin         3 letter IATA code(eg. LHR, AMS)
     * @param destination    3 letter IATA code(eg. LHR, AMS)
     * @param departureDate  ISO_LOCAL_DATE format
     * @param returnDate     ISO_LOCAL_DATE format
     * @param passengerCount Number of passengers
     * @return               List of matching Flights
     */
    Collection<CrazyAirResponse> getFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengerCount);

}
