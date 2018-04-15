package com.toh.tourofheroes.service;

import com.toh.tourofheroes.model.Hero;
import com.toh.tourofheroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    public Hero getHero(String id) {
        Optional<Hero> hero = Optional.empty();
        try {
            hero = heroRepository.findById(Long.valueOf(id));
        } catch (NumberFormatException e) {
            System.out.println("Id '" + id + "' with invalid format.");
        }
        return hero.orElse(null);
    }

    public List<Hero> getHeroesWithTermInName(String term) {
        return heroRepository.findByNameContainingIgnoreCase(term);
    }

    public Hero addHero(Hero hero) {

        // TODO: hero to be validated

        return heroRepository.save(hero);
    }

    public Hero updateHero (Hero hero) {

        // TODO: hero to be validated

        Hero updatedHero = null;
        Optional<Hero> heroToBeUpdated = heroRepository.findById(hero.getId());

        if (heroToBeUpdated.isPresent()) {
            hero.setId(heroToBeUpdated.get().getId());
            updatedHero = heroRepository.save(hero);
        }

        return updatedHero;
    }

    public Hero deleteHero(String id) {

        Optional<Hero> heroToBeDeleted;

        try {
            heroToBeDeleted = heroRepository.findById(Long.valueOf(id));
        } catch (NumberFormatException e) {
            return null;
        }

        if (!heroToBeDeleted.isPresent()) {
            return null;
        }

        heroRepository.delete(Long.valueOf(id));
        return heroToBeDeleted.get();
    }

}
