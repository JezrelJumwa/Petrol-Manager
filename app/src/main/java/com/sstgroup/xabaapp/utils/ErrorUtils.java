package com.sstgroup.xabaapp.utils;

import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.models.errors.ErrorLogin;
import com.sstgroup.xabaapp.models.errors.ErrorRegisterWorker;
import com.sstgroup.xabaapp.models.errors.ErrorStatusAndError;
import com.sstgroup.xabaapp.models.errors.ErrorWithDictionary;
import com.sstgroup.xabaapp.service.RestClient;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by rosenstoyanov on 6/6/17.
 */

public class ErrorUtils {

    public static ErrorCodeAndMessage parseErrorCodeMessage(Response<?> response) {
//        JSONObject object = (JSONObject) JSONValue.parse(jsonString);
//        response.errorBody().string()
//        response.errorBody().string()
        Converter<ResponseBody, ErrorCodeAndMessage> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(ErrorCodeAndMessage.class, ErrorCodeAndMessage.class.getAnnotations());
        try {
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            Timber.d("parseErrorCodeMessage exception : ", e);
            return new ErrorCodeAndMessage(Constants.ERROR_STATUS_UNEXPECTED, new ErrorCodeAndMessage.Error(1000, "Error Parse Failure"));
        }
    }

    public static ErrorLogin parseLoginError(Response<?> response) {
        Converter<ResponseBody, ErrorLogin> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(ErrorLogin.class, ErrorLogin.class.getAnnotations());
        try {
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            Timber.d("parseLoginError exception : ", e);
            return new ErrorLogin(Constants.ERROR_STATUS_UNEXPECTED, "Error Parse Failure");
        }
    }

    public static ErrorStatusAndError parseStatusAndError(Response<?> response) {
        Converter<ResponseBody, ErrorStatusAndError> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(ErrorStatusAndError.class, ErrorStatusAndError.class.getAnnotations());
        try {
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            Timber.d("parseStatusAndError exception : ", e);
            return new ErrorStatusAndError(Constants.ERROR_STATUS_UNEXPECTED, "Error Parse Failure");
        }
    }

    public static ErrorRegisterWorker parseRegisterWorkerError(Response<?> response) {
        Converter<ResponseBody, ErrorRegisterWorker> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(ErrorRegisterWorker.class, ErrorRegisterWorker.class.getAnnotations());
        try {
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            Timber.d("parseRegisterWorkerError exception : ", e);
            return new ErrorRegisterWorker(Constants.ERROR_STATUS_UNEXPECTED, null);
        }
    }

    public static ErrorWithDictionary parseErrorWithDictionary(Response<?> response) {
        Converter<ResponseBody, ErrorWithDictionary> converter =
                RestClient.getRetrofit()
                        .responseBodyConverter(ErrorWithDictionary.class, ErrorWithDictionary.class.getAnnotations());
        try {
            return converter.convert(response.errorBody());
        } catch (Exception e) {
            Timber.d("parseErrorWithDictionary exception : ", e);
            return new ErrorWithDictionary(Constants.ERROR_STATUS_UNEXPECTED, null);
        }
    }
}
