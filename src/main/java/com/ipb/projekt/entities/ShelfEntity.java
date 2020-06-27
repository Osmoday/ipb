package com.ipb.projekt.entities;

import javax.persistence.*;

@Entity
@Table(name = "shelf", schema = "public")
public class ShelfEntity {
    @Id
    @Column(name = "id_shelf")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idShelf;

    @Basic(optional = false)
    @Column(name = "c", nullable = false)
    private int column;

    @Basic(optional = false)
    @Column(name = "r", nullable = false)
    private int row;

    @Basic(optional = false)
    @Column(name = "l", nullable = false)
    private int level;

    @OneToOne
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;

    public ShelfEntity() {
    }

    public ShelfEntity(int column, int row, int level) {
        this.column = column;
        this.row = row;
        this.level = level;
    }

    public int getIdShelf() {
        return idShelf;
    }

    public void setIdShelf(int idShelf) {
        this.idShelf = idShelf;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
