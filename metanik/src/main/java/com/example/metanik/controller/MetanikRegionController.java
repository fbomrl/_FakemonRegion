package com.example.metanik.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    @PutMapping("/fakemon/")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody @Valid Fakemon fakemon){
    if(fakemon.getId_general() == null) return new ResponseEntity<String>("ID_General precisa ser informado", HttpStatus.OK);

        Fakemon fkm = metanikDao.save(fakemon);

        return new ResponseEntity<Fakemon>(fkm, HttpStatus.OK);
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


}









