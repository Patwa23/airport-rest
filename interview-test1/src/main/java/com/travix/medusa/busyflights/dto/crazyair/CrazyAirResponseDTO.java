package com.travix.medusa.busyflights.dto.crazyair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Crazy Air DTO to convert into required response
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrazyAirResponseDTO {
    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
