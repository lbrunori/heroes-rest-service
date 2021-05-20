package com.w2m.controller;

import com.w2m.model.Heroe;
import com.w2m.service.IHeroesService;
import com.w2m.service.impl.HeroesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class HeroesControllerTest {

    private HeroesController heroesController;
    private IHeroesService heroesService;

    @BeforeEach
    public void beforeEach() {
        heroesService = Mockito.mock(HeroesServiceImpl.class);
        heroesController = new HeroesController(heroesService);
    }

    @Test
    public void testHeroesContrllerNotNull() {
        assertNotNull(heroesController);
    }

    @Test
    public void testGetAllHeroesReturnsSuccessfully() {
        Heroe heroMock1 = Mockito.mock(Heroe.class);
        when(heroesService.findAll(anyString())).thenReturn(Arrays.asList(heroMock1, heroMock1));

        ResponseEntity<HeroesController.HeroesListResponse> allHeroesResponse = heroesController.getAllHeroes("");
        verify(heroesService,times(1)).findAll(anyString());
        assertEquals(200, allHeroesResponse.getStatusCodeValue());
        assertEquals(2, Objects.requireNonNull(allHeroesResponse.getBody()).getElements().size());
    }

    @Test
    public void testGetHeroeByIdReturnsSuccessfully() {
        Heroe heroMock1 = Mockito.mock(Heroe.class);
        when(heroesService.findById(anyLong())).thenReturn(Optional.of(heroMock1));

        ResponseEntity<Heroe> heroeResponse = heroesController.getHeroe(1L);
        verify(heroesService,times(1)).findById(anyLong());
        assertEquals(200, heroeResponse.getStatusCodeValue());
        assertNotNull(Objects.requireNonNull(heroeResponse.getBody()));
    }

    @Test
    public void testCreateHeroeReturnsSuccessfully() {
        Heroe heroMock1 = Mockito.mock(Heroe.class);
        when(heroesService.createNewHeroe(any(Heroe.class))).thenReturn(heroMock1);

        ResponseEntity<Heroe> allHeroesResponse = heroesController.postHeroe(heroMock1);
        assertEquals(200, allHeroesResponse.getStatusCodeValue());
        verify(heroesService,times(1)).createNewHeroe(any(Heroe.class));

    }

    @Test
    public void testUpdateHeroeAndReturnsSuccessfully() {
        Heroe dummyHeroe = new Heroe();
        when(heroesService.updateHeroe(any(Heroe.class))).thenReturn(dummyHeroe);

        ResponseEntity<Heroe> allHeroesResponse = heroesController.putHeroe(dummyHeroe);
        assertEquals(200, allHeroesResponse.getStatusCodeValue());
        verify(heroesService,times(1)).updateHeroe(any(Heroe.class));

    }

    @Test
    public void testDeleteHeroeByIdReturnsSuccessfully() {
        Heroe dummyHeroe = new Heroe();
        try {
            heroesController.deleteHeroe(1L);
        } catch (Exception e) {
            fail();
        }
    }
}
