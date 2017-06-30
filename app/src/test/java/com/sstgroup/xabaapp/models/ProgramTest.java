package com.sstgroup.xabaapp.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/30/17.
 */
public class ProgramTest {

    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().create();
    }

    @Test
    public void testProgramPrasesWithGSON() {
        Program program = gson.fromJson("{\"program_id\": 1,\"name\": \"NCA\",\"status\": \"active\"}", Program.class);
        Assert.assertEquals("Parsed program does not matches the expected name", "NCA", program.name);
        Assert.assertEquals("Parsed program does not matches the expected status", Program.STATUS_ACTIVE, program.status);
        Assert.assertEquals("Parsed program does not matches the expected program ID", (long)1L, (long)program.programId);
    }

}