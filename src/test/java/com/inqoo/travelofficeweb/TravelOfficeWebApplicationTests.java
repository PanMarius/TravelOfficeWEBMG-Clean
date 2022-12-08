package com.inqoo.travelofficeweb;

import com.inqoo.travelofficeweb.model.Trip;
import com.inqoo.travelofficeweb.repository.TripJpaRepository;
import com.inqoo.travelofficeweb.service.TripService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
class TravelOfficeWebApplicationTests {
    private TripService tripService;
    private Trip tosave = new Trip();
    private Trip tosave2 = new Trip();

    @BeforeEach
    public void initDependencies() {
        tosave.setDestination("Berlin");
        tosave.setPriceEur(1500);
        tosave2.setDestination("Berlin2");
        tosave2.setPriceEur(1500);

        TripJpaRepository mockedTripRepository =
                Mockito.mock(TripJpaRepository.class);

        Mockito.when(mockedTripRepository.findAll())
                .thenReturn(List.of(tosave, tosave2));

        tripService = new TripService(mockedTripRepository);
    }

    @Test
    void saveTripShouldBeReturnedByFindAll() {
        tripService.saveTrip(tosave);
        List<Trip> allTrips = tripService.getAllTrips("Berlin");
        System.out.println(allTrips);
        org.junit.jupiter.api.Assertions.assertEquals(2, allTrips.size());
        Optional<Trip> maybeBerlin = allTrips.stream().filter(t -> t.getDestination().equals("Berlin")).findFirst();
        Assertions.assertThat(maybeBerlin);
    }
}
