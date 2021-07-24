package dev.liam.postcodesandnames.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PostcodesAndNamesApplication.class)
@AutoConfigureMockMvc
public class PostcodeRequestHandlerTest {

    @Autowired
    private MockMvc mvc;

    private static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenNoPostcodeNames_whenGetName_thenStatus200WithEmptyResponse() {
        try {
            PostcodeName[] testPostcodeNames = {
                    new PostcodeName(1, 1, "Bonnie"),
                    new PostcodeName(2, 2, "Clyde")
            };

            mvc.perform(post("/postcode")
                    .content(toJson(testPostcodeNames))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
