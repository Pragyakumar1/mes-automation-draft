package com.twist.twistautomation.props;

public interface TAPIEndpoints {

    public static String VERSION = "/v1";
    public static String EMAIL = "ragade@twistbioscience.com";
    public static String CREATE_CONSTRUCT_RESOURCE = VERSION+"/users/"+EMAIL+"/constructs/";
    public static String CREATE_QUOTE_RESOURCE = VERSION+"/users/"+EMAIL+"/quotes/";
    public static String GET_QUOTE_RESOURCE = VERSION+"/users/"+EMAIL+"/quotes/quotation_id/";
    public static String CREATE_ORDER_RESOURCE = VERSION+"/users/"+EMAIL+"/orders/";
    public static String GET_ORDER_RESOURCE = VERSION+"/users/"+EMAIL+"/orders/sfdc_id/items/";
    public static String GET_ORDER_ITEM_RESOURCE = VERSION+"/mes/orders/sfdc_id/order-items/";
    public static String ORDER_TRANSFORMER_RESOURCE = VERSION+"/mes/ot/trigger/";
    public static String CREATE_INVENTORY_RESOURCE = VERSION+"/orders/sfdc_id/inventory-transaction/";
    public static String CREATE_SHIPMENT_RESOURCE = "/internal/shipments/";
    public static String FINALIZE_SHIPMENT_RESOURCE = "/internal/shipments/sap_delivery_id/finalize/";
    public static String CREATE_INVOICE_RESOURCE = "/internal/shipments/sap_delivery_id/update/";
    public static String SEQUENCE_GEN = "https://users-birc.au.dk/~palle/php/fabox/random_sequence_generator.php";
    public static String GET_PDF_INVOICE_RESOURCE=VERSION+"/users/"+EMAIL+"/invoices/sap_delivery_id/pdf/?get_url=true";

    //- Synthesis API
    public static String CREATE_SYNTHESIS_RUN_RESOURCE="/api/v2/rest/synthesis-runs";
    public static String CREATE_ADD_STARTED_EVENT_RESOURCE="/api/v2/rest/synthesis-runs/srn_id/status-events";
    public static String CREATE_LAYER_COMPLETION_RESOURCE="/api/v2/rest/synthesis-runs/srn_id/layers/layer_count/completion";
    public static String CREATE_ADD_COMPLETED_EVENT_RESOURCE="/api/v2/rest/synthesis-runs/srn_id/status-events";
    public static String GENERATION_EXTRACTION_PLATE_RESOURCE="/api/${version}/rest/extraction/extraction-plate-barcode";
    public static String EXTRACTION_SET_TO_EXTRACTION_PLATE_RESOURCE="/api/${version}/rest/extraction/extraction-plate-spec";
    public static String GET_CHP_DEPROTECTION_READY_PLATES_RESOURCE="/api/v1/plates-ready-queue/62";
    public static String CREATE_CHP_DEPROTECTION_SUBMISSION_RESOURCE="/api/quest/responses/batch";
    public static String GENERATE_EXTRACTION_PLATE_BAR_CODE_RESOURCE="/api/v1/rest/extraction/extraction-plate-barcode";
    public static String EXTRACTION_SET_EXTRACTION_PLATE_BAR_CODE_RESOURCE="/api/v1/rest/extraction/extraction-plate-spec";
    public static String GET_PLATE_POSITION_RESOURCE="/api/kung/synth-admin-details/synthesis-request/srq_number?fields=summary&version=1";
    //public static String GET_ORDER_RESOURCE = VERSION+"/users/"+"twistuat88@gmail.com"+"/orders/sfdc_id/items/";
    public static String GET_PLATE_INFO_RESOURCE="/api/v1/basic-plate-info/bar_code";
}
