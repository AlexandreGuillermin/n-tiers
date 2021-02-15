package com.tse.ntiers.services.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tse.ntiers.business.Eleve;
import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Promotion;
import com.tse.ntiers.business.Ville;
import com.tse.ntiers.dao.EleveRepository;
import com.tse.ntiers.service.EleveService;

@Service
public class EleveServiceImpl implements EleveService {

	@Autowired
	private EleveRepository eleveRepository;
	
	/*Ajout d'un élève à la BDD à partir de la demande de mobilité, de sa ville et de sa promotion*/
	public Eleve addEleve(Mobility mobility, Ville ville, Promotion promotion) {
		Eleve eleve = new Eleve();
		String eleveName = mobility.getNom();
		String elevePPname = mobility.getPpnom();
		Date eleveDateDebut = mobility.getDate_debut();
		Date eleveDateFin = mobility.getDate_fin();
		Iterable<Eleve> listEleve = eleveRepository.findAll();
		Iterator<Eleve> it = listEleve.iterator();
		Boolean eleveExisteDeja = false;
		// cherche si l'élève existe déjà
		while ((it.hasNext()) && !(eleveExisteDeja)) {
			eleve = it.next();
			eleveExisteDeja=((eleveName.contentEquals(eleve.getEleve_nom()))
					&&(elevePPname.contentEquals(eleve.getEleve_ppnom())));
		}
		// a défaut le crée et sauvegarde le Eleve en BDD
		if (!eleveExisteDeja) {
			eleve = new Eleve();
			eleve.setEleve_nom(eleveName);
			eleve.setEleve_ppnom(elevePPname);
			eleve.setEleve_ville(ville);
			eleve.setEleve_promotion(promotion);
			eleve.setEleve_date_debut(eleveDateDebut);
			eleve.setEleve_date_fin(eleveDateFin);
			eleveRepository.save(eleve);
		}
		return eleve;
	}
	
	/*Obtenir tout les élèves*/
	public Iterable<Eleve> getEleve(){
		return eleveRepository.findAll();
	}
	
	/*Obtenir un élève à partir de son ID*/	
	public Eleve getEleve(Long eleveId) {
		return eleveRepository.findById(eleveId).get();
	}
	
	/*Suprime l'élève donné en paramètre de la DB*/	
	public void delEleve(Eleve eleve) {
		//On cherche l'id correspondante
		Long eleveId=null;
		Boolean eleveIdFound=false;
		Iterable<Eleve> listEleve = eleveRepository.findAll();;
		Iterator<Eleve> iterator = listEleve.iterator();
		Eleve eleveIt;
		while ((iterator.hasNext())&&!(eleveIdFound)) {
			eleveIt = iterator.next();
			if(eleveIt.isEqualMinimal(eleve)) {
				eleveIdFound=true;
				eleveId=eleveIt.getEleve_id();
			}			
		}	
		if(eleveIdFound) {
			eleveRepository.deleteById(eleveId);
		}
		else {
			//cas où l'élève n'existe pas (erreur dans la requête)
			throw new NullPointerException();
		}		
	}
	
	
}
