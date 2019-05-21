package com.travix.medusa.busyflights.controller.crazyair;

import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Java RESTful Web Service endpoints for Crazy Air
 * <p>
 * Created by Prakash Patwa on 20/05/2019
 */
@RestController
@RequestMapping("/crazyair")
@Api(value = "CrazyAir", tags = ("Crazy Air API"))
public class CrazyAirController {

    @Autowired
    private CrazyAirService crazyAirService;

    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Get a list of Flights",
            notes = "Get a Crazy Air Flight list"
    )
    public Collection<CrazyAirResponseDTO> getCrazyAirFlights(@ApiParam(name = "origin", value = "3 letter IATA code(eg. LHR, AMS)", required = true) @RequestParam(name = "origin") String origin,
                                                              @ApiParam(name = "destination", value = "3 letter IATA code(eg. LHR, AMS)", required = true) @RequestParam(name = "destination") String destination,
                                                              @ApiParam(name = "departureDate", value = "ISO_LOCAL_DATE format", required = true) @RequestParam(name = "departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                              @ApiParam(name = "returnDate", value = "ISO_LOCAL_DATE format", required = true) @RequestParam(name = "returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                                              @ApiParam(name = "passengerCount", value = "Number of passengers",required = true) @RequestParam(name = "passengerCount") int passengerCount) {
        return crazyAirService.getFlights(origin, destination, departureDate, returnDate, passengerCount);
    }
}
