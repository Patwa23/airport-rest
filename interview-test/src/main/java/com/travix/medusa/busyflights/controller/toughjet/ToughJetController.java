package com.travix.medusa.busyflights.controller.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.service.toughjet.ToughJetService;
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
    public Collection<ToughJetResponseDTO> getToughJetFlights(@Valid ToughJetRequest toughJetRequest) {
        return toughJetService.getFlights(toughJetRequest);
    }
}
