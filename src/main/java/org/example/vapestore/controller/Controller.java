package org.example.vapestore.controller;


import org.example.vapestore.model.Vape;
import org.example.vapestore.service.VapeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/VapeStore")
public class Controller {
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
        vapeService.addVape(vape);
        return response;
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
}

class Data {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}