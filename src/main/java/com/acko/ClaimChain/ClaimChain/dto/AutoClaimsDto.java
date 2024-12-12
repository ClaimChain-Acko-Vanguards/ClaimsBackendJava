package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Data;

@Data
public class AutoClaimsDto extends ClaimsSuperDto{
    private String vehicleRegistrationNo;  // Vehicle registration number
    private String vehicleType;            // Type of vehicle (Car/Bike)
    private String vehicleMakeAndModel;    // Make and model of the vehicle
    private int carBikeAge;                // Age of the car/bike
    private String accidentDate;           // Date of the accident
    private String accidentLocation;       // Location of the accident
    private String garageName;             // Name of the garage
    private double repairEstimate;         // Repair estimate in INR
    private String drivingBehaviorData;    // Driving behavior data
    private boolean iotDataAvailable;
}
