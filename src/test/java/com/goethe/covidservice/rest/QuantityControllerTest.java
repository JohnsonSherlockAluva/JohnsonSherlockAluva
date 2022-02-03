package com.goethe.covidservice.rest;

import com.goethe.covidservice.model.Quantity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class QuantityControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void checkStatusOkTest() throws Exception {
        String uri = "/covid/stateName";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void checkStatus404Test() throws Exception {
        String uri = "/covid/stateNames";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);

    }

    @Test
    public void responseNotEmptyTest() throws Exception {
        String uri = "/covid/stateName";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Quantity[] quantities = super.mapFromJson(content, Quantity[].class);
        Assert.assertTrue(quantities.length > 0);
    }

    @Test
    public void stateNameTest() throws Exception {
        String uri = "/covid/stateName/Jena";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Quantity quantities = super.mapFromJson(content, Quantity.class);
        Assert.assertEquals(quantities.getStateName(), "Jena");

    }

}
