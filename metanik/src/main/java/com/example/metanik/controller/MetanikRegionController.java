package com.example.metanik.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.metanik.dao.MetanikDao;
import com.example.metanik.model.Fakemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @PostMapping("/fakemon")
    @ResponseBody
    public ResponseEntity<Fakemon> gravar(@RequestBody @Valid Fakemon fakemon) {
        Optional<Fakemon> fakemonRetornado = metanikDao.findById(fakemon.getId_general());
        if(fakemonRetornado.isPresent()) return new ResponseEntity<Fakemon>(HttpStatus.CONFLICT);

        Fakemon fkm = metanikDao.save(fakemon);

        return new ResponseEntity<Fakemon>(fkm, HttpStatus.CREATED);
    }

    @DeleteMapping("/fakemon/{id_general}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id_general) {
        metanikDao.deleteById(id_general);
        return new ResponseEntity<String>("Fakemon excluido com sucesso!", HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
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








