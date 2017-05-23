package com.sstgroup.xabaapp.service;


import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.ProfessionResponse;
import com.sstgroup.xabaapp.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.sstgroup.xabaapp.utils.Constants.AGENT_APP_NAME;
import static com.sstgroup.xabaapp.utils.Constants.NATIONAL_ID;
import static com.sstgroup.xabaapp.utils.Constants.PIN;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOCATION_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_LOGIN_PATH;
import static com.sstgroup.xabaapp.utils.Constants.WS_PROFESSION_PATH;

public interface LeadsService {

    @FormUrlEncoded
    @POST(WS_LOGIN_PATH)
    Call<UserResponse> login(@Field(AGENT_APP_NAME) String agentApp,
                             @Field(NATIONAL_ID) String nationalId,
                             @Field(PIN) String pin);

    @GET(WS_LOCATION_PATH)
    Call<LocationResponse> getLocations(@Header(AGENT_APP_NAME) String agentApp);

    @GET(WS_PROFESSION_PATH)
    Call<ProfessionResponse> getProfessions(@Header(AGENT_APP_NAME) String agentApp);
}
