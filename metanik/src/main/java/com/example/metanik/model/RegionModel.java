package com.example.metanik.model;


import javax.persistence.*;

@Entity
@Table(name="region")

public class RegionModel {

    @Id
    @GeneratedValue
    @Column(name="id_Region")
    private int codregion;
    @Column(name="region_Name")
    private String regioname;

    @ManyToOne
    @JoinColumn(
    name ="cod_region"
    )
    private FakemonModel fakemonModel;

    public int getCodregion() {
        return codregion;
    }

    public void setCodregion(int codregion) {
        this.codregion = codregion;
    }

    public String getRegioname() {
        return regioname;
    }

    public void setRegioname(String regioname) {
        this.regioname = regioname;
    }

    public FakemonModel getFakemonModel() {
        return fakemonModel;
    }

    public void setFakemonModel(FakemonModel fakemonModel) {
        this.fakemonModel = fakemonModel;
    }
}
