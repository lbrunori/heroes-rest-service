package com.w2m.service;

import com.w2m.model.Heroe;

import java.util.List;
import java.util.Optional;

public interface IHeroesService {

    List<Heroe> findAll(String name);

    Optional<Heroe> findById(long id);

    Heroe createNewHeroe(Heroe e);

    Heroe updateHeroe(Heroe heroe);

    void deleteHeroe(long id);
}
