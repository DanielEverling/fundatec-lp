package com.fundatec.veterinaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundatec.veterinaria.domain.Cachorro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CachorroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retornaTodosCachorrosIsOk() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*@Test
    public void retornaCachorroByNomeIsOk() throws Exception {
        Cachorro cachorro = new Cachorro("Lili", 2, "SRD", 3);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cachorro);

        mockMvc
                .perform(
                    MockMvcRequestBuilders.post("/cachorros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/cachorros/{nome}", "Lili"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Lili"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.raca").value("SRD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.peso").value(3));
    }*/
}
