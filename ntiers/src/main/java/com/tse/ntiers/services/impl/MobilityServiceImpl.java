package com.tse.ntiers.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tse.ntiers.business.Eleve;
import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Pays;
import com.tse.ntiers.business.Promotion;
import com.tse.ntiers.business.Ville;
import com.tse.ntiers.service.EleveService;
import com.tse.ntiers.service.MobilityService;
import com.tse.ntiers.service.PaysService;
import com.tse.ntiers.service.PromotionService;
import com.tse.ntiers.service.VilleService;

@Service
public class MobilityServiceImpl implements MobilityService {

	@Autowired
	private PaysService paysService;
	@Autowired
	private VilleService villeService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private EleveService eleveService;

	/* Crée chaque objet s'il n'existe pas déjà dans la BDD */
	public Eleve addMobility(Mobility mobility) {
		Pays pays = paysService.addPays(mobility);
		// crée la ville si elle n'existe pas
		Ville ville = villeService.addVille(mobility, pays);
		// crée la promotion si elle n'existe pas
		Promotion promotion = promotionService.addPromotion(mobility);
		// crée et retourne l'élève s'il n'existe pas
		return eleveService.addEleve(mobility, ville, promotion);
	}

	/* Retourne toute les mobilités en liste */
	public List<Mobility> getMobility() {
		List<Mobility> mobilities = new ArrayList<Mobility>();
		Iterator<Eleve> iterator = eleveService.getEleve().iterator();
		Eleve eleve;
		while (iterator.hasNext()) {
			eleve = iterator.next();
			mobilities.add(new Mobility(eleve.getEleve_ppnom(), eleve.getEleve_nom(),
					eleve.getEleve_ville().getVille_nom(), eleve.getEleve_ville().getVille_pays().getPays_name(),
					eleve.getEleve_promotion().getPromotion_nom(), eleve.getEleve_date_debut(),
					eleve.getEleve_date_fin()));
		}
		return mobilities;
	}
	
	/*Recréer une mobilité à partir d'un ID d'élève*/
	public Mobility getMobility(Long eleveId) {
		Eleve eleve = eleveService.getEleve(eleveId);
		return new Mobility(eleve.getEleve_ppnom(), eleve.getEleve_nom(),
				eleve.getEleve_ville().getVille_nom(), eleve.getEleve_ville().getVille_pays().getPays_name(),
				eleve.getEleve_promotion().getPromotion_nom(), eleve.getEleve_date_debut(),
				eleve.getEleve_date_fin());		
	}
	
	/*Retourne toute les mobilités correspondantes au filtre*/
	public List<Mobility> getMobilityFilter(Mobility mobility){
		List<Mobility> mobilities = new ArrayList<Mobility>();
		Iterator<Eleve> iterator = eleveService.getEleve().iterator();
		Eleve eleve;
		//par défaut les date de début valident les conditions
		boolean isDateDebutOk = true;
		boolean isDateFinOk = true;
		while (iterator.hasNext()) {
			eleve = iterator.next();
			//test les dates si elles sont non nulls
			if(mobility.getDate_debut()!=null) {
				isDateDebutOk=eleve.getEleve_date_debut().before(mobility.getDate_debut());
			}
			if(mobility.getDate_debut()!=null) {
				isDateFinOk=eleve.getEleve_date_fin().after(mobility.getDate_debut());
			}
			
			//ajoute la mobilité à la liste si elle correspond au filtre
			if((eleve.getEleve_ppnom().contains(mobility.getPpnom())) 
					&& (eleve.getEleve_nom().contains(mobility.getNom()))
					&& (eleve.getEleve_ville().getVille_nom().contains(mobility.getVille()))
					&& (eleve.getEleve_ville().getVille_pays().getPays_name().contains(mobility.getPays()))
					&& (eleve.getEleve_promotion().getPromotion_nom().contains(mobility.getPromotion()))
					&& (isDateDebutOk)
					&& (isDateFinOk)
					)
			{
				mobilities.add(new Mobility(eleve.getEleve_ppnom(), eleve.getEleve_nom(),
						eleve.getEleve_ville().getVille_nom(), eleve.getEleve_ville().getVille_pays().getPays_name(),
						eleve.getEleve_promotion().getPromotion_nom(), eleve.getEleve_date_debut(),
						eleve.getEleve_date_fin()));
			}
		}
		return mobilities;
	}
	
	/*Supprime une mobilité ! Attention ne supprime que l'élève, pas les objets liés (promo, ville, pays) !*/
	public Boolean delMobility(Mobility mobility) {
		Eleve eleveADel = new Eleve();
		eleveADel.setEleve_nom(mobility.getNom());
		eleveADel.setEleve_ppnom(mobility.getPpnom());
		eleveService.delEleve(eleveADel);
		return true;
	}
}
