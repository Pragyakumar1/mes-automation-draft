package com.twist.twistautomation.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plates {

    String containerBarcode;
    List<String> destinationPlatePPRp = new ArrayList();
    List<String> destinationPlatePPRq = new ArrayList();
    List<String> destinationPlatePPRn = new ArrayList();
    String containerpECRsBarCode;
    //List<String>destinationPlatepECRe=new ArrayList<>();
    List<String> destinationPlatepECRe = new ArrayList();
    List<String> destinationPlatePEPRp = new ArrayList();
    List<String> destinationPlatePEPRq = new ArrayList();
    List<String> destinationPlatePEPRt = new ArrayList();
    Set<String> destinationPlatePPLSi = new HashSet<>();
    String destinationPlatePPLSv;
    Set<String> vectorPlateBarcode = new HashSet<>();
    Set<String> ltId = new HashSet<>();
    List<String> vectorTubeBarcodePLS = new ArrayList<>();
    Map<String, Integer> vectorVolume = new ConcurrentHashMap<>();
    List<String> vectorTubeIds = new ArrayList();
    String destinationRackId;
    List<String> thermoConditionECR = new ArrayList();
    List<String> TBKANBarcodes = new ArrayList();
    List<String> pTBSuBarcodes = new ArrayList();
    List<String> pRCAaBarcodes = new ArrayList();
    List<String> pRCAcBarcodes = new ArrayList();
    List<String> pRCArBarcodes = new ArrayList();
    List<String> pRCAdBarcodes = new ArrayList();
    List<String> pPLSiPlateList = new ArrayList<>();
    ArrayList<String> pCLOxBarcodeList = new ArrayList<>();
    Set<String> destinationPlatepCLOq = new HashSet<>();
    List<String> pCHPxBarcode = new ArrayList<>();
}
