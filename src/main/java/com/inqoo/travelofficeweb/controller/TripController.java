package com.inqoo.travelofficeweb.controller;

import com.inqoo.travelofficeweb.model.Trip;
import com.inqoo.travelofficeweb.model.exception.ErrorMessage;
import com.inqoo.travelofficeweb.model.exception.NoTripFoundException;
import com.inqoo.travelofficeweb.model.exception.WrongParameters;
import com.inqoo.travelofficeweb.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping(path = "/trips", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        URI savedCityUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trip.getId())
                .toUri();
        // powinniśmy zwrócić URL właśnie zapisanego miasta
        return ResponseEntity.created(savedCityUri).build();
    }

    @GetMapping(path = "/trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trip>> trips(@RequestParam(name = "tripDestinationFragment", required = false) String nameFragment) {
        System.out.println("Zapytanie zawierało parametr 'tripDestinationFragment' o wartości: " + nameFragment);
        return ResponseEntity.ok(tripService.getAllTrips(nameFragment));
    }

    @GetMapping(path = "/tripsByPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trip>> tripsByPrice(@RequestParam double priceFrom, @RequestParam double priceTo) {
        try {
            return ResponseEntity.ok(tripService.getByPrice(priceFrom, priceTo));
        } catch (NoTripFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/trips/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> tripsById(@PathVariable("tripId") Integer id) {
        return ResponseEntity.ok(tripService.getAllTrips(null).get(id));
    }

    @ExceptionHandler(NoTripFoundException.class) // jaki wyjątek obsługujemy
    @ResponseStatus(HttpStatus.NOT_FOUND) // jaki kod HTTP zwrócimy
    public ResponseEntity<ErrorMessage> handleNoTripFoundException(NoTripFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @GetMapping(path = "/trips/{tripId}/revisions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trip>> tripRevisionById(@PathVariable("tripId") Integer id) {
        return ResponseEntity.ok(tripService.getAllTripsRevisions(id).get()
                .map(Revision::getEntity)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler(WrongParameters.class) // jaki wyjątek obsługujemy
    @ResponseStatus(HttpStatus.BAD_REQUEST) // jaki kod HTTP zwrócimy
    public ResponseEntity<ErrorMessage> handleBadParametersException(WrongParameters exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @DeleteMapping(path = "/trips/{id}")
    public ResponseEntity removeTrip(@PathVariable Integer id) {
        tripService.removeTripById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/trips/{id}")
    public ResponseEntity updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
        tripService.updateTrip(id, trip);
        return ResponseEntity.noContent().build();
    }
}