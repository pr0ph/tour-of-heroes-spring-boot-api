package com.toh.tourofheroes.api;

import com.toh.tourofheroes.model.Hero;
import com.toh.tourofheroes.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroRestController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(value = "/api/heroes")
    public List<Hero> getHeroes(@RequestParam(value = "name", required = false) String term) {

        if (term != null) {
            System.out.println(String.format("getHeroes(\"%s\") requested", term));
            return heroService.getHeroesWithTermInName(term);
        }

        System.out.println("getHeroes() requested");
        return heroService.getHeroes();
    }

    @RequestMapping(value = "/api/heroes/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable(value = "id") String id) {

        System.out.println(String.format("getHero(%s) requested", id));
        Hero hero = heroService.getHero(id);

        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes", method = RequestMethod.POST)
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {

        System.out.println("addHero() requested");

        Hero addedHero = heroService.addHero(hero);
        if (addedHero == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(addedHero, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes", method = RequestMethod.PUT)
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {

        System.out.println("updateHero() requested");

        Hero updatedHero = heroService.updateHero(hero);
        if (updatedHero == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(updatedHero, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Hero> deleteHero(@PathVariable(value = "id") String id) {

        System.out.println(String.format("deleteHero(%s) requested", id));

        Hero deletedHero = heroService.deleteHero(id);

        if (deletedHero == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(deletedHero, HttpStatus.OK);
    }

}
