package com.damnjan.nystores.document;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "stores")
public class Store {

    @Id
    private String id;
    @Field(type = FieldType.Text, name = "county")
    @CsvBindByName(column = "County")
    private String county;
    @Field(type = FieldType.Text, name = "license_number")
    @CsvBindByName(column = "License_Number")
    private String licenseNumber;
    @Field(type = FieldType.Text, name = "establishment_type")
    @CsvBindByName(column = "Establishment_Type")
    private String establishmentType;
    @Field(type = FieldType.Text, name = "entity_name")
    @CsvBindByName(column = "Entity_Name")
    private String entityName;
    @Field(type = FieldType.Text, name = "dba_name")
    @CsvBindByName(column = "DBA_Name")
    private String dbaName;
    @Field(type = FieldType.Text, name = "street_number")
    @CsvBindByName(column = "Street_Number")
    private String streetNumber;
    @Field(type = FieldType.Text, name = "street_name")
    @CsvBindByName(column = "Street_Name")
    private String streetName;
    @Field(type = FieldType.Text, name = "city")
    @CsvBindByName(column = "City")
    private String city;
    @Field(type = FieldType.Text, name = "state_abbreviation")
    @CsvBindByName(column = "State_Abbreviation")
    private String stateAbbreviation;
    @Field(type = FieldType.Text, name = "zip_code")
    @CsvBindByName(column = "Zip_Code")
    private String zipCode;
    @Field(type = FieldType.Text, name = "square_footage")
    @CsvBindByName(column = "Square_Footage")
    private String squareFootage;
    @Field(type = FieldType.Text, name = "latitude")
    @CsvBindByName(column = "Latitude")
    private Double latitude;
    @Field(type = FieldType.Text, name = "longitude")
    @CsvBindByName(column = "Longitude")
    private Double longitude;
}
