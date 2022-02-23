package com.example.metanik.controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.metanik.dao.FakemonDao;
import com.example.metanik.model.Fakemon;
import com.example.metanik.service.FakemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
public class FakemonController {
    @Autowired
    private FakemonDao fakemonDao;
    @Autowired
    private FakemonService fakemonService;


    @GetMapping("/fakemon")
    public ArrayList<Fakemon> listaFakemon() {
        Iterable<Fakemon> lista = this.fakemonDao.findAll();
        return (ArrayList<Fakemon>) lista;

    }

    @GetMapping({"/fakemon/{id_general}"})
    public Fakemon FiltroFakemon(@PathVariable int id_general) {
        return this.fakemonDao.findById(id_general).orElse(null);

    }

    @PostMapping("/fakemon")
    @ResponseBody
    public ResponseEntity<Fakemon> gravar(@RequestBody @Valid Fakemon fakemon) {
        Optional<Fakemon> fakemonRetornado = fakemonDao.findById(fakemon.getId_general());
        if (fakemonRetornado.isPresent()) return new ResponseEntity<Fakemon>(HttpStatus.CONFLICT);

        LocalDateTime now = fakemonService.DateTimeFormatter();

        fakemon.setCreatedDate(now);
        fakemon.setUpdatedDate(now);

        Fakemon fkm = fakemonDao.save(fakemon);

        return new ResponseEntity<Fakemon>(fkm, HttpStatus.CREATED);

    }

    @PutMapping("/fakemon/")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody @Valid Fakemon fakemon) {
        if (fakemon.getId_general() == null)
            return new ResponseEntity<String>("ID_General precisa ser informado", HttpStatus.OK);

        Optional<Fakemon> fakemonRetornado = fakemonDao.findById(fakemon.getId_general());
        if (!fakemonRetornado.isPresent()) return new ResponseEntity<Fakemon>(HttpStatus.CONFLICT);
        fakemon.setCreatedDate(fakemonRetornado.get().getCreatedDate());

        LocalDateTime now = fakemonService.DateTimeFormatter();

        fakemon.setCreatedDate(fakemonRetornado.get().getCreatedDate());
        fakemon.setUpdatedDate(now);

        Fakemon fkm = fakemonDao.save(fakemon);

        return new ResponseEntity<>(fkm, HttpStatus.OK);

    }

    @DeleteMapping("/fakemon/{id_general}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id_general) {
        Optional<Fakemon> returned = fakemonDao.findById(id_general);

        if (!returned.isPresent())
            return new ResponseEntity<String>("Fakemon a ser excluido não existe!", HttpStatus.NOT_FOUND);

        fakemonDao.deleteById(id_general);
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

    @RequestMapping(value = "/fakemon/import", method = RequestMethod.POST)
    public ResponseEntity<String> ProcessarCSV(@RequestParam("file") @Valid MultipartFile file) {
        String retorno = fakemonService.readerCSV(file);

        if (retorno == null) {
            return new ResponseEntity<String>("Ocorreu um erro, tente novamente mais tarde", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<String>(retorno, HttpStatus.OK);
        }
    }
}









