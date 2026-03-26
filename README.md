# Getting Started

## Guides
### Starting the Server
* Go to src/main/java/parkingManagementApplication and click on run
* Or you can type ".mvnw spring-boot:run"

## API
* **POST** /api/parking/lot
  * This API is responsible for registering a new parking lot in the system.
* **POST** /api/parking/vehicle
  * This API is used to register a new vehicle in the system.
* **POST** /api/parking/checkin?lotId={lotId}&plate={plate}.
  * This API is used for checking in a vehicle to an existing LOT that has free spaces.
  * This requires **lotId** of the parking lot and **plate** number of the vehicle.
* **POST** /api/parking/checkout?plate={plate}.
  * This API is used for checking out a vehicle in a parking LOT.
  * This API accepts **plate** number as its parameter.
*  **GET** /api/parking/lot/:id
  * This API is used to get the details of an existing parking lot in the system.
  * This API accepts **lotId** as its path variable.
*  **GET** /api/parking/lot/:id/vehicles
  * This API is used to get all the existing vehicles parked in a parking lot.
  * This API accepts **lotId** as its path variable.

## Test Cases
#### All test cases can be found in the postman collection named "smartparking.json" in this project.
#### data.sql contains the initial data for both ParkingLot and Vehicle model