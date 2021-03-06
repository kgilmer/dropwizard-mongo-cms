package com.eeb.dropwizardmongo.cms.test;

import com.eeb.dropwizardmongo.cms.api.Author;
import com.eeb.dropwizardmongo.cms.api.BasicDetail;
import com.eeb.dropwizardmongo.cms.api.BasicPage;
import com.eeb.dropwizardmongo.cms.api.Metadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by eeb on 7/2/14.
 */
public class APITests {

    final private ObjectMapper m = new ObjectMapper();

    @Test
    public void AuthorTest() throws JsonProcessingException {

        Author a = new Author();
        a.setId("1");
        a.setName("Tom");

        String json = m.writeValueAsString(a);
        assert json.equals("{\"_id\":\"1\",\"name\":\"Tom\"}") : "Author JSON does not match expectation.";


    }

    @Test
    public void basicDetailTest() throws JsonProcessingException {

        String expected = "{\"text\":\"asdf\"}";
        BasicDetail bd = new BasicDetail();
        bd.setText("asdf");

        assert m.writeValueAsString(bd).equals(expected) : "BasicDetail JSON does not match expectation";



    }

    @Test
    public void metaDataTest() throws JsonProcessingException {

        BasicPage bp = new Mocks.MockBasicPage();
        bp.getMetadata().getAuthor().setId("1");
        bp.setId("1");
        bp.setNonce("2");


        assert m.writeValueAsString(bp).equals("{\"_id\":\"1\",\"nonce\":\"2\",\"metadata\":{\"type\":\"basic-page\",\"section\":\"my-photos\",\"slug\":\"about\",\"title\":\"About Us\",\"created\":null,\"author\":{\"_id\":\"1\",\"name\":\"Tom\"},\"tags\":[\"a\",\"b\"],\"detail\":{\"text\":\"asdf\"}}}")
                : "BasicPage JSON does not equal expectation.";


    }



}
