package com.example.metanik.controller;

import java.util.ArrayList;

import com.example.metanik.dao.MetanikDao;
import com.example.metanik.model.Fakemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MetanikRegionController {
	@Autowired
	private MetanikDao metanikDao;

	@GetMapping("/fakemon")
	public ArrayList<Fakemon> listaFakemon() {
		Iterable<Fakemon> lista = this.metanikDao.findAll();
		return (ArrayList<Fakemon>) lista;
	}

	@GetMapping({"/fakemon/{id_general}"})
	public Fakemon FiltroFakemon(@PathVariable int id_general) {
		return this.metanikDao.findById(id_general).orElse(null);
	}




//	@GetMapping({"/fakemon/{id_general}"})
//	public ResponseEntity<Object> teste(@PathVariable int id_general) {
//		Optional<Fakemon> fakemon = this.metanikDao.findById(id_general);
//
//		if (fakemon.isPresent()){
//			Fakemon fakemon1 = fakemon.get();
//			return ResponseEntity.ok(fakemon1);
//		}else {
//			return new ResponseEntity<>("Esse Fakemon não existe no sistema;",HttpStatus.NOT_FOUND);
//		}
//
//	}



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








