package com.w2m.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.w2m.model.Heroe;
import com.w2m.repository.IHeroesRepository;
import com.w2m.service.IHeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroesServiceImpl implements IHeroesService {

    private final IHeroesRepository heroesRepository;

    /*
        I will cache all heroes and assume that they are less than 1k in order to improve performance.
        In this particular project, we use h2 so the performance will almost no vary.
     LoadingCache<String, Heroe> heroesCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, Heroe>() {
                        @Override
                        public Heroe load(String key) {
                            return createExpensiveGraph(key);
                        }
                    });
     */

    @Autowired
    public HeroesServiceImpl(IHeroesRepository repository) {
        this.heroesRepository = repository;
    }

    @Override
    public List<Heroe> findAll(String name) {
        List<Heroe> allHeroes = heroesRepository.findAll();

        if(!"".equals(name)) {
            return allHeroes.stream().filter(heroe -> heroe.getName() != null &&
                    heroe.getName().toLowerCase().contains(name)).collect(Collectors.toList());
        }

        return allHeroes;
    }

    @Override
    public Optional<Heroe> findById(long id) {
        Heroe heroe = heroesRepository.findById(id);
        return Optional.of(heroe);
    }

    @Override
    public Heroe createNewHeroe(Heroe heroe) {
        return heroesRepository.insert(heroe);
    }

    @Override
    public Heroe updateHeroe(Heroe heroe) {
        return heroesRepository.update(heroe);
    }

    @Override
    public void deleteHeroe(long id) {
        heroesRepository.deleteById(id);
    }
}
