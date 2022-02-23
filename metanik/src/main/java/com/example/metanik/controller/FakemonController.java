package com.example.metanik.controller;

import java.time.LocalDateTime;
import java.util.*;

import com.example.metanik.dao.FakemonDao;
import com.example.metanik.model.FakemonModel;
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
    public ArrayList<FakemonModel> listaFakemon() {
        Iterable<FakemonModel> lista = this.fakemonDao.findAll();
        return (ArrayList<FakemonModel>) lista;

    }

    @GetMapping({"/fakemon/{id_general}"})
    public FakemonModel FiltroFakemon(@PathVariable int id_general) {
        return this.fakemonDao.findById(id_general).orElse(null);

    }

    @PostMapping("/fakemon")
    @ResponseBody
    public ResponseEntity<FakemonModel> gravar(@RequestBody @Valid FakemonModel fakemonModel) {
        Optional<FakemonModel> fakemonRetornado = fakemonDao.findById(fakemonModel.getId_general());
        if (fakemonRetornado.isPresent()) return new ResponseEntity<FakemonModel>(HttpStatus.CONFLICT);
        LocalDateTime now = fakemonService.DateTimeFormatter();

        fakemonModel.setCreatedDate(now);
        fakemonModel.setUpdatedDate(now);

        FakemonModel fkm = fakemonDao.save(fakemonModel);

        return new ResponseEntity<FakemonModel>(fkm, HttpStatus.CREATED);

    }

    @PutMapping("/fakemon/")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody @Valid FakemonModel fakemonModel) {
        if (fakemonModel.getId_general() == null)
            return new ResponseEntity<String>("ID_General precisa ser informado", HttpStatus.OK);

        Optional<FakemonModel> fakemonRetornado = fakemonDao.findById(fakemonModel.getId_general());
        if (!fakemonRetornado.isPresent()) return new ResponseEntity<FakemonModel>(HttpStatus.CONFLICT);
        fakemonModel.setCreatedDate(fakemonRetornado.get().getCreatedDate());

        LocalDateTime now = fakemonService.DateTimeFormatter();

        fakemonModel.setCreatedDate(fakemonRetornado.get().getCreatedDate());
        fakemonModel.setUpdatedDate(now);

        FakemonModel fkm = fakemonDao.save(fakemonModel);

        return new ResponseEntity<>(fkm, HttpStatus.OK);

    }

    @DeleteMapping("/fakemon/{id_general}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id_general) {
        Optional<FakemonModel> returned = fakemonDao.findById(id_general);

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









