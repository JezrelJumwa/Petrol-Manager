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
public class LocationResponseTest {

    private Gson gson;
    private LocationResponse locationResponse;

    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().create();

        URL locations = this.getClass().getClassLoader().getResource("locations.json");
        locationResponse = gson.fromJson(new FileReader(locations.getPath()), LocationResponse.class);
    }

    @Test
    public void testLocationResponseIsParsed() {
        Assert.assertNotNull("Locations response is not parsed", locationResponse);
    }

    @Test
    public void getStatusEqualsTheExpectedValue() throws Exception {
        Assert.assertEquals("Status is not parsed as expected.", "OK", locationResponse.getStatus());
    }

    @Test
    public void getLocationStructureIsParsed() throws Exception {
        Assert.assertNotNull("Location response structure is not parsed.", locationResponse.getLocationStructure());

    }

}