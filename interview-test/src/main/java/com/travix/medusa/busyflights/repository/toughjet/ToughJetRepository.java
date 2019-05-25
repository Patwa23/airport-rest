package com.travix.medusa.busyflights.repository.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.time.LocalDate;
import java.util.Collection;


public interface ToughJetRepository {

    /**
     * Crazy Air Repository
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param toughJetRequest   Tough Jet Request
     * @return                  Return a list of matching Flights
     */
    Collection<ToughJetResponse> getFlights(ToughJetRequest toughJetRequest);
}
