package com.smartparking.parkingmanagement.service;

import com.smartparking.parkingmanagement.exceptions.BadRequestException;
import com.smartparking.parkingmanagement.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import com.smartparking.parkingmanagement.model.ParkingLot;
import com.smartparking.parkingmanagement.model.Vehicle;
import org.springframework.stereotype.Service;
import com.smartparking.parkingmanagement.repository.ParkingLotRepository;
import com.smartparking.parkingmanagement.repository.VehicleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingLotRepository parkingLotRepo;
    private final VehicleRepository vehicleRepo;

    public ParkingLot registerParkingLot(ParkingLot lot) {
        return parkingLotRepo.save(lot);
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public String checkIn(String lotId, String licensePlate) {

        ParkingLot lot = parkingLotRepo.findById(lotId)
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found"));

        Vehicle vehicle = vehicleRepo.findById(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        if (vehicle.getParkedLotId() != null) {
            throw new BadRequestException("Vehicle already parked");
        }

        if (lot.getOccupiedSpaces() >= lot.getCapacity()) {
            throw new BadRequestException("Parking lot full");
        }

        lot.setOccupiedSpaces(lot.getOccupiedSpaces() + 1);
        vehicle.setParkedLotId(lotId);

        parkingLotRepo.save(lot);
        vehicleRepo.save(vehicle);

        return "Vehicle checked in successfully";
    }

    public String checkOut(String licensePlate) {

        Vehicle vehicle = vehicleRepo.findById(licensePlate)
                .orElseThrow(() ->  new ResourceNotFoundException("Lot not found"));

        if (vehicle.getParkedLotId() == null) {
            throw new BadRequestException("Vehicle not parked");
        }

        ParkingLot lot = parkingLotRepo.findById(vehicle.getParkedLotId())
                .orElseThrow(() ->  new ResourceNotFoundException("Lot not found"));

        lot.setOccupiedSpaces(lot.getOccupiedSpaces() - 1);
        vehicle.setParkedLotId(null);

        parkingLotRepo.save(lot);
        vehicleRepo.save(vehicle);

        return "Vehicle checked out successfully";
    }

    public Map<String, Object> getLotStatus(String lotId) {

        ParkingLot lot = parkingLotRepo.findById(lotId)
                .orElseThrow(() ->  new ResourceNotFoundException("Lot not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("capacity", lot.getCapacity());
        response.put("occupied", lot.getOccupiedSpaces());
        response.put("available", lot.getCapacity() - lot.getOccupiedSpaces());

        return response;
    }

    public List<Vehicle> getVehiclesInLot(String lotId) {
        return vehicleRepo.findByParkedLotId(lotId);
    }
}