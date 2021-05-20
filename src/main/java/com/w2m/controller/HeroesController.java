package com.w2m.controller;

import com.w2m.annotation.Timed;
import com.w2m.exception.BadRequestException;
import com.w2m.exception.NotFoundException;
import com.w2m.model.Heroe;
import com.w2m.service.IHeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/heroes")
public class HeroesController {

    private final IHeroesService heroesService;

    @Autowired
    public HeroesController(IHeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping
    @Timed
    @Cacheable(value = "heroes")
    public ResponseEntity<List<Heroe>> getAllHeroes(
            @RequestParam(defaultValue = "", required = false, name = "name") String name) {

        List<Heroe> heroes = heroesService.findAll(name);

        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    @Timed
    @Cacheable(value = "heroes")
    public ResponseEntity<Heroe> getHeroe(@PathVariable("id") long id) {
        validateIdParam(id);

        Optional<Heroe> heroeOptional = heroesService.findById(id);

        if (heroeOptional.isEmpty()) {
            throw new NotFoundException("heroe_not_found", "No heroe found with id: " + id);
        }

        return ResponseEntity.ok(heroeOptional.get());
    }

    @PostMapping
    @Timed
    public ResponseEntity<Heroe> postHeroe(@RequestBody @Valid Heroe heroe) {
        Heroe newHeroe = heroesService.createNewHeroe(heroe);

        return ResponseEntity.ok(newHeroe);
    }

    @PutMapping
    @Timed
    public ResponseEntity<Heroe> putHeroe(@RequestBody @Valid Heroe heroeParam) {
        Heroe heroe = heroesService.updateHeroe(heroeParam);

        return ResponseEntity.ok(heroe);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity deleteHeroe(@PathVariable("id") long id) {
        validateIdParam(id);
        heroesService.deleteHeroe(id);
        return ResponseEntity.noContent().build();
    }

    private void validateIdParam(long id) {
        if(id < 0) {
            throw new BadRequestException("heroe_id_invalid", "Invalid value for ID property");
        }
    }
}
