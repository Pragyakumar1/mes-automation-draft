package com.twist.twistautomation.DO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class TestContext {

    String item_id;
    String item_external_id;
    String sap_delivery_id;
    String quotation_id;

    String sequence;
    String srn_id;
    String chp_dep_barCode;
    String chip_plan_sp_id;
    String chip_plan_srq_id;
    //-- Fields to pass as part fo synthesis-runs API
    String synthesis_sp_id;
    String synthesis_srq_id;
    String synthesis_chip_id;
    String chip_type_id;
    String recipe_name;
    String machine_id;
    //--
    String human_id;
    Integer layer_count;
    // need to refactor this
    List<Integer> platesList=new ArrayList<Integer>();
    List<String> platesBarCodeList= new ArrayList<>();
    Map<Integer,String> barCodeMap=new LinkedHashMap<>();
    ArrayList<String> sfdcList= new ArrayList();
    ArrayList<String> worIdList= new ArrayList();
    String invoicePDFLink;

    @Autowired
    Plates plates;

    @Autowired
    OrderDetail orderDetail;

    List<OrderDetail> orderDetails = new ArrayList<>();

    List<String>destinationPlateDILA= new ArrayList<>();
    List<String>destinationPlateDILB= new ArrayList<>();
    List<String>destinationPlateMMX= new ArrayList<>();
    List<String>destinationPCRPlate= new ArrayList<>();
    List<String>destinationFlpBndhPlate=new ArrayList<>();
    List<String>destinatioinFLPBarcode=new ArrayList<>();

    List<String> sfdcListItem= new ArrayList<>();
    String glycerol_sfdc_id;
    String glycerol_quote_number;
    String glycerol_order_number;
    String srn_number;
    Set<String> srnSet= new HashSet<>();
    List<String>pCHPxBarcodeList=new ArrayList<>();
    List<String> destinationPlatePPRn= new ArrayList<>();
    Integer numberOfRequiredAmplification;
    Integer clusterCount;
    String DILAPlate;
    String DILBPlate;
    List<String>DILBarcodeList= new ArrayList<>();
    List<String>FLPBarcodeList= new ArrayList<>();
    List<String>DILABarCodeList=new ArrayList<>();
    List<String>DILBBarCodeList=new ArrayList<>();
    Integer numberOfSamples;

}
