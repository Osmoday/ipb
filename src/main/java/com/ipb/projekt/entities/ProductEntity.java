package com.ipb.projekt.entities;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "mas")
public class ProductEntity {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idProduct;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Basic(optional = true)
    @Column(name = "serial_number", nullable = true)
    private String serialNumber;

    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;

    //TODO: zmień to na enuma, zobacz czy hibernate/mariadb to przeżyją
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private String status;

    public ProductEntity() {
    }

    public ProductEntity(String name, String serialNumber, int amount, String status) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.amount = amount;
        this.status = status;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
