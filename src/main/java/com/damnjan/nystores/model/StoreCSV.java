package com.damnjan.nystores.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCSV {
    @CsvBindByName(column = "County")
    private String county;
    @CsvBindByName(column = "License_Number")
    private String licenseNumber;
    @CsvBindByName(column = "Establishment_Type")
    private String establishmentType;
    @CsvBindByName(column = "Entity_Name")
    private String entityName;
    @CsvBindByName(column = "DBA_Name")
    private String dbaName;
    @CsvBindByName(column = "Street_Number")
    private String streetNumber;
    @CsvBindByName(column = "Street_Name")
    private String streetName;
    @CsvBindByName(column = "City")
    private String city;
    @CsvBindByName(column = "State_Abbreviation")
    private String stateAbbreviation;
    @CsvBindByName(column = "Zip_Code")
    private String zipCode;
    @CsvBindByName(column = "Square_Footage")
    private String squareFootage;
    @CsvBindByName(column = "Latitude")
    private Double latitude;
    @CsvBindByName(column = "Longitude")
    private Double longitude;
}
