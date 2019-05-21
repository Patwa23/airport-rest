package com.travix.medusa.busyflights.domain.crazyair;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Crazy Air Entity
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@Data
@AllArgsConstructor
public class CrazyAirResponse {
    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDate;
    private int passengerCount;
}
