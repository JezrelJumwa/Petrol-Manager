package com.sstgroup.xabaapp.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/30/17.
 */
public class ProfessionResponseTest {

    private Gson gson;
    private ProfessionResponse professionResponse;

    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().create();

        URL professions = this.getClass().getClassLoader().getResource("professions.json");
        professionResponse = gson.fromJson(new FileReader(professions.getPath()), ProfessionResponse.class);
    }

    @Test
    public void testProfessionResponseIsParsed() throws Exception {
        Assert.assertNotNull("Profession response is not parsed", professionResponse);
    }

    @Test
    public void getStatusEqualsTheExpectedValue() throws Exception {
        Assert.assertEquals("Status is not parsed as expected.", "OK", professionResponse.getStatus());
    }

    @Test
    public void getProfessionStructureIsParsed() throws Exception {
        Assert.assertNotNull("Profession response structure is not parsed.", professionResponse.getProfessionStructure());
    }

    @Test
    public void programsAreParsed() throws Exception {
        Assert.assertNotNull("Programs are not parsed in the profession response", professionResponse.getProfessionStructure().getPrograms());
    }

    @Test
    public void programsCountEquealsTheExpected() throws Exception {
        Assert.assertEquals("Programs parsed does not match the expected count", 15, professionResponse.getProfessionStructure().getPrograms().size());
    }
}