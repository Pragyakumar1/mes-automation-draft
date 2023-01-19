package com.twist.twistautomation.props;

public interface MESEndpoints {
    public static String PLATE_DETAILS = "/api/sampletracker/rest/plates/{barcode}/details";
    public static String ORDER_FULL_DETAILS = "/api/kung/orders/{wor_id}/full-details";
    public static String SEARCH_VECTOR_NODES = "/api/clu/vector-nodes/search";
    public static String VECTOR_NODE_DETAILS = "/api/clu/vector-nodes/{parent_id}";
    public static String INSERT_VECTOR_TUBE = "/api/clu/vector-nodes/{parent_id}/tubes";
    public static String ITEM_TRANSITIONS = "/api/skub/item_transitions";
    public static String GET_STORAGE_CONTAINER_BARCODES = "/api/storage/v1.0/stores/Vector inventory/tree?includeContainers=true";
    public static String ORDER_DETAILS = "/api/kung/tapi/orders/{sfdc_id}/details";
}
