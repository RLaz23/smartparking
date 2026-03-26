package com.smartparking.parkingmanagement.controller;

import lombok.RequiredArgsConstructor;
import com.smartparking.parkingmanagement.model.ParkingLot;
import com.smartparking.parkingmanagement.model.Vehicle;
import org.springframework.web.bind.annotation.*;
import com.smartparking.parkingmanagement.service.ParkingService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService service;

    @PostMapping("/lot")
    public ParkingLot createLot(@RequestBody ParkingLot lot) {
        return service.registerParkingLot(lot);
    }

    @PostMapping("/vehicle")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return service.registerVehicle(vehicle);
    }

    @PostMapping("/checkin")
    public String checkIn(@RequestParam String lotId,
                          @RequestParam String plate) {
        return service.checkIn(lotId, plate);
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam String plate) {
        return service.checkOut(plate);
    }

    @GetMapping("/lot/{id}")
    public Map<String, Object> getStatus(@PathVariable String id) {
        return service.getLotStatus(id);
    }

    @GetMapping("/lot/{id}/vehicles")
    public List<Vehicle> getVehicles(@PathVariable String id) {
        return service.getVehiclesInLot(id);
    }
}