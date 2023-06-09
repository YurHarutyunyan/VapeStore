package org.example.vapestore.model;



import jakarta.persistence.*;
import org.hibernate.annotations.Table;


@Table(appliesTo = "vape")
@Entity
public class Vape {

    @Column
    private String name;

    @Column
    private Integer puffCount;

    @Column
    private Double price;

    @Column
    private Integer quantity;
    @Id
    @SequenceGenerator(name = "vape_id_seq", sequenceName = "vapeSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vapeSeq")
    Long vapeId;

    public Vape(String name, Integer puffCount, Double price, Long vapeId, Integer quantity) {
        this.name = name;
        this.puffCount = puffCount;
        this.price = price;
        this.vapeId = vapeId;
        this.quantity = quantity;
    }

    public Vape() {
    }

    public Vape(String name, Integer puffCount, Integer quantity, Double price) {
        this.name = name;
        this.puffCount = puffCount;
        this.price = price;
        this.quantity = quantity;
    }
    public Vape(String name, Integer puffCount, Double price){
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPuffCount() {
        return puffCount;
    }

    public void setPuffCount(Integer puffCount) {
        this.puffCount = puffCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVapeId() {
        return vapeId;
    }

    public void setVapeId(Long vapeId) {
        this.vapeId = vapeId;
    }
}
