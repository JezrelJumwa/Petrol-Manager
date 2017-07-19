package com.sstgroup.xabaapp.service;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.errors.ErrorMapListString;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseXabaCall<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            ErrorMapListString errorMapListString = ErrorUtils.parseLoginError(response);

            if (errorMapListString.getError().equals(Constants.ERROR_UNAUTHORIZED)) {
                XabaApplication.getInstance().logout();
                //from this point we logout user
                return;
            }

            if (errorMapListString.getStatus().equals(Constants.ERROR_STATUS_UNEXPECTED)) {
                onFailure(XabaApplication.getInstance().getString(R.string.something_is_wrong));
            } else {
//                onFailure(errorMapListString.getError());
            }

        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof IOException) {
            //Add your code for displaying no network connection error
            onFailure(XabaApplication.getInstance().getString(R.string.check_your_internet_connection));
        } else {
            onFailure(XabaApplication.getInstance().getString(R.string.something_is_wrong));
        }
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onFailure(String error);


}
