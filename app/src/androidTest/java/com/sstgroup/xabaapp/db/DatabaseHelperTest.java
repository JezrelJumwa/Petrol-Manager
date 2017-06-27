package com.sstgroup.xabaapp.db;

import android.os.Handler;

import com.sstgroup.xabaapp.XabaApplication;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/27/17.
 */
public class DatabaseHelperTest {
    DatabaseHelper xabaDbHelper;

    @Before
    public void setUp() throws Exception {
        xabaDbHelper = DatabaseHelper.getInstance(XabaApplication.getInstance());
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void getProfessions_doesNotThrowException() throws Exception {
        try {
            xabaDbHelper.getProfessions("InvalidCategoryASDF", "InvalidIndustryASDF");
        } catch (Exception e) {
            Assert.fail("getProfessions throws exception on invalid category and industry names");
        }
    }

}