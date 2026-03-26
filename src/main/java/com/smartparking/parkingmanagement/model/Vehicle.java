package com.smartparking.parkingmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String ownerName;

    private String parkedLotId; // null if not parked
}