package com.ipb.projekt.entities;

import javax.persistence.*;

@Entity
@Table(name = "shelf", schema = "mas")
public class ShelfEntity {
    @Id
    @Column(name = "id_shelf")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idShelf;

    @Basic(optional = false)
    @Column(name = "column", nullable = false)
    private int column;

    @Basic(optional = false)
    @Column(name = "row", nullable = false)
    private int row;

    @Basic(optional = false)
    @Column(name = "level", nullable = false)
    private int level;

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
}
