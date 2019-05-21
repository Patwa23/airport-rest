package com.travix.medusa.busyflights.domain.toughjet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Tough Jet Entity
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@Data
@AllArgsConstructor
public class ToughJetResponse {
    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private LocalDateTime outboundDateTime;
    private LocalDateTime inboundDateTime;
    private int numberOfAdults;
}
