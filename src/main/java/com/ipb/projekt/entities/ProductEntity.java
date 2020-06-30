package com.ipb.projekt.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product", schema = "public")
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

    @Basic(optional = true)
    @Column(name = "manufacturer", nullable = true)
    private String manufacturer;

    //TODO: zmień to na enuma, zobacz czy hibernate/mariadb to przeżyją
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_received_faktura")
    private ReceivedFakturaEntity receivedFakturaEntity;

    @OneToOne
    @JoinColumn(name = "id_shelf")
    private ShelfEntity shelfEntity;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private SaleEntity saleEntity;

    @OneToOne
    @JoinColumn(name = "id_annex")
    private AnnexEntity annexEntity;

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

    public ReceivedFakturaEntity getReceivedFakturaEntity() {
        return receivedFakturaEntity;
    }

    public void setReceivedFakturaEntity(ReceivedFakturaEntity receivedFakturaEntity) {
        this.receivedFakturaEntity = receivedFakturaEntity;
    }

    public ShelfEntity getShelfEntity() {
        return shelfEntity;
    }

    public void setShelfEntity(ShelfEntity shelfEntity) {
        this.shelfEntity = shelfEntity;
    }

    public SaleEntity getSaleEntity() {
        return saleEntity;
    }

    public void setSaleEntity(SaleEntity saleEntity) {
        this.saleEntity = saleEntity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public AnnexEntity getAnnexEntity() {
        return annexEntity;
    }

    public void setAnnexEntity(AnnexEntity annexEntity) {
        this.annexEntity = annexEntity;
    }

    public boolean hasAnnex() {
        return this.annexEntity != null;
    }
    public boolean hasFaktura() {
        return this.receivedFakturaEntity != null;
    }
}
