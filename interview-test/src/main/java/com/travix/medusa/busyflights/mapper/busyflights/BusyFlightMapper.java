package com.travix.medusa.busyflights.mapper.busyflights;

import com.travix.medusa.busyflights.dto.busyflights.BusyFlightResponseDTO;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class  BusyFlightMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BusyFlightResponseDTO convertCrazyAirToBusyFlightResponseDTO(CrazyAirResponseDTO crazyAirResponseDTO) {
        BusyFlightResponseDTO busyFlightResponseDTO = modelMapper.map(crazyAirResponseDTO, BusyFlightResponseDTO.class);
        busyFlightResponseDTO.setSupplier("CrazyAir");
        busyFlightResponseDTO.setFare(crazyAirResponseDTO.getPrice());
        busyFlightResponseDTO.setDepartureDate(LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(crazyAirResponseDTO.getDepartureDate())).toString());
        busyFlightResponseDTO.setArrivalDate(LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(crazyAirResponseDTO.getArrivalDate())).toString());
        return busyFlightResponseDTO;
    }

    public BusyFlightResponseDTO convertToughJetToBusyFlightResponseDTO(ToughJetResponseDTO toughJetResponseDTO) {
        BusyFlightResponseDTO busyFlightResponseDTO = modelMapper.map(toughJetResponseDTO, BusyFlightResponseDTO.class);
        busyFlightResponseDTO.setAirline(toughJetResponseDTO.getCarrier());
        busyFlightResponseDTO.setSupplier("ToughJet");
        busyFlightResponseDTO.setFare(toughJetResponseDTO.getBasePrice());
        busyFlightResponseDTO.setDepartureAirportCode(toughJetResponseDTO.getDepartureAirportName());
        busyFlightResponseDTO.setDestinationAirportCode(toughJetResponseDTO.getArrivalAirportName());
        busyFlightResponseDTO.setDepartureDate(LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(toughJetResponseDTO.getOutboundDateTime())).toString());
        busyFlightResponseDTO.setArrivalDate(LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(toughJetResponseDTO.getInboundDateTime())).toString());
        return busyFlightResponseDTO;
    }
}
