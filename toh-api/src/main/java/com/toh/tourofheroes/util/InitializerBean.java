package com.toh.tourofheroes.util;

import com.toh.tourofheroes.model.Hero;
import com.toh.tourofheroes.repository.HeroRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(HeroRepository heroRepository) {
        heroRepository.save(new Hero("Mr. Nice"));
        heroRepository.save(new Hero("Narco"));
        heroRepository.save(new Hero("Bombasto"));
        heroRepository.save(new Hero("Celeritas"));
        heroRepository.save(new Hero("Magneta"));
        heroRepository.save(new Hero("RubberMan"));
        heroRepository.save(new Hero("Dynama"));
        heroRepository.save(new Hero("Magma"));
        heroRepository.save(new Hero("Tornado"));
    }

}
