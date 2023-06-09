package org.example.vapestore.service;
import org.example.vapestore.repository.VapeRepository;
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
        Integer q=0;
        q= vape.getQuantity();
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
        List<Vape> vapes = new ArrayList<>();
        repository.findAll().forEach(vapes::add);
        List<Vape>searchedVapes = vapes.stream()
                .filter(vape -> {
                    boolean nameMatch = name == null || name.equals(vape.getName());
                    boolean priceMatch = (minPrice == null || vape.getPrice() >= minPrice)
                            && (maxPrice == null || vape.getPrice() <= maxPrice);
                    return nameMatch && priceMatch;
                })
                .collect(Collectors.toList());

        return searchedVapes;
    }
}
