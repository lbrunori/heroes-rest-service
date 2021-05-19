package com.w2m.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.w2m.model.Heroe;
import com.w2m.service.IHeroesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroesServiceImpl implements IHeroesService {

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

    @Override
    public List<Heroe> findAll(String name) {
        return null;
    }

    @Override
    public Optional<Heroe> findById(long id) {
        return Optional.empty();
    }
    @Override
    public Heroe updateHeroe(Heroe heroe) {
        return null;
    }

    @Override
    public void deleteHeroe(long id) {

    }
}
