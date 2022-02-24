package com.example.metanik.controller;


import com.example.metanik.dao.RegionDao;
import com.example.metanik.model.RegionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RegionController {
    @Autowired
    private RegionDao regionDao;

    @GetMapping("/region")
    public ArrayList<RegionModel> listaregion() {
        Iterable listaregion = this.regionDao.findAll();;

        return (ArrayList<RegionModel>) listaregion;

    }
}
