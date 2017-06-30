package com.sstgroup.xabaapp.db;

import android.os.Handler;

import com.sstgroup.xabaapp.XabaApplication;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

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
    public void getCategories_doesNotThrowException() throws Exception {
        try {
            xabaDbHelper.getCategories("InvalidIndustryASDF");
        } catch (Exception e) {
            Assert.fail("getCategories throws exception on invalid industry name");
        }
    }

    @Test
    public void getProfessions_doesNotThrowException() throws Exception {
        try {
            xabaDbHelper.getProfessions("InvalidCategoryASDF", "InvalidIndustryASDF");
        } catch (Exception e) {
            Assert.fail("getProfessions throws exception on invalid category and industry names");
        }
    }

    @Test
    public void getProgramIds_doesNotThrowException() throws Exception {
        try {
            ArrayList<String> invalidPrograms = new ArrayList<>();
            invalidPrograms.add("invalidProgramASDF");
            xabaDbHelper.getProgramIds(invalidPrograms);
        } catch (Exception e) {
            Assert.fail("getProgramIds throws exception on invalid program name");
        }

        try {
            xabaDbHelper.getProgramIds(new ArrayList<String>());
        } catch (Exception e) {
            Assert.fail("getProgramIds throws exception on empty program name list");
        }
    }

    @Test
    public void getProgramIds_returnsTheSameNumberOfIdsAsNamesProvided() throws Exception {
        List<String> programs = xabaDbHelper.getPrograms();
        List<Long> ids =  xabaDbHelper.getProgramIds(programs);
        if (ids == null) {
            if (programs != null && programs.size() > 0) {
                Assert.fail("getProgramIds returns null for non empty program name list.");
            }
        } else {
            Assert.assertEquals("getProgramIds returns different count of ids than expected.", programs.size(), ids.size());
        }
    }
}