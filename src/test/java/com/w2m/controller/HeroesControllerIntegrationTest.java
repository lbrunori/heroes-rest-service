package com.w2m.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HeroesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHeroeWithInvalidId() throws Exception {
        this.mockMvc.perform(get("/api/heroes/-1")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("heroe_id_invalid")));
    }

    @Test
    public void testGetHeroeWithRequestNotFoundId() throws Exception {
        this.mockMvc.perform(get("/api/heroes/11232")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("heroe_not_found")));
    }

    @Test
    public void testGetAllHeroes() throws Exception {
        this.mockMvc.perform(get("/api/heroes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"elements\":[{\"id\":1,\"name\":\"Batman\"},{\"id\":2,\"name\":\"Superman\"},{\"id\":3,\"name\":\"Flash\"},{\"id\":4,\"name\":\"Wonder Woman\"},{\"id\":5,\"name\":\"Cyborg\"}]}"));
    }

    @Test
    public void testGetBatmanHeroes() throws Exception {
        this.mockMvc.perform(get("/api/heroes/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Batman\"}"));
    }

    @Test
    public void testGetAllHeroesWithMan() throws Exception {
        this.mockMvc.perform(get("/api/heroes").queryParam("name", "man")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"elements\":[{\"id\":1,\"name\":\"Batman\"},{\"id\":2,\"name\":\"Superman\"},{\"id\":4,\"name\":\"Wonder Woman\"}]}"));
    }

    @Test
    public void testPostUnauthorized() throws Exception {
        this.mockMvc.perform(post("/api/heroes")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testPostNewHeroeAndDeleteIt() throws Exception {
        this.mockMvc.perform(post("/api/heroes").header("content-type", "application/json")
                .content("{\"id\":6,\"name\":\"Martin Hunter\"}")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":6,\"name\":\"Martin Hunter\"}"));

        this.mockMvc.perform(delete("/api/heroes/6")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testPutNewHeroeAndDeleteIt() throws Exception {
        this.mockMvc.perform(put("/api/heroes").header("content-type", "application/json")
                .content("{\"id\":3,\"name\":\"Flashhhh\"}")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"name\":\"Flashhhh\"}"));
    }
}
