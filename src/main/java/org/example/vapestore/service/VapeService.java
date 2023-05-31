package org.example.vapestore.service;

import org.example.vapestore.model.Vape;
import org.example.vapestore.repository.VapeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VapeService implements VapeServiceInterface {
    VapeRepository repository;

    public VapeService(VapeRepository repo) {
        this.repository = repo;
    }

    @Override
    public Vape addVape(Vape vape) {
        return repository.save(vape);
    }

    public List<Vape> viewVapes() {
        List<Vape> vapes = new ArrayList<>();
        repository.findAll().forEach(vapes::add);
        return vapes;
    }

    @Override
    public Integer getVapeQuantityByName(String name) {
        Vape vape = repository.findVapeByName(name);
        Integer q = vape.getQuantity();
        return q;
    }

    public void decrementVapeQuantity(String name) {
        Vape vape = repository.findVapeByName(name);
        Integer quantity = repository.findVapeByName(name).getQuantity();
        quantity--;
        vape.setQuantity(quantity);
        repository.save(vape);
    }

    public Boolean deleteVapeById(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Boolean findVapeById(Long id) {
        return repository.existsById(id);
    }

    public List<Vape> searchingMechanism(String name, Double minPrice, Double maxPrice) {
        List<Vape> searchedVapes = new ArrayList<>();
        List<Vape> vapes = new ArrayList<>();
        repository.findAll().forEach(vapes::add);
        searchedVapes = vapes.stream()
                .filter(vape -> vape != null && vape.getPrice() >= minPrice && vape.getPrice() <= maxPrice && vape.getName().equals(name)
                ).collect
                        (Collectors.toList());
        return searchedVapes;
    }
}
