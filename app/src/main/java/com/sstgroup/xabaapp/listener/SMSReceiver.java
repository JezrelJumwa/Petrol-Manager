package com.sstgroup.xabaapp.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

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
                Object[] smsExtra = (Object[]) bundle.get("pbus");
                msgs = new SmsMessage[smsExtra.length];

                String format = bundle.getString("format");

                for (int i = 0; i < msgs.length; ++i) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                    String body = sms.getMessageBody().toString();
                    String sender = sms.getOriginatingAddress().toString();
                    Toast.makeText(context, "From :" + sender + "\n" + "body:" + body, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Timber.d("SMSReceiver onReceive throws exception: " + e.getLocalizedMessage(), e);
            }
        }
    }
}
