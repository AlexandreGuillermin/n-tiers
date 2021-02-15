package com.tse.ntiers.service;

import com.tse.ntiers.business.Eleve;
import com.tse.ntiers.business.Mobility;
import com.tse.ntiers.business.Promotion;
import com.tse.ntiers.business.Ville;

public interface EleveService {
	public Eleve addEleve(Mobility mobility, Ville ville, Promotion promotion);
	Iterable<Eleve> getEleve();
	Eleve getEleve(Long eleveId);
	public void delEleve(Eleve eleve);
}
