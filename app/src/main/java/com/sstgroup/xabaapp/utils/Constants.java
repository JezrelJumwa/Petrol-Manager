package com.sstgroup.xabaapp.utils;


public class Constants {

    public static final int SPLASH_DURATION = 3000;

    // web service
//    public static final String BASE_URL = "http://ec2-52-59-94-240.eu-central-1.compute.amazonaws.com/api/v1/en-US/";
    public static final String BASE_URL = "http://dev.xaba.org/api/v1/en-US/";

    public static final String WS_REGISTER_PATH = "worker/create/";
    public static final String WS_REGISTER_WORKER_BY_AGENT_PATH = "worker/create-by-agent/";
    public static final String WS_ACTIVATE_REGISTRATION_PATH = "worker/activate/";
    public static final String WS_RESEND_SMS_WITH_NEW_ACTIVATION_CODE_PATH = "worker/activate/resend-code/";
    public static final String WS_LOGIN_PATH = "worker/login/";
    public static final String WS_LOCATION_PATH = "location/list/";
    public static final String WS_PROFESSION_PATH = "profession/list/";
    public static final String WS_GET_USER_PATH = "worker/get-user/";
    public static final String WS_CHANGE_PIN_PATH = "worker/change-pin/";
    public static final String WS_RESET_VERIFY_PATH = "worker/reset/verify/";
    public static final String WS_RESET_PIN_PATH = "worker/reset/pin/";
    public static final String WS_UPDATE_WORKER_PATH = "worker/update/";

//    Extras
    public static final String AGENT_APP_NAME = "client";
    public static final String AGENT_APP_VALUE = "agent-app";
    public static final String ACTIVATION_CODE = "activation_code";
    public static final String STATUS = "status";
    public static final String BODY = "body";
    public static final String TOKEN = "token";
    public static final String HASH = "hash";
    public static final String NATIONAL_ID = "national_idn";
    public static final String PIN = "pin";
    public static final String OLD_PIN = "old_pin";
    public static final String NEW_PIN = "new_pin";
    public static final String PHONE = "phone";
    public static final String PREFERRED_LANGUAGE = "preferred_language";
    public static final String COUNTRY_ID = "country_id";
    public static final String COUNTY_ID = "county_id";
    public static final String SUBCOUNTY_ID = "subcounty_id";
    public static final String PROFESSIONS = "professions[]";
    public static final String AGENT_ID = "agent_id";
    public static final String WORKER_ID = "worker_id";
    public static final String VERIFICATION_CODE = "verification_code";
    public static final String REGISTER_CONFIRM_STARTED_FROM_LOGIN = "register_confirm_started_from_login";


    //error status
    public static final String ERROR_UNAUTHORIZED = "Unauthorized";
    public static final String ERROR_STATUS_UNEXPECTED = "unexpected";
    public static final String ERROR_STATUS_ERROR = "ERROR";
    public static final String ERROR_STATUS_OK = "OK";
    public static final String ERROR_STATUS_FAIL = "FAIL";

    //genders
    public static final String MALE = "male";
    public static final String FEMALE = "female";

    //visit xaba url
    public static final String VISIT_XABA_URL = "http://www.xaba.org";

    public static final String DATE_FORMAT_DASHES = "- d-MMM-yyyy";
}
