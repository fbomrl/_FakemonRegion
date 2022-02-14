package com.example.metanik.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.metanik.dao.MetanikDao;
import com.example.metanik.model.Fakemon;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MetanikRegion {
@Autowired
	private MetanikDao metanikDao;
	@GetMapping("/")
	public ArrayList<Fakemon> listaFakemon() {
		var lista = metanikDao.findAll();
		return (ArrayList<Fakemon>) lista;
	}



//	String arquivoCSV = "C:\\Users\\fabio\\Documents\\_FakemonRegion\\MetanikRegion.csv";
//	    	    BufferedReader br = null;
//	    	    String linha = "";
//	    	    Fakemon fakemon = new Fakemon();
//	    	    try {
//
//	    	        br = new BufferedReader(new FileReader(arquivoCSV));
//	    	        while ((linha = br.readLine()) != null) {
//
//	    	           String[] criatura = linha.split(",");
//	    	           String[] celulas = linha.split(";");
//
//	    	          fakemon.setName_fkm(celulas[2]);
//	    	          fakemon.setType1(celulas[3]);
//	    	          fakemon.setType2(celulas[4]);
//
//	    	          System.out.println("Nome : " + fakemon.getName_fkm());
//	    	          System.out.println("Tipo 1 : " + fakemon.getType1());
//	    	          System.out.println("Tipo 2 : " + fakemon.getType2());
//
//	    	        }
//
//	    	    } catch (FileNotFoundException e) {
//	    	        e.printStackTrace();
//	    	    } catch (IOException e) {
//	    	        e.printStackTrace();
//	    	    } finally {
//	    	        if (br != null) {
//	    	            try {
//	    	                br.close();
//	    	            } catch (IOException e) {
//	    	                e.printStackTrace();
//	    	            }
//	    	        }
//	    	    }


			}








