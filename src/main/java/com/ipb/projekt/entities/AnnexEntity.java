package com.ipb.projekt.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "annex", schema = "public")
public class AnnexEntity {

    @Id
    @Column(name = "id_annex")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idAnnex;

    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Basic(optional = false)
    @Column(name = "info", nullable = false)
    private String info;

    @OneToOne
    @JoinColumn(name = "id_sale")
    private SaleEntity saleEntity;

    public AnnexEntity() {
    }

    public AnnexEntity(LocalDate date, String info, SaleEntity saleEntity) {
        this.date = date;
        this.info = info;
        this.saleEntity = saleEntity;
    }

    public int getIdAnnex() {
        return idAnnex;
    }

    public void setIdAnnex(int idAnnex) {
        this.idAnnex = idAnnex;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SaleEntity getSaleEntity() {
        return saleEntity;
    }

    public void setSaleEntity(SaleEntity saleEntity) {
        this.saleEntity = saleEntity;
    }
}
