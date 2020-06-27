package com.ipb.projekt.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sale", schema = "mas")
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

    @Basic(optional = false)
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public SaleEntity() {
    }

    public SaleEntity(LocalDate date, LocalTime time, BigDecimal cost) {
        this.date = date;
        this.time = time;
        this.cost = cost;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }
}
