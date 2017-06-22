package com.sstgroup.xabaapp.service;


import com.sstgroup.xabaapp.models.ActivationCodeResponse;
import com.sstgroup.xabaapp.models.CommissionLogsResponse;
import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.NotificationResponse;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.models.ProfessionResponse;
import com.sstgroup.xabaapp.models.ReferredWorkersResponse;
import com.sstgroup.xabaapp.models.SendNewActivationCodeResponse;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.models.XabaResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.sstgroup.xabaapp.utils.Constants.ACTIVATION_CODE;
import static com.sstgroup.xabaapp.utils.Constants.AGENT_APP_KEY;
import static com.sstgroup.xabaapp.utils.Constants.HASH;
import static com.sstgroup.xabaapp.utils.Constants.LANGUAGE;
import static com.sstgroup.xabaapp.utils.Constants.NATIONAL_ID;
import static com.sstgroup.xabaapp.utils.Constants.NEW_PIN;
import static com.sstgroup.xabaapp.utils.Constants.NOTIFICATION_FILTER;
import static com.sstgroup.xabaapp.utils.Constants.NOTIFICATION_LAST_ITEM;
import static com.sstgroup.xabaapp.utils.Constants.OLD_PIN;
import static com.sstgroup.xabaapp.utils.Constants.PIN;
import static com.sstgroup.xabaapp.utils.Constants.TOKEN;
import static com.sstgroup.xabaapp.utils.Constants.WORKER_ID;
import static com.sstgroup.xabaapp.utils.Constants.WS_ACTIVATE_REGISTRATION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_CHANGE_PIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_COMMISSIONS;
import static com.sstgroup.xabaapp.utils.Constants.WS_DEACTIVATE_ACCOUNT;
import static com.sstgroup.xabaapp.utils.Constants.WS_GET_USER_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOAD_NOTIFICATIONS;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOCATION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOGIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_PROFESSION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_REFERRED_WORKERS;
import static com.sstgroup.xabaapp.utils.Constants.WS_REGISTER_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_REGISTER_WORKER_BY_AGENT_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_RESEND_SMS_WITH_NEW_ACTIVATION_CODE_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_RESET_PIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_RESET_VERIFY_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_UPDATE_WORKER_PATH;

public interface XabaService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(WS_REGISTER_PATH)
    Call<UserResponse> register(@Path("language") String language, @Body RequestBody model);

    @FormUrlEncoded
    @POST(WS_ACTIVATE_REGISTRATION_PATH)
    Call<ActivationCodeResponse> sendActivationCode(@Path(LANGUAGE) String language, @Field(AGENT_APP_KEY) String agentApp,
                                                    @Field(ACTIVATION_CODE) String activationCode);

    @FormUrlEncoded
    @POST(WS_RESEND_SMS_WITH_NEW_ACTIVATION_CODE_PATH)
    Call<SendNewActivationCodeResponse> sendSmsWithNewActivationCode(@Path(LANGUAGE) String language,
                                                                     @Field(AGENT_APP_KEY) String agentApp,
                                                                     @Field(WORKER_ID) Long workerId);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(WS_REGISTER_WORKER_BY_AGENT_PATH)
    Call<Object> registerWorkerByAgent(@Path(LANGUAGE) String language, @Body RequestBody model);

    @FormUrlEncoded
    @POST(WS_GET_USER_PATH)
    Call<UserResponse> getWorkerData(@Path(LANGUAGE) String language, @Field(AGENT_APP_KEY) String agentApp,
                                     @Field(TOKEN) String token);

    @FormUrlEncoded
    @POST(WS_LOGIN_PATH)
    Call<UserResponse> login(@Path(LANGUAGE) String language, @Field(AGENT_APP_KEY) String agentApp,
                             @Field(NATIONAL_ID) String nationalId,
                             @Field(PIN) String pin);

    @GET(WS_LOCATION_PATH)
    Call<LocationResponse> getLocations(@Path(LANGUAGE) String language,
                                        @Query(AGENT_APP_KEY) String agentApp, @Query(HASH) String hash);

    @GET(WS_PROFESSION_PATH)
    Call<ProfessionResponse> getProfessions(@Path(LANGUAGE) String language,
                                            @Query(AGENT_APP_KEY) String agentApp, @Query(HASH) String hash);

    @FormUrlEncoded
    @POST(WS_CHANGE_PIN_PATH)
    Call<PinResponse> changePin(@Path(LANGUAGE) String language,
                                @Field(AGENT_APP_KEY) String agentApp,
                                @Field(TOKEN) String token,
                                @Field(OLD_PIN) String oldPin,
                                @Field(NEW_PIN) String newPin);

    @FormUrlEncoded
    @POST(WS_RESET_VERIFY_PATH)
    Call<PinResponse> sendVerificationCodeForResetPIN(@Path(LANGUAGE) String language,
                                                      @Field(AGENT_APP_KEY) String agentApp,
                                                      @Field(NATIONAL_ID) String nationalId);

    @FormUrlEncoded
    @POST(WS_RESET_PIN_PATH)
    Call<PinResponse> resetPin(@Path(LANGUAGE) String language,
                               @Field(AGENT_APP_KEY) String agentApp,
                               @Field(NATIONAL_ID) String nationalId);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(WS_UPDATE_WORKER_PATH)
    Call<UserResponse> updateWorker(@Path(LANGUAGE) String language,
                                    @Body RequestBody model);

    @FormUrlEncoded
    @POST(WS_LOAD_NOTIFICATIONS)
    Call<XabaResponse<NotificationResponse>> loadNotifications(
            @Path(LANGUAGE) String language,
            @Field(AGENT_APP_KEY) String agentApp,
            @Field(TOKEN) String token,
            @Field(NOTIFICATION_FILTER) String filter,
            @Field(NOTIFICATION_LAST_ITEM) Integer lastItemId);

    @FormUrlEncoded
    @POST(WS_REFERRED_WORKERS)
    Call<XabaResponse<ReferredWorkersResponse>> loadReferredWorkers(
            @Path(LANGUAGE) String language,
            @Field(AGENT_APP_KEY) String agentApp,
            @Field(TOKEN) String token);

    @FormUrlEncoded
    @POST(WS_COMMISSIONS)
    Call<XabaResponse<CommissionLogsResponse>> loadCommissionLogs(
            @Path(LANGUAGE) String language,
            @Field(AGENT_APP_KEY) String agentApp,
            @Field(TOKEN) String token);

    @FormUrlEncoded
    @POST(WS_DEACTIVATE_ACCOUNT)
    Call<Object> deactivateAccount(@Path(LANGUAGE) String language,
                                   @Field(AGENT_APP_KEY) String agentApp,
                                   @Field(TOKEN) String token);

}
