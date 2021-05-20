package com.w2m.repository;

import com.w2m.model.Heroe;

import java.util.List;

public interface IHeroesRepository {

    List<Heroe> findAll();

    Heroe findById(long id);

    Heroe update(Heroe person);

    Heroe insert(Heroe person);

    void deleteById(long id);
}
