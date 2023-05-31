package org.example.vapestore.service;

import org.example.vapestore.model.Vape;

public interface VapeServiceInterface {
    public Vape addVape(Vape vape);

    public Integer getVapeQuantityByName(String name);
    public Boolean findVapeById(Long id);
}
