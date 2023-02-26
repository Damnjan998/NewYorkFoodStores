package com.damnjan.nystores.document;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "stores")
public class Store {

    @Id
    private String id;
    @Field(type = FieldType.Text, name = "county")
    private String county;
    @Field(type = FieldType.Text, name = "license_number")
    private String licenseNumber;
    @Field(type = FieldType.Text, name = "establishment_type")
    private String establishmentType;
    @Field(type = FieldType.Text, name = "entity_name")
    private String entityName;
    @Field(type = FieldType.Text, name = "dba_name")
    private String dbaName;
    @Field(type = FieldType.Text, name = "street_number")
    private String streetNumber;
    @Field(type = FieldType.Text, name = "street_name")
    private String streetName;
    @Field(type = FieldType.Text, name = "city")
    private String city;
    @Field(type = FieldType.Text, name = "state_abbreviation")
    private String stateAbbreviation;
    @Field(type = FieldType.Text, name = "zip_code")
    private String zipCode;
    @Field(type = FieldType.Text, name = "square_footage")
    private String squareFootage;
    private GeoPoint location;
}
