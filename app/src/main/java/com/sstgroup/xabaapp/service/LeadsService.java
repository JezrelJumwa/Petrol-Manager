package com.sstgroup.xabaapp.service;


import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.models.ProfessionResponse;
import com.sstgroup.xabaapp.models.UserResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.sstgroup.xabaapp.utils.Constants.AGENT_APP_NAME;
import static com.sstgroup.xabaapp.utils.Constants.NATIONAL_ID;
import static com.sstgroup.xabaapp.utils.Constants.NEW_PIN;
import static com.sstgroup.xabaapp.utils.Constants.OLD_PIN;
import static com.sstgroup.xabaapp.utils.Constants.PIN;
import static com.sstgroup.xabaapp.utils.Constants.TOKEN;
import static com.sstgroup.xabaapp.utils.Constants.VERIFICATION_CODE;
import static com.sstgroup.xabaapp.utils.Constants.WS_CHANGE_PIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_GET_USER_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOCATION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOGIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_PROFESSION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_REGISTER_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_REGISTER_WORKER_BY_AGENT_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_RESET_PIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_RESET_VERIFY_PATH;

public interface LeadsService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(WS_REGISTER_PATH)
    Call<Object> register(@Body RequestBody model);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(WS_REGISTER_WORKER_BY_AGENT_PATH)
    Call<Object> registerWorkerByAgent(@Body RequestBody model);

    @FormUrlEncoded
    @POST(WS_GET_USER_PATH)
    Call<UserResponse> getWorkerData(@Field(AGENT_APP_NAME) String agentApp,
                                     @Field(TOKEN) String token);

    @FormUrlEncoded
    @POST(WS_LOGIN_PATH)
    Call<UserResponse> login(@Field(AGENT_APP_NAME) String agentApp,
                             @Field(NATIONAL_ID) String nationalId,
                             @Field(PIN) String pin);

    @GET(WS_LOCATION_PATH)
    Call<LocationResponse> getLocations(@Query(AGENT_APP_NAME) String agentApp);

    @GET(WS_PROFESSION_PATH)
    Call<ProfessionResponse> getProfessions(@Query(AGENT_APP_NAME) String agentApp);

    @FormUrlEncoded
    @POST(WS_CHANGE_PIN_PATH)
    Call<PinResponse> changePin(@Field(AGENT_APP_NAME) String agentApp,
                                @Field(TOKEN) String token,
                                @Field(OLD_PIN) String oldPin,
                                @Field(NEW_PIN) String newPin);

    @FormUrlEncoded
    @POST(WS_RESET_VERIFY_PATH)
    Call<PinResponse> sendVerificationCodeForResetPIN(@Field(AGENT_APP_NAME) String agentApp,
                                                      @Field(NATIONAL_ID) String nationalId);

    @FormUrlEncoded
    @POST(WS_RESET_PIN_PATH)
    Call<PinResponse> resetPin(@Field(AGENT_APP_NAME) String agentApp,
                               @Field(PIN) String pin,
                               @Field(VERIFICATION_CODE) String verificationCode);
}
