package com.twist.twistautomation.props;

public interface STEndpoints {
    public static String AUTH = "/api/auth/login";
    public static String TRANSFORM_IDS = "/api/v1/rest/transform-ids/";
    public static String CHECK_PLATE_FOR_STEP = "/api/v1/check-plate-for-step/barcode/60/source";
    public static String BASIC_PLATE_INFO = "/api/v1/basic-plate-info/barcode";
    public static String TRANSFORM_PREVIEW = "/api/v1/transform-preview/type-60/template-2";
    public static String TRANSFORM_SPECS = "/api/v1/rest/transform-specs";
    public static String TRANSFORM_EXECUTION_HEADER_KEY = "Transform-Execution";
    public static String TRANSFORM_EXECUTION_HEADER_VALUE = "Immediate";
    public static String ECR_TRANSFORM_PREVIEW = "/api/v1/transform-preview/type-74/template-2";
    public static String PLS_TRANSFORM_PREVIEW = "/api/v1/transform-preview/type-288/template-113";
    public static String PLS_TRANSFORM_PREVIEW_TEMPLATE_130 = "/api/v1/transform-preview/type-286/template-130";
    public static String VIN_TRANSFORM_PREVIEW = "/api/v1/transform-preview/type-278/template-113";
}
