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
        Iterable lista = this.fakemonService.listarFakemon();

        return (ArrayList<FakemonModel>) lista;

    }

    @GetMapping({"/fakemon/{id_general}"})
    public ResponseEntity<FakemonModel> filtroFakemon(@PathVariable int id_general) {

        FakemonModel fkm = fakemonService.filtrarFakemon(id_general);

        return new ResponseEntity<FakemonModel>(fkm, HttpStatus.OK);
    }

    @PostMapping("/fakemon")
    @ResponseBody
    public ResponseEntity<?> gravar(@RequestBody @Valid FakemonModel fakemonModel) {

        FakemonModel fkm = fakemonService.gravarFakemon(fakemonModel);

        if (fkm == null) return new ResponseEntity<>("Fakemon já existente!", HttpStatus.CONFLICT);


        return new ResponseEntity<>(fkm, HttpStatus.CREATED);

    }

    @PutMapping("/fakemon/")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody @Valid FakemonModel fakemonModel) {

        FakemonModel fkm = fakemonService.atualizarFakemon(fakemonModel);

        if (fkm == null) return new ResponseEntity<String>("Fakemon solicitado não existe", HttpStatus.CONFLICT);

        return new ResponseEntity<>(fkm, HttpStatus.OK);

    }

    @DeleteMapping("/fakemon/{id_general}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id_general) {
        FakemonModel fkm = fakemonService.deletarFakemon(id_general);

        if (fkm == null) return new ResponseEntity<String>("Fakemon a ser excluido não existe!", HttpStatus.NOT_FOUND);

        return new ResponseEntity<String>("Fakemon excluido com sucesso!", HttpStatus.OK);

    }
    @RequestMapping(value = "/fakemon/import", method = RequestMethod.POST)
    public ResponseEntity<String> ProcessarCSV(@RequestParam("file") @Valid MultipartFile file) {
        String retorno = fakemonService.readerCSV(file);
        if (retorno == null) return new ResponseEntity<String>("Ocorreu um erro, tente novamente mais tarde", HttpStatus.CONFLICT);

        return new ResponseEntity<String>(retorno, HttpStatus.OK);

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

}









