package com.sstgroup.xabaapp.listener;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.core.ReflectUtils;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/28/17.
 */
public class SMSReceiverTest {

    private SMSReceiver smsReceiver;

    @Before
    public void setUp() throws Exception {
        smsReceiver = new SMSReceiver();
        smsReceiver.setLocalizedVerificationCode("verification code");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parseSMSVerificationCode_parsesSMSCorrectly() {
        String smsMessage = "Dear Sir, your verification code for account in XABA is BC995EA8. Dial *884# to login and manage your account.";
        String sms = smsReceiver.parseSMSVerificationCode(smsMessage);
        Assert.assertNotNull("The parsed SMS message is null", sms);
        Assert.assertEquals("The parsed SMS mesage \"" + sms + "\" is NOT equal to the expected message BC995EA8.", "BC995EA8", sms);
    }

    @Test
    public void parseSMSVerificationCode_doesNotTrowExceptionOnNullMessage() {
        String smsMessage = null;
        try {
            smsReceiver.parseSMSVerificationCode(smsMessage);
        } catch (Exception e) {
            Assert.fail("If the smsMessage is null parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_doesNotTrowExceptionOnInvalidFormattedMessage() {
        String smsMessage = "asdfadsfkjasl;dgkj34wjnr$%%#@$$#!!";
        try {
            smsReceiver.parseSMSVerificationCode(smsMessage);
        } catch (Exception e) {
            Assert.fail("If the smsMessage is invalid format parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_returnsNULLIfThereIsNoXaba() {
        String smsMessage = "Dear Sir, your verification code for account in is BC995EA8. Dial *884# to login and manage your account.";
        try {
            String sms = smsReceiver.parseSMSVerificationCode(smsMessage);

            Assert.assertNull("The parsed SMS message is not null, even there is no XABA substring", sms);
        } catch (Exception e) {
            Assert.fail("If the smsMessage has not XABA substring parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_returnsNULLIfNumberIsInvalid() {
        String smsMessage = "Dear Sir, your verification code for account in XABA is $$BC99=!EA8. Dial *884# to login and manage your account.";
        try {
            String sms = smsReceiver.parseSMSVerificationCode(smsMessage);

            Assert.assertNull("The parsed SMS message is not null, even the number is not valid xaba verification code", sms);
        } catch (Exception e) {
            Assert.fail("If the verification code is not in valid format, parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_returnsNULLIfNumberHasLowerCaseCharacters() {
        String smsMessage = "Dear Sir, your verification code for account in XABA is BC995ea8. Dial *884# to login and manage your account.";
        try {
            String sms = smsReceiver.parseSMSVerificationCode(smsMessage);

            Assert.assertNull("The parsed SMS message is not null, even the number is not valid xaba verification code", sms);
        } catch (Exception e) {
            Assert.fail("If the verification code is not in valid format, parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_returnsNULLIfXABASubstringHasLowerCaseCharacters() {
        String smsMessage = "Dear Sir, your verification code for account in XaBA is BC995EA8. Dial *884# to login and manage your account.";
        try {
            String sms = smsReceiver.parseSMSVerificationCode(smsMessage);

            Assert.assertNull("The parsed SMS message is not null, even the XaBA substring contains lowercase characters", sms);
        } catch (Exception e) {
            Assert.fail("If XaBA substring has lower case characters, parseSMSVerificationCode throws exception : " + e);
        }
    }

    @Test
    public void parseSMSVerificationCode_returnsNULLIfXABAVerificationCodeIsNull() {
        String smsMessage = "Dear Sir, your for account in XABA is BC995EA8. Dial *884# to login and manage your account.";
        try {
            String sms = smsReceiver.parseSMSVerificationCode(smsMessage);

            Assert.assertNull("The parsed SMS message is not null, even there is no substring 'verification code'", sms);
        } catch (Exception e) {
            Assert.fail("If the tehre is no substring 'verification code', parseSMSVerificationCode throws exception : " + e);
        }
    }

}