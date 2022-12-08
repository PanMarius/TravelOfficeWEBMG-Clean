package com.inqoo.travelofficeweb.repository;

import com.inqoo.travelofficeweb.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface TripJpaRepository extends JpaRepository<Trip, Integer>,
        JpaSpecificationExecutor<Trip>,
        RevisionRepository<Trip, Integer, Integer> {
    List<Trip> findAllByPriceEurBetween(double from, double to);
}