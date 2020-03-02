package com.skillbox.sw.controller;

import com.skillbox.sw.utils.IntegrationTest;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc(addFilters = false)
public class PlatformControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLanguages1() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/languages")
                .accept(MediaType.APPLICATION_JSON).param("language","an").param("offset","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(1)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data[0].id", Matchers.is(3)));
    }

    @Test
    public void getLanguages2() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/languages")
                .accept(MediaType.APPLICATION_JSON).param("language","an").param("offset","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(5)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void getLanguages3() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/languages")
                .accept(MediaType.APPLICATION_JSON).param("language","").param("itemPerPage","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.perPage", Matchers.is(2)))
                .andExpect(jsonPath("$.data.size()").value(2));
    }

    @Test
    public void getCities1() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/cities")
                .accept(MediaType.APPLICATION_JSON).param("countryId","1").param("city","o"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data[1].id", Matchers.is(2)));
    }

    @Test
    public void getCities2() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/cities")
                .accept(MediaType.APPLICATION_JSON).param("countryId","1").param("city","Mos").param("offset","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(5)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void getCities3() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/cities")
                .accept(MediaType.APPLICATION_JSON).param("countryId","1").param("city","i").param("itemPerPage","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.perPage", Matchers.is(2)))
                .andExpect(jsonPath("$.data.size()").value(2));
    }

    @Test
    public void getCountries1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/platform/countries")
                .accept(MediaType.APPLICATION_JSON).param("country","an"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data[2].id", Matchers.is(4)));
    }

    @Test
    public void getCountries2() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/platform/countries")
                .accept(MediaType.APPLICATION_JSON).param("country","a").param("offset","5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(5)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void getCountries3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/platform/countries")
                .accept(MediaType.APPLICATION_JSON).param("country","an"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", Matchers.is("done")))
                .andExpect(jsonPath("$.total", Matchers.is(4)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.perPage", Matchers.is(20)))
                .andExpect(jsonPath("$.data.size()").value(3));
    }
}
