package com.travix.medusa.busyflights.controller.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.service.crazyair.CrazyAirService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Collection<CrazyAirResponseDTO> getCrazyAirFlights(@Valid CrazyAirRequest crazyAirRequest) {
        return crazyAirService.getFlights(crazyAirRequest);
    }
}
