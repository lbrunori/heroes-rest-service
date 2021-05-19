package com.w2m.controller;

import com.w2m.model.Heroe;
import com.w2m.service.IHeroesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HeroesControllerTest {

    @Autowired
    private HeroesController heroesController;

    @MockBean
    private IHeroesService heroesService;

    @Test
    public void testHeroesContrllerNotNull() {
        assertNotNull(heroesController);
    }

    @Test void testGetAllHeroesReturnsSuccessfully() {
        Heroe heroMock1 = Mockito.mock(Heroe.class);
        when(heroesService.findAll(anyString())).thenReturn(Arrays.asList(heroMock1, heroMock1));

        ResponseEntity<List<Heroe>> allHeroesResponse = heroesController.getAllHeroes("");
        assertEquals(200, allHeroesResponse.getStatusCodeValue());
        assertEquals(2, Objects.requireNonNull(allHeroesResponse.getBody()).size());
    }
}
