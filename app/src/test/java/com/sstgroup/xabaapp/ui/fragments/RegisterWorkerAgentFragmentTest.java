package com.sstgroup.xabaapp.ui.fragments;

import com.sstgroup.xabaapp.utils.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/23/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterWorkerAgentFragmentTest {

    RegisterWorkerAgentFragment fragment;

    @Before
    public void setUp() throws Exception {
        fragment = new RegisterWorkerAgentFragment();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void validateAndGetRegisterWorkerRequestModel_doesNotCrashOnEmptyRefferalId() throws Exception {
        try {
            fragment.getRegisterWorkerRequestModelWithInfo(new RegisterWorkerAgentFragment.RegistrationInfo("77777777", "77777777", "+254771161777", "0000", "0000", "", new ArrayList<Long>(), new ArrayList<Long>(), null));
        } catch (Exception e) {
            e.printStackTrace();
            fail("validateAndGetRegisterWorkerRequestModel should not throw exception: " + e.getLocalizedMessage());
        }
    }

}