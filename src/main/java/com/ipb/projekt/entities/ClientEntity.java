package com.ipb.projekt.entities;

import javax.persistence.*;

@Entity
@Table(name = "client", schema = "mas")
public class ClientEntity {
    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idClient;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "surname", nullable = false)
    private String surname;

    @Basic(optional = true)
    @Column(name = "nip", nullable = true)
    private String NIP;

    public ClientEntity() {
    }

    public ClientEntity(String name, String surname, String nip) {
        this.name = name;
        this.surname = surname;
        NIP = nip;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
}
