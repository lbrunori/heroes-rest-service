package com.w2m.service;

import com.w2m.model.Heroe;
import com.w2m.repository.IHeroesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HeroesServiceImplTest {

    @Autowired
    private IHeroesService heroesService;

    @MockBean
    private IHeroesRepository repository;

    private static final List<Heroe> heroes = new LinkedList<>();

    @BeforeAll
    public static void beforeAll() {
        Heroe supermanMock = new Heroe(1L, "Superman");
        Heroe batmanMock = new Heroe(2L, "Batman");

        heroes.add(supermanMock);
        heroes.add(batmanMock);
    }

    @Test
    public void testHeroesServiceNotNull() {
        assertNotNull(heroesService);
    }

    @Test
    public void testFindAllWithEmptyStringSuccessfully() {
        when(repository.findAll()).thenReturn(heroes);

        List<Heroe> all = heroesService.findAll("");
        assertEquals(2, all.size());
    }

    @Test
    public void testFindAllWithNameQueryStringSuccessfully() {
        when(repository.findAll()).thenReturn(heroes);

        List<Heroe> all = heroesService.findAll("super");
        assertEquals(1, all.size());
    }

    @Test
    public void testCreateNewHeroeSuccessfully() {
        when(repository.insert(any(Heroe.class))).thenReturn(heroes.get(0));

        Heroe newHeroe = heroesService.createNewHeroe(heroes.get(0));
        assertEquals(heroes.get(0).getName(), newHeroe.getName());
    }

    @Test
    public void testUpdateHeroeSuccessfully() {
        Heroe heroe = new Heroe(3L, "Wonder Woman");
        when(repository.update(any(Heroe.class))).thenReturn(heroe);

        Heroe updatedHeroe = heroesService.updateHeroe(heroe);
        assertEquals("Wonder Woman", updatedHeroe.getName());
    }

    @Test
    public void testDeleteHeroeSuccessfully() {
        try {
            heroesService.deleteHeroe(1L);
        } catch (Exception e) {
            fail();
        }
    }
}
