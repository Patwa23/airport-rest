package com.travix.medusa.busyflights.domain.busyflights;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusyFlightsRequest {
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
    @Max(4)
    @ApiModelProperty(value = "Number of Passengers", required = true)
    private int numberOfPassengers;
}
