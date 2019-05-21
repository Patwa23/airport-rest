package com.travix.medusa.busyflights.repository.toughjet;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FakeToughJetRepositoryImpl implements ToughJetRepository {

    private List<ToughJetResponse> flights = Arrays.asList(
            new ToughJetResponse("KLM", 300, 200, 100, "AMS", "DEL", LocalDateTime.parse("2018-09-25T10:13:14.743"), LocalDateTime.parse("2018-09-27T10:13:14.743"), 5)

    );

    public Collection<ToughJetResponse> getFlights(String from, String to, LocalDate outboundDate, LocalDate inboundDate, int numberOfAdults) {
        return this.flights.stream()
                .filter(flight -> flight.getDepartureAirportName().equals(from)
                        && flight.getArrivalAirportName().equals(to)
                        && LocalDate.from(flight.getOutboundDateTime()).equals(outboundDate)
                        && LocalDate.from(flight.getInboundDateTime()).equals(inboundDate)
                )
                .filter(flight -> flight.getNumberOfAdults() >= numberOfAdults)
                .collect(Collectors.toList());
    }
}
