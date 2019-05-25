package com.travix.medusa.busyflights.domain.crazyair;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrazyAirRequest {
    @NotBlank
    @ApiModelProperty(value = "Origin", required = true)
    private String origin;
    @NotBlank
    @ApiModelProperty(value = "Destination", required = true)
    private String destination;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(value = "Departure Date", required = true)
    private LocalDate departureDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(value = "Return Date", required = true)
    private LocalDate returnDate;
    @ApiModelProperty(value = "Passenger Count", required = true)
    private int passengerCount;
}
