package com.tse.ntiers.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Pays;
import com.tse.ntiers.dao.PaysRepository;
import com.tse.ntiers.service.PaysService;

@Service
public class PaysServiceImpl implements PaysService {

	@Autowired
	private PaysRepository paysRepository;
	
	//Ajout d'un pays en BDD s'il n'existe pas déjà
	public Pays addPays(Mobility mobility) {
		Pays pays = new Pays();
		String paysName = mobility.getPays();
		Iterable<Pays> listPays = paysRepository.findAll();
		Iterator<Pays> it = listPays.iterator();
		Boolean paysExisteDeja = false;
		// cherche si le pays existe déjà
		while ((it.hasNext()) && !(paysExisteDeja)) {
			pays = it.next();
			
			System.out.println("Pays liste :"+ pays.getPays_name());
			System.out.println("Pays cherche :"+ paysName);
			paysExisteDeja=paysName.contentEquals(pays.getPays_name());
		}
		// a défaut le crée et sauvegarde le pays en BDD
		if (!paysExisteDeja) {
			pays = new Pays();
			pays.setPays_name(paysName);
			paysRepository.save(pays);
		}		
		return pays;
	}

}
