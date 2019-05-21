package com.travix.medusa.busyflights.mapper.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class ToughJetMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ToughJetResponseDTO convertToToughJetResponseDTO(ToughJetResponse toughJetResponse) {
        ToughJetResponseDTO toughJetResponseDTO = modelMapper.map(toughJetResponse, ToughJetResponseDTO.class);
        toughJetResponseDTO.setInboundDateTime(DateTimeFormatter.ISO_INSTANT.format(toughJetResponse.getInboundDateTime().atZone(ZoneId.systemDefault())));
        toughJetResponseDTO.setOutboundDateTime(DateTimeFormatter.ISO_INSTANT.format(toughJetResponse.getOutboundDateTime().atZone(ZoneId.systemDefault())));
        return toughJetResponseDTO;
    }

}
