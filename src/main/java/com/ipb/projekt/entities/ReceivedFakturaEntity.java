package com.ipb.projekt.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "received_faktura", schema = "mas")
public class ReceivedFakturaEntity {
    @Id
    @Column(name = "id_received_faktura")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idReceivedFaktura;

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

    public ReceivedFakturaEntity() {
    }

    public ReceivedFakturaEntity(String fakturaNumber, LocalDate date,
                                 LocalTime time, String scannedImagePath) {
        this.fakturaNumber = fakturaNumber;
        this.date = date;
        this.time = time;
        this.scannedImagePath = scannedImagePath;
    }

    public int getIdReceivedFaktura() {
        return idReceivedFaktura;
    }

    public void setIdReceivedFaktura(int idReceivedFaktura) {
        this.idReceivedFaktura = idReceivedFaktura;
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
}
