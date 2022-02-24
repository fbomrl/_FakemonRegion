package com.example.metanik.dao;


import com.example.metanik.model.RegionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDao extends CrudRepository<RegionModel,Integer> {
}
