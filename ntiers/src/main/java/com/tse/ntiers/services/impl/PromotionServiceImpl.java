package com.tse.ntiers.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Promotion;
import com.tse.ntiers.dao.PromotionRepository;
import com.tse.ntiers.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;

	/*Ajout d'une promotion en BDD si elle n'existe pas déjà*/
	public Promotion addPromotion(Mobility mobility) {
		Promotion promotion = new Promotion();
		String promotionName = mobility.getPromotion();
		Iterable<Promotion> listPromotion = promotionRepository.findAll();
		Iterator<Promotion> it = listPromotion.iterator();
		Boolean promotionExisteDeja = false;
		// cherche si le promotion existe déjà
		while ((it.hasNext()) && !(promotionExisteDeja)) {
			promotion = it.next();
			promotionExisteDeja=promotionName.contentEquals(promotion.getPromotion_nom());
		}
		// a défaut le crée et sauvegarde le promotion en BDD
		if (!promotionExisteDeja) {
			promotion = new Promotion();
			promotion.setPromotion_nom(promotionName);
			promotionRepository.save(promotion);
		}		
		return promotion;
	}

}
