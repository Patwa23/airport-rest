package com.travix.medusa.busyflights.controller.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;
import com.travix.medusa.busyflights.service.busyflights.BusyFlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.Collection;

/**
 * Java RESTful Web Service endpoints for Busy Flights
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@RestController
@RequestMapping("/busyflight")
@Api(value = "BusyFlight", tags = ("Busy Flight API"))
@Validated
public class BusyFlightController {

    @Autowired
    private BusyFlightService busyFlightService;

    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Get a list of Flights",
            notes = "Get an aggregated result of flights from both CrazyAir and ToughJet etc"
    )
    public Collection<BusyFlightResponseDTO> getBusyFlights(@Valid BusyFlightsRequest busyFlightsRequest) {
        return busyFlightService.getFlights(busyFlightsRequest);
    }
}
