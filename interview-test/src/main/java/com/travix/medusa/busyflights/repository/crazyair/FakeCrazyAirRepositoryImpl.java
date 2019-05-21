package com.travix.medusa.busyflights.repository.crazyair;

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

    public Collection<CrazyAirResponse> getFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengerCount) {
        return this.flights.stream()
                .filter(flight -> flight.getDepartureAirportCode().equals(origin)
                        && flight.getDestinationAirportCode().equals(destination)
                        && LocalDate.from(flight.getDepartureDate()).equals(departureDate)
                        && LocalDate.from(flight.getArrivalDate()).equals(returnDate)
                )
                .filter(flight -> flight.getPassengerCount() >= passengerCount)
                .collect(Collectors.toList());
    }
}
