package com.example.metanik.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "metanikregion")

public class Fakemon {

    @Id
    @Column(name = "id_general", nullable = false, unique = true)
    @NotNull(message = "Campo Id_General é Obrigatório!")
    private Integer id_general;

    @Column(name = "id_reg", nullable = false)
    @NotNull(message = "Campo id_reg é Obrigatório!")
    private Integer id_reg;

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedDate;

    @Column(name = "name_fkm", nullable = false, length = 20)
    @NotBlank(message = "Campo nome é Obrigatório!")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Campo Nome deve iniciar com letra maiúscula!")
    private String name_fkm;

    @Column(name = "type1", nullable = false)
    @NotBlank(message = "Campo tipo1 é Obrigatório!")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Campo Tipo deve iniciar com letra maiúscula!")
    private String type1;

    @Column(name = "type2", length = 30)
    private String type2;

    @Column(name = "species", length = 30)
    private String species;

    @Column(name = "abilities1", length = 30)
    private String abilities1;

    @Column(name = "abilities2", length = 30)
    private String abilities2;

    @Column(name = "hiddenability", length = 30)
    private String hiddenability;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "habitat", length = 40)
    private String habitat;

    @Column(name = "feeding", length = 30)
    private String feeding;

    @Column(name = "evoby")
    private String evoby;

    @Column(name = "inspiration1", length = 250)
    private String inspiration1;

    @Column(name = "inspiration2", length = 250)
    private String inspiration2;


    public Integer getId_general() {
        return this.id_general;
    }

    public void setId_general(Integer id_general) {
        this.id_general = id_general;
    }

    public Integer getId_reg() {
        return id_reg;
    }

    public void setId_reg(Integer id_reg) {
        this.id_reg = id_reg;
    }

    public String getName_fkm() {
        return name_fkm;
    }

    public void setName_fkm(String name_fkm) {
        this.name_fkm = name_fkm;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAbilities1() {
        return abilities1;
    }

    public void setAbilities1(String abilities1) {
        this.abilities1 = abilities1;
    }

    public String getAbilities2() {
        return abilities2;
    }

    public void setAbilities2(String abilities2) {
        this.abilities2 = abilities2;
    }

    public String getHiddenability() {
        return hiddenability;
    }

    public void setHiddenability(String hiddenability) {
        this.hiddenability = hiddenability;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getEvoby() {
        return evoby;
    }

    public void setEvoby(String evoby) {
        this.evoby = evoby;
    }

    public String getInspiration1() {
        return inspiration1;
    }

    public void setInspiration1(String inspiration1) {
        this.inspiration1 = inspiration1;
    }

    public String getInspiration2() {
        return inspiration2;
    }

    public void setInspiration2(String inspiration2) {
        this.inspiration2 = inspiration2;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
