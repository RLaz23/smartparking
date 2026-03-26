package com.smartparking.parkingmanagement.repository;

import com.smartparking.parkingmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByParkedLotId(String lotId);
}
