package com.travix.medusa.busyflights.repository.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.time.LocalDate;
import java.util.Collection;


public interface ToughJetRepository {

    /**
     * Crazy Air Repository
     *
     * Created by Prakash Patwa on 20/05/2019
     * @param from              3 letter IATA code(eg. LHR, AMS)
     * @param to                3 letter IATA code(eg. LHR, AMS)
     * @param outboundDate      ISO_LOCAL_DATE format
     * @param inboundDate       ISO_LOCAL_DATE format
     * @param numberOfAdults    Number of passengers
     * @return                  Return a list of matching Flights
     */
    Collection<ToughJetResponse> getFlights(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults);
}
