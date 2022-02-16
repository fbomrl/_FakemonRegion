package com.example.metanik.model;

import javax.persistence.*;

@Entity
@Table(name = "metanikregion")

public class Fakemon {

	@Id
	@Column(name = "id_general", nullable = false)
	private int id_general;

	@Column(name = "id_reg", nullable = false)
	private int id_reg;

	@Column(name = "name_fkm", nullable = false, length = 20)
	private String name_fkm;

	@Column(name = "type1", nullable = false)
	private String type1;

	@Column(name = "type2")
	private String type2;

	@Column(name = "species")
	private String species;

	@Column(name = "abilities1")
	private String abilities1;

	@Column(name = "abilities2")
	private String abilities2;

	@Column(name = "hiddenability")
	private String hiddenability;

	@Column(name = "height")
	private String height;

	@Column(name = "weight")
	private String weight;

	@Column(name = "habitat")
	private String habitat;

	@Column(name = "feeding")
	private String feeding;

	@Column(name = "evoby")
	private String evoby;

	@Column(name = "inspiration1")
	private String inspiration1;

	@Column(name = "inspiration2")
	private String inspiration2;

	public int getId_general() {
		return this.id_general;
	}

	public void setId_general(int id_general) {
		this.id_general = id_general;
	}

	public int getId_reg() {
		return id_reg;
	}

	public void setId_reg(int id_reg) {
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
}
