package com.w2m.repository;

import com.w2m.model.Heroe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HeroesRepositoryTest {

    @Autowired
    private IHeroesRepository repository;

    @Test
    public void testHeroesRepositoryNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void testFindAllSuccessfully() {
        assertEquals(5, repository.findAll().size());
    }

    @Test
    public void testFindByIdSuccessfully() {
        assertNotNull(repository.findById(1L));
    }

    @Test
    public void testUpdateSuccessfully() {
        Heroe heroe = new Heroe(1L, "Batman 1");
        Heroe updatedBatman = repository.update(heroe);
        assertEquals("Batman 1", updatedBatman.getName());
    }

    @Test
    public void testInsertAndDeleteSuccessfully() {
        Heroe heroe = new Heroe(1L, "Batman 1");
        Heroe insertedHeroe = repository.insert(heroe);

        repository.deleteById(heroe.getId());
        List<Heroe> all = repository.findAll();

        assertNotNull(insertedHeroe);
        Optional<Heroe> batman = all.stream().filter(hr -> "Batman 1".equals(hr.getName())).findFirst();
        assertTrue(batman.isEmpty());
    }

}
