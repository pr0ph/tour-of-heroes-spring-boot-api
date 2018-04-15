package com.toh.tourofheroes.repository;

import com.toh.tourofheroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    List<Hero> findAll();

    Optional<Hero> findById(Long id);

    List<Hero> findByNameContainingIgnoreCase(String term);

}
