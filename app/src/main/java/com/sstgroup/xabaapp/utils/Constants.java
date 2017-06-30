package com.sstgroup.xabaapp.utils;


public class Constants {

    public static final int SPLASH_DURATION = 3000;

    // web service
//    public static final String BASE_URL = "http://ec2-52-59-94-240.eu-central-1.compute.amazonaws.com/api/v1/en-US/";
//    public static final String BASE_URL = "http://dev.xaba.org/api/v1/en-US/";
    public static final String BASE_URL = "http://shikanishadev.xaba.org/api/v1/";

    public static final String WS_REGISTER_PATH = "{language}/worker/create/";
    public static final String WS_REGISTER_WORKER_BY_AGENT_PATH = "{language}/worker/create-by-agent/";
    public static final String WS_ACTIVATE_REGISTRATION_PATH = "{language}/worker/activate/";
    public static final String WS_RESEND_SMS_WITH_NEW_ACTIVATION_CODE_PATH = "{language}/worker/activate/resend-code/";
    public static final String WS_LOGIN_PATH = "{language}/worker/login/";
    public static final String WS_LOCATION_PATH = "{language}/location/list/";
    public static final String WS_PROFESSION_PATH = "{language}/profession/list/";
    public static final String WS_GET_USER_PATH = "{language}/worker/get-user/";
    public static final String WS_CHANGE_PIN_PATH = "{language}/worker/change-pin/";
    public static final String WS_RESET_VERIFY_PATH = "{language}/worker/reset/verify/";
    public static final String WS_RESET_PIN_PATH = "{language}/worker/reset/pin/";
    public static final String WS_UPDATE_WORKER_PATH = "{language}/worker/update/";
    public static final String WS_LOAD_NOTIFICATIONS = "{language}/worker/notifications/list/";
    public static final String WS_REFERRED_WORKERS = "{language}/worker/referred-workers/list/";
    public static final String WS_COMMISSIONS = "{language}/worker/commissions/list/";
    public static final String WS_DEACTIVATE_ACCOUNT = "{language}/worker/deactivate/";
    public static final String WS_SYSTEM_CONTACT = "{language}/system/contact/";


    //    Extras
    public static final String AGENT_APP_KEY = "client";
    public static final String AGENT_APP_VALUE = "agent-app";
    public static final String ACTIVATION_CODE = "activation_code";
    public static final String LANGUAGE = "language";
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
    public static final String PROGRAMS = "programs[]";
    public static final String AGENT_ID = "agent_id";
    public static final String WORKER_ID = "worker_id";
    public static final String VERIFICATION_CODE = "verification_code";
    public static final String REGISTER_CONFIRM_STARTED_FROM_LOGIN = "register_confirm_started_from_login";
    public static final String NOTIFICATION_FILTER = "filter[description]";
    public static final String COMMISSION_FILTER_PERIOD = "filter[period]";
    public static final String NOTIFICATION_LAST_ITEM = "next_page_params[from_id]";
    public static final String LOG_OUT_MESSAGE = "log_out_message";
    public static final String EMAIL = "email";
    public static final String MESSAGE = "message";

    //Notification types
    public static final String NOTIFICATION_PAYOUT = "payout";
    public static final String NOTIFICATION_REFERRAL_VALIDATION = "referral_validation";

    //commission log period
    public static final Integer COMMISSIONS_PERIOD_ALL = null;
    public static final Integer COMMISSIONS_PERIOD_TODAY = 1;
    public static final Integer COMMISSIONS_PERIOD_LAST_WEEK = 7;
    public static final Integer COMMISSIONS_PERIOD_LAST_30_DAYS = 30;
    //commission
    public static final String COMMISSIONS_TYPE_ALL = "";
    public static final String COMMISSIONS_TYPE_PAYOUT = "payout";
    public static final String COMMISSIONS_TYPE_REFERRAL_VALIDATION = "referral_validation";

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
    public static final String VISIT_XABA_URL = "http://dev.xaba.org/";

    //Activation code
    public static final String USER_ID_KEY = "userId";
    public static final String ACTIVATION_CODE_KEY = "activationCode";

    public static final String DATE_FORMAT_DASHES = "- d-MMM-yyyy";
    public static final String DATE_FORMAT_REFERRED_WORKERS = "d-MMM-yyyy - H:m";
}
