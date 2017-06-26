package com.sstgroup.xabaapp.listener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Preferences;

import timber.log.Timber;

/**
 * Created by julianlubenov on 6/23/17.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        /*if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                String sender = smsMessage.getOriginatingAddress().toString();
                Toast.makeText(context, "From :" + sender + "\n" + "body:" + messageBody, Toast.LENGTH_LONG).show();
            }
        }*/

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        if (bundle != null) {
            try {
                Object[] smsExtra = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[smsExtra.length];

                String format = bundle.getString("format");

                for (int i = 0; i < msgs.length; ++i) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                    String body = sms.getMessageBody().toString();
                    String sender = sms.getOriginatingAddress().toString();
                    String msg = "verification code for account in XABA is ";
                    //Toast.makeText(context, "From :" + sender + "\n" + "body:" + body, Toast.LENGTH_LONG).show();
                    if (body != null && body.contains(msg)) {
                        int index = body.indexOf(msg);
                        if (index > 0 && body.length() > index + msg.length()) {
                            String code = body.substring(index + msg.length());
                            int space_index = code.indexOf(" ");
                            if (space_index > 0 && code.length() > space_index) {
                                code = code.substring(0, space_index);
                            }
                            int dot_index = code.indexOf(".");
                            if (dot_index > 0 && code.length() > dot_index) {
                                code = code.substring(0, dot_index);
                            }

                            //Create login activity intent and add the userId and activation code extra
                            Long userId = Preferences.getLoggedUserId(XabaApplication.getInstance().getApplicationContext());
                            Intent loginActivityInent = new Intent(XabaApplication.getInstance().getApplicationContext(), LoginActivity.class);
                            loginActivityInent.putExtra(Constants.USER_ID_KEY, userId);
                            loginActivityInent.putExtra(Constants.ACTIVATION_CODE_KEY, code);
                            loginActivityInent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            XabaApplication.getInstance().getApplicationContext().startActivity(loginActivityInent);

                            //Toast.makeText(context, "From :" + sender + "\n" + "code:" + code, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (Exception e) {
                Timber.d("SMSReceiver onReceive throws exception: " + e.getLocalizedMessage(), e);
            }
        }
    }
}
