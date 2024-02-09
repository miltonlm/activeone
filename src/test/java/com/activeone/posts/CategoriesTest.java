package com.activeone.posts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriesTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init() {

    }

    @Test
    public void categories200() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
