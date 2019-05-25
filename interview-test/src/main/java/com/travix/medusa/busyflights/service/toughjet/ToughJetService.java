package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;

import java.time.LocalDate;
import java.util.Collection;

public interface ToughJetService {
    /**
     * Get a list of Tough Jet Flights
     *
     * Created by Prakash Patwa on 20/05/2019
     *
     * @param toughJetRequest   Tough Jet Request
     * @return                  Return a list of flight
     */
    Collection<ToughJetResponseDTO> getFlights(ToughJetRequest toughJetRequest);
}
