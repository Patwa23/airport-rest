package com.travix.medusa.busyflights.controller.toughjet;

import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.service.toughjet.ToughJetService;
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
 * Java RESTful Web Service endpoints for Tough Jet
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@RestController
@RequestMapping("/toughjet")
@Api(value = "ToughJet", tags = ("Tough Jet API"))
public class ToughJetController {

    @Autowired
    private ToughJetService toughJetService;

    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Get a list of Flights",
            notes = "Get a Crazy Air Flight list"
    )
    public Collection<ToughJetResponseDTO> getToughJetFlights(@ApiParam(name = "from", value = "3 letter IATA code(eg. LHR, AMS)",required = true) @RequestParam(name = "from") String from,
                                                              @ApiParam(name = "to", value = "3 letter IATA code(eg. LHR, AMS)",required = true) @RequestParam(name = "to") String to,
                                                              @ApiParam(name = "outboundDate", value = "ISO_LOCAL_DATE format",required = true) @RequestParam(name = "outboundDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate outboundDate,
                                                              @ApiParam(name = "inboundDate", value = "ISO_LOCAL_DATE format",required = true) @RequestParam(name = "inboundDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inboundDate,
                                                              @ApiParam(name = "numberOfAdults", value = "Number of passengers",required = true) @RequestParam(name = "numberOfAdults") int numberOfAdults) {
        return toughJetService.getFlights(from, to, outboundDate, inboundDate, numberOfAdults);
    }
}
