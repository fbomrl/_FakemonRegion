package com.example.metanik.dao;

import com.example.metanik.model.Fakemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakemonDao extends CrudRepository<Fakemon,Integer> {
}

