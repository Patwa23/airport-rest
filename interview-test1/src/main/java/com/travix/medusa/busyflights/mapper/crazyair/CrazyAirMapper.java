package com.travix.medusa.busyflights.mapper.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.dto.crazyair.CrazyAirResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CrazyAirMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CrazyAirResponseDTO convertToCrazyAirResponseDTO(CrazyAirResponse crazyAirResponse){
        CrazyAirResponseDTO crazyAirResponseDTO= modelMapper.map(crazyAirResponse,CrazyAirResponseDTO.class);
        crazyAirResponseDTO.setDepartureDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(crazyAirResponse.getDepartureDate()));
        crazyAirResponseDTO.setArrivalDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(crazyAirResponse.getArrivalDate()));
        return crazyAirResponseDTO;
    }

}
