package com.enneagon.digital.exchange.protocol.chinaunion.biz;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class QueryStationInfoVO {

    private String StationID;

    private String OperatorID;

    private String EquipmentOwnerID;

    private String StationName;

    private String CountryCode;

    private String AreaCode;

    private String Address;

    private String StationTel;

    private Integer StationType;

    private Integer StationStatus;

    private Integer ParkNums;

    private Double StationLng;

    private Double StationLat;

    private String SiteGuide;

    private Integer Construction;

    private String[] Pictures;
    
    private String MatchCars;

    private String ParkInfo;

    private String BusineHours;

    private String ElectricityFee;

    private String ServiceFee;

    private String ParkFee;

    private String Payment;

    private Integer SupportOrder;

    private String Remark;

    private List<EquipmentInfoVO> EquipmentInfos;



}
