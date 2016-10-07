package com.charlires.junittest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    @DataProvider(format = "/users - a: %p[0],  b: %p[1], expected: %p[2]")
    public static Object[][] data() {
        return new Object[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
        };
    }

    @DataProvider(format = "/get - a: %p[0], expected: %p[1]")
    public static Object[][] dataRest() {
        // input, expected
        return new Object[][]{
                {"carlos", "carlos"},
                {"jose", "jose"},
                {"luis", "luis"},
                {"david", "david"},
                {"diego", "diego"},
        };
    }

    @Test
    @UseDataProvider("data")
    public void testAddNumbers(int a, int b, int expected) {
        assertEquals((a + b), expected);
    }

    @Test
    @UseDataProvider("dataRest")
    public void testGet(String name, String expected) throws JSONException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/get")
                .queryString("name", name).asJson();
        assertEquals(response.getBody().getObject().getJSONObject("args").getString("name"), expected);
    }

    @Test
    public void testGetRoot() throws JSONException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/get").asJson();
        assertEquals(response.getStatus(), 200);
        assertThat("Response status code", response.getStatus(), is(200));
    }

}

