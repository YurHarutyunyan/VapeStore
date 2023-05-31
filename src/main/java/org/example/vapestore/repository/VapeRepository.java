package org.example.vapestore.repository;

import org.example.vapestore.model.Vape;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EntityScan("org.example.model")
@Repository
public interface VapeRepository extends CrudRepository<Vape, Long> {
    Vape findByName(String name);

    Vape findVapeByName(String name);
    List<Vape> findVapesByName(String name);

}
