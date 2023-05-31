package org.example.vapestore.controller;


import org.example.vapestore.model.Vape;
import org.example.vapestore.service.VapeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/VapeStore")
public class Controller<T> {
    private final VapeService vapeService;

    public Controller(VapeService service) {
        this.vapeService = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Vape> addNewVape(@RequestBody Vape vape) {
        HttpStatus status = HttpStatus.CREATED;
        HttpHeaders headers = new HttpHeaders();
        headers.add("ContentType", "text/plain");
        ResponseEntity<Vape> response = new ResponseEntity<>(vape, headers, status);
        if (!viewVapes().getBody().contains(vape))
            vapeService.addVape(vape);
        return null;
    }

    @RequestMapping(value = "/viewVapes")
    public ResponseEntity<List<Vape>> viewVapes() {
        if (vapeService.viewVapes().isEmpty()) {
            String message = "there is no vape in our shop. Sorry!!";
            ResponseEntity response = new ResponseEntity(message, HttpStatus.OK);
            return response;
        } else {
            ResponseEntity response = new ResponseEntity(vapeService.viewVapes(), HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/buyVape", method = RequestMethod.POST)
    public String buyVape(@RequestBody Data data) {
        if (vapeService.getVapeQuantityByName(data.getName()) == null) {
            return "we dont sell this kind of vapes but we will find it for u :)";
        } else if (vapeService.getVapeQuantityByName(data.getName().toString()) == 0) {
            return new String("there is no more of this one but we will call u as long as we have  :)");
        } else {
            vapeService.decrementVapeQuantity(data.getName().toString());
            return "Nice choice";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> update(@PathVariable Long id, Vape changedVape) {
        changedVape.setVapeId(id);
        vapeService.addVape(changedVape);
        return ResponseEntity.ok("Vape details updated successfully");
    }

    @RequestMapping(value = "/deleteVape/{id}", method = RequestMethod.POST)
    public String deleteVape(@PathVariable Long id) {
        if (vapeService.findVapeById(id)) {
            vapeService.deleteVapeById(id);
            return "deletion completed";
        } else {
            return "there is no vape with that id, sorry";
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Vape>> searchVapes(@RequestBody(required = false) Data data) {
        List<Vape> searchedVapes = vapeService.searchingMechanism(data.getName(), data.getMinPrice(), data.getMaxPrice());
        return ResponseEntity.ok(searchedVapes);
    }
}


class Data {
    private String name;
    private Double minPrice;
    private Double maxPrice;

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}