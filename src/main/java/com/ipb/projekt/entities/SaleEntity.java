package com.ipb.projekt.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "sale", schema = "public")
public class SaleEntity {
    @Id
    @Column(name = "id_sale")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idSale;

    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Basic(optional = false)
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @OneToOne
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;

    @OneToOne
    @JoinColumn(name = "id_annex")
    private AnnexEntity annexEntity;

    @OneToOne
    @JoinColumn(name = "id_created_faktura")
    private CreatedFakturaEntity createdFakturaEntity;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private ClientEntity clientEntity;

    public SaleEntity() {
    }

    public SaleEntity(LocalDate date, LocalTime time,
                      ProductEntity productEntity) {
        this.date = date;
        this.time = time;
        this.productEntity = productEntity;
    }


    public SaleEntity(LocalDate date, LocalTime time,
                      ProductEntity productEntity, ClientEntity client) {
        this.date = date;
        this.time = time;
        this.productEntity = productEntity;
        this.clientEntity = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public AnnexEntity getAnnexEntity() {
        return annexEntity;
    }

    public void setAnnexEntity(AnnexEntity annexEntity) {
        this.annexEntity = annexEntity;
    }

    public CreatedFakturaEntity getCreatedFakturaEntity() {
        return createdFakturaEntity;
    }

    public void setCreatedFakturaEntity(CreatedFakturaEntity createdFakturaEntity) {
        this.createdFakturaEntity = createdFakturaEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
