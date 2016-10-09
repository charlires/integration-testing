package com.charlires.junittest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    private static Properties prop = new Properties();

    @DataProvider(format = "/users - a: %p[0],  b: %p[1], expected: %p[2]")
    public static Object[][] data() {
        return new Object[][]{
                {1, 2, 3},
                {2, 2, 4},
                {3, 2, 5},
                {4, 2, 6},
                {5, 2, 7},
        };
    }

    @DataProvider(format = "/get - a: %p[0], expected: %p[1]")
    public static Object[][] dataRest() {
        return new Object[][]{
                {"carlos", "carlos"},
                {"jose", "jose"},
                {"luis", "luis"},
                {"david", "david"},
                {"diego", "diego"},
        };
    }

    @BeforeClass
    public static void init()  {
        try {
            InputStream input = FileInputStream.class.getResourceAsStream("/env.properties");
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @UseDataProvider("data")
    public void testAddNumbers(int a, int b, int expected) {
        assertEquals((a + b), expected);
    }

    @Test
    @UseDataProvider("dataRest")
    public void testGet(String name, String expected) throws JSONException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(prop.getProperty("host") + "/get")
                .queryString("name", name)
                .asJson();
        assertEquals(response.getBody().getObject().getJSONObject("args").getString("name"), expected);
    }

    @Test
    public void testGetRoot() throws JSONException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(prop.getProperty("host") + "/status/{code}")
                .routeParam("code", "300")
                .asJson();
        assertEquals(response.getStatus(), 300);
        assertThat("Response status code", response.getStatus(), is(300));
    }

}
