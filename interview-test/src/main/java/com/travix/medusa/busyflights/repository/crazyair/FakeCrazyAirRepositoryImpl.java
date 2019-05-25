package com.travix.medusa.busyflights.repository.crazyair;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FakeCrazyAirRepositoryImpl implements CrazyAirRepository {

    private List<CrazyAirResponse> flights = Arrays.asList(
            new CrazyAirResponse("Emirates", 650, "Economy", "AMS", "DEL", LocalDateTime.parse("2018-09-25T10:13:14.743"), LocalDateTime.parse("2018-09-27T10:13:14.743"), 6),
            new CrazyAirResponse("KLM", 1000, "Business", "AMS", "DEL", LocalDateTime.parse("2018-09-25T12:18:14.743"), LocalDateTime.parse("2018-09-27T18:45:20.743"), 8)
    );

    public Collection<CrazyAirResponse> getFlights(CrazyAirRequest crazyAirRequest) {
        return this.flights.stream()
                .filter(flight -> flight.getDepartureAirportCode().equals(crazyAirRequest.getOrigin())
                        && flight.getDestinationAirportCode().equals(crazyAirRequest.getDestination())
                        && LocalDate.from(flight.getDepartureDate()).equals(crazyAirRequest.getDepartureDate())
                        && LocalDate.from(flight.getArrivalDate()).equals(crazyAirRequest.getReturnDate())
                )
                .filter(flight -> flight.getPassengerCount() >= crazyAirRequest.getPassengerCount())
                .collect(Collectors.toList());
    }
}
