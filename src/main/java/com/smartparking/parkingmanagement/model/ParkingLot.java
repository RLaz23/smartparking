package com.smartparking.parkingmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {

    @Id
    @Column(length = 50)
    private String lotId;

    private String location;
    private int capacity;
    private int occupiedSpaces;
}