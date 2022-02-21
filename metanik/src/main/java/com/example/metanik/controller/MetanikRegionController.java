package com.example.metanik.controller;

import java.io.*;
import java.util.*;

import com.example.metanik.dao.MetanikDao;
import com.example.metanik.model.Fakemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/fakemon/import", method=RequestMethod.POST)
    public ResponseEntity<String> ProcessarCSV(@RequestParam("file") MultipartFile file){
        BufferedReader br = null;
        String linha = "";
        Fakemon fakemon = new Fakemon();

        try {
            InputStream is =  file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine();
            while ((linha = br.readLine()) != null) {

                String[] celulas = linha.split(";");

                fakemon.setId_general(Integer.valueOf(celulas[0]));
                fakemon.setId_reg(Integer.valueOf(celulas[1]));
                fakemon.setName_fkm(celulas[2]);
                fakemon.setType1(celulas[3]);
                fakemon.setType2(celulas[4]);

                Optional<Fakemon> fakemonRetornado = metanikDao.findById(fakemon.getId_general());
                //! = negação; Se o fakemonretornado não está presente ...
                if(!fakemonRetornado.isPresent()){
                    //Gravar fakemonretornado;
                    metanikDao.save(fakemon);
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Ocorreu um erro, tente novamente mais tarde", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (
                IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Ocorreu um erro, tente novamente mais tarde", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("Ocorreu um erro, tente novamente mais tarde", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        return new ResponseEntity<String>("Fakemon cadastrados com sucesso!", HttpStatus.OK);
    }




}









