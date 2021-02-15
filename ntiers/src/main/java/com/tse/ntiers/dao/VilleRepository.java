package com.tse.ntiers.dao;

import org.springframework.data.repository.CrudRepository;

import com.tse.ntiers.business.Pays;
import com.tse.ntiers.business.Ville;

public interface VilleRepository extends CrudRepository<Ville,Long>   {}
