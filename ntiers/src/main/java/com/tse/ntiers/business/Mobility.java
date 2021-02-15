package com.tse.ntiers.business;

import java.sql.Date;

/*POJO représentant une mobilité. Non sauvegardé en BDD*/
public class Mobility {
	private String ppnom;
	private String nom;
	private String ville;
	private String pays;
	private String promotion;
	private Date date_debut;
	private Date date_fin;
	
	public Mobility() {}
	
	public Mobility(String ppnom, String nom, String ville, String pays, String promotion, Date date_debut, Date date_fin) {
		this.ppnom=ppnom;
		this.nom=nom;
		this.ville=ville;
		this.pays=pays;
		this.promotion=promotion;
		this.date_debut=date_debut;
		this.date_fin=date_fin;
	}
	
	public String getPpnom() {
		return ppnom;
	}
	public void setPpnom(String ppnom) {
		this.ppnom = ppnom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
}
