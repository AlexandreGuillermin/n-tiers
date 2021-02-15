package com.tse.ntiers.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/*POJO représentant la table promotion*/
@Entity
@Table(name="promotion")
public class Promotion {
	
	@Id 
	@GeneratedValue
	private int promotion_id;
	
	private String promotion_nom;
	
	@OneToMany(mappedBy = "eleve_promotion", fetch = FetchType.LAZY)
	private Set<Eleve> promotion_eleves;
	
	public String getPromotion_nom() {
		return promotion_nom;
	}
	public void setPromotion_nom(String promotion_nom) {
		this.promotion_nom = promotion_nom;
	}
	public int getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}
	
}
