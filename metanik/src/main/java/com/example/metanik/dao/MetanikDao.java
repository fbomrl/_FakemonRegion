package com.example.metanik.dao;

import com.example.metanik.model.Fakemon;
import org.springframework.data.repository.CrudRepository;


public interface MetanikDao extends CrudRepository<Fakemon,Integer> {
}

