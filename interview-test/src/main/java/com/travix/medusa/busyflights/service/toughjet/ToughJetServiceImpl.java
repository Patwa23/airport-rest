package com.travix.medusa.busyflights.service.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.dto.toughjet.ToughJetResponseDTO;
import com.travix.medusa.busyflights.mapper.toughjet.ToughJetMapper;
import com.travix.medusa.busyflights.repository.toughjet.ToughJetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ToughJetServiceImpl implements ToughJetService {

    @Autowired
    private ToughJetRepository toughJetRepository;

    @Autowired
    private ToughJetMapper toughJetMapper;

    @Override
    public Collection<ToughJetResponseDTO> getFlights(ToughJetRequest toughJetRequest) {
        Collection<ToughJetResponse> flightList = toughJetRepository.getFlights(toughJetRequest);
        return flightList.stream()
                .map(flight -> toughJetMapper.convertToToughJetResponseDTO(flight))
                .collect(Collectors.toList());

    }
}
