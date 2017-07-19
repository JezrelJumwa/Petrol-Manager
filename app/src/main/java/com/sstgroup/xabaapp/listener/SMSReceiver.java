package com.sstgroup.xabaapp.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Preferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Created by julianlubenov on 6/23/17.
 */

public class SMSReceiver extends BroadcastReceiver {

    private String localizedVerificationCode = null;
    private String localizedActivationCode = null;

    public void setLocalizedVerificationCode(String localizedVerificationCode) {
        this.localizedVerificationCode = localizedVerificationCode;
    }

    public void setLocalizedActivationCode(String localizedActivationCode) {
        this.localizedActivationCode = localizedActivationCode;
    }

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
                    String verificationCode = parseSMSVerificationCode(body);
                    if (verificationCode != null && verificationCode.length() > 0) {
                        //Create login activity intent and add the userId and activation code extra
                        Long userId = Preferences.getLoggedUserId(XabaApplication.getInstance().getApplicationContext());
                        Intent loginActivityInent = new Intent(XabaApplication.getInstance().getApplicationContext(), LoginActivity.class);
                        loginActivityInent.putExtra(Constants.USER_ID_KEY, userId);
                        loginActivityInent.putExtra(Constants.ACTIVATION_CODE_KEY, verificationCode);
                        loginActivityInent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        XabaApplication.getInstance().getApplicationContext().startActivity(loginActivityInent);

                        //Toast.makeText(context, "From :" + sender + "\n" + "code:" + verificationCode, Toast.LENGTH_LONG).show();

                    }
                }
            } catch (Exception e) {
                Timber.d("SMSReceiver onReceive throws exception: " + e.getLocalizedMessage(), e);
            }
        }
    }

    /**
     * This method parses SMS message string and parses XABA verification code.
     * To recognize that the SMS is for XABA verification code this method checks two things.
     * First it checks that the SMS message has XABA as substring.
     * Secondary it checks for localizaed sub string "verification code".
     * Then it uses regular expression to find the verification code itself.
     *
     * @param smsMessage The SMS message body to be parsed
     * @return The parsed XABA verification code. Returns null if the code can not be parsed.
     */
    public String parseSMSVerificationCode(String smsMessage) {

        if (localizedVerificationCode == null) {
            localizedVerificationCode = XabaApplication.getInstance().getString(R.string.verification_code);
        }
        if (localizedVerificationCode == null) {
            localizedVerificationCode = "";
        }

        if (localizedActivationCode == null) {
            localizedActivationCode = XabaApplication.getInstance().getString(R.string.sms_activation_code);
        }
        if (localizedActivationCode == null) {
            localizedActivationCode = "";
        }

        if (smsMessage == null || !smsMessage.toLowerCase().contains("xaba")) {
            return null;
        }

        if (!(smsMessage.toLowerCase().contains(localizedVerificationCode.toLowerCase()) || smsMessage.toLowerCase().contains(localizedActivationCode.toLowerCase()))) {
            return null;
        }

        try {
            Pattern pattern = Pattern.compile("( +)is ([A-Z0-9][A-Z0-9][A-Z0-9][A-Z0-9]+)([\\. ]+)");
            Matcher matcher = pattern.matcher(smsMessage);
            if (matcher.find()) {
                String verificationCode = matcher.group(2);
                if (verificationCode != null && verificationCode.length() > 0) {
                    return verificationCode;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            Timber.d("parseSMSVerificationCode failed to match regular expression with exception : " + e.getLocalizedMessage());
        }

        return null;
    }
}
