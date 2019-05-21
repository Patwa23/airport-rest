package com.travix.medusa.busyflights.domain.busyflights;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Busy Flight Entity
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@Data
@AllArgsConstructor
public class BusyFlightsResponse {
    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int numberOfPassengers;
}
