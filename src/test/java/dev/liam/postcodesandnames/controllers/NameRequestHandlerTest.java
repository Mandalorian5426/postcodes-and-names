package dev.liam.postcodesandnames.controllers;

import dev.liam.postcodesandnames.PostcodesAndNamesApplication;
import dev.liam.postcodesandnames.models.PostcodeName;
import dev.liam.postcodesandnames.repositories.PostcodeNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PostcodesAndNamesApplication.class)
@AutoConfigureMockMvc
public class NameRequestHandlerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostcodeNameRepository repository;

    @Test
    public void givenNoPostcodeNames_whenGetName_thenStatus200WithEmptyResponse() {
        try {
            mvc.perform(get("/name?start=6000&end=6999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalChars").value(0))
                .andExpect(jsonPath("$.names").isArray())
                .andExpect(jsonPath("$.names").isEmpty());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void givenNoRequestParameters_whenGetName_thenStatus400() {
        try {
            mvc.perform(get("/name")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void givenStartPostcodeGreaterThanEndPostcode_whenGetName_thenStatus400() {
        try {
            mvc.perform(get("/name?start=6999&end=6000")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void givenPostCodeName_whenGetName_thenStatus200WithNameAndTotalCharsReturned() {
        try {
            repository.save(new PostcodeName(1, 6500, "Steve"));

            mvc.perform(get("/name?start=6000&end=6999")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.totalChars").value(5))
                    .andExpect(jsonPath("$.names").isArray())
                    .andExpect(jsonPath("$.names[0]").value("Steve"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
