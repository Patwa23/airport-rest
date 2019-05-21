package com.travix.medusa.busyflights.dto.toughjet;

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
public class ToughJetResponseDTO {
    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;
}
