package com.w2m.controller;

import com.w2m.model.Heroe;
import com.w2m.service.IHeroesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController("/heroes")
public class HeroesController {

    private final IHeroesService heroesService;

    @Autowired
    public HeroesController(IHeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping
    public ResponseEntity<List<Heroe>> getAllHeroes(
            @RequestParam(defaultValue = "", required = false, name = "name") String name) {

        List<Heroe> heroes = heroesService.findAll(name);

        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroe> getHeroe(@PathVariable("id") String id) {
        return ResponseEntity.ok(new Heroe());
    }

    @PostMapping
    public ResponseEntity<Heroe> postHeroe(@RequestBody @Valid Heroe heroe) {
        return ResponseEntity.ok(new Heroe());
    }

    @PutMapping
    public ResponseEntity<Heroe> putHeroe(@RequestBody @Valid Heroe heroe) {
        return ResponseEntity.ok(new Heroe());
    }

    @DeleteMapping
    public ResponseEntity deleteHeroe(@RequestBody @Valid String id) {
        return ResponseEntity.ok().build();
    }
}
