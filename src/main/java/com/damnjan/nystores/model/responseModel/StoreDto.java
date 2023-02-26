package com.damnjan.nystores.model.responseModel;

import lombok.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private String id;
    private String county;
    private String licenseNumber;
    private String establishmentType;
    private String entityName;
    private String dbaName;
    private String streetNumber;
    private String streetName;
    private String city;
    private String stateAbbreviation;
    private String zipCode;
    private String squareFootage;
    private GeoPoint location;
}
