package com.travix.medusa.busyflights.dto.busyflights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Busy Flight DTO to convert into required response
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusyFlightResponseDTO {
    private String airline;
    private String supplier;
    private double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
