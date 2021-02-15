package com.tse.ntiers.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Pays;
import com.tse.ntiers.business.Ville;
import com.tse.ntiers.dao.VilleRepository;
import com.tse.ntiers.service.VilleService;

@Service
public class VilleServiceImpl implements VilleService {
	@Autowired
	private VilleRepository VilleRepository;
	
	/*Ajout d'une ville en BDD si elle n'existe pas déjà*/
	public Ville addVille(Mobility mobility, Pays pays) {
		Ville ville = new Ville();
		String villeName = mobility.getVille();
		Iterable<Ville> listVille = VilleRepository.findAll();
		Iterator<Ville> it = listVille.iterator();
		Boolean villeExisteDeja = false;
		// cherche si le Ville existe déjà
		while ((it.hasNext()) && !(villeExisteDeja)) {
			ville = it.next();
			villeExisteDeja=villeName.contentEquals(ville.getVille_nom());
		}
		// a défaut le crée et sauvegarde le Ville en BDD
		if (!villeExisteDeja) {
			ville = new Ville();
			ville.setVille_nom(villeName);
			ville.setVille_pays(pays);
			VilleRepository.save(ville);
		}		
		return ville;
	}

}
