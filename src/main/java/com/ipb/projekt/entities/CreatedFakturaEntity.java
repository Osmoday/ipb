package com.ipb.projekt.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "created_faktura", schema = "public")
public class CreatedFakturaEntity {
    //TODO: powinno raczej dziedziczyć z jakiejś wspólnej klasy dla obu faktur, ale nie mamy tak w dokumentacji
    @Id
    @Column(name = "id_created_faktura")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCreatedFaktura;

    @Basic(optional = false)
    @Column(name = "faktura_number", nullable = false)
    private String fakturaNumber;

    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Basic(optional = false)
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Basic(optional = false)
    @Column(name = "scanned_image_path", nullable = false)
    private String scannedImagePath;

    @OneToOne
    @JoinColumn(name = "id_sale")
    private SaleEntity saleEntity;

    public CreatedFakturaEntity() {
    }

    public CreatedFakturaEntity(String fakturaNumber, LocalDate date,
                                LocalTime time, String scannedImagePath, SaleEntity saleEntity) {
        this.fakturaNumber = fakturaNumber;
        this.date = date;
        this.time = time;
        this.scannedImagePath = scannedImagePath;
        this.saleEntity = saleEntity;
    }

    public int getIdCreatedFaktura() {
        return idCreatedFaktura;
    }

    public void setIdCreatedFaktura(int idCreatedFaktura) {
        this.idCreatedFaktura = idCreatedFaktura;
    }

    public String getFakturaNumber() {
        return fakturaNumber;
    }

    public void setFakturaNumber(String fakturaNumber) {
        this.fakturaNumber = fakturaNumber;
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

    public String getScannedImagePath() {
        return scannedImagePath;
    }

    public void setScannedImagePath(String scannedImagePath) {
        this.scannedImagePath = scannedImagePath;
    }

    public SaleEntity getSaleEntity() {
        return saleEntity;
    }

    public void setSaleEntity(SaleEntity saleEntity) {
        this.saleEntity = saleEntity;
    }
}
