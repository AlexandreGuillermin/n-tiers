package com.tse.ntiers.business;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*POJO représentant la table élève*/
@Entity
@Table(name="eleve")
public class Eleve{
	
	@Id 
	@GeneratedValue
	private Long eleve_id;
	private String eleve_ppnom;
	private String eleve_nom;
	private Date eleve_date_debut;
	private Date eleve_date_fin;
	@ManyToOne
	@JoinColumn(name = "eleve_ville_id")
	private Ville eleve_ville;
	@ManyToOne
	@JoinColumn(name = "eleve_promotion_id")
	private Promotion eleve_promotion;
	
	/*Comparaison minimum ! attention ne compare pas les tables liées !*/
	public Boolean isEqualMinimal(Eleve eleve) {
		return ((this.eleve_ppnom.contentEquals(eleve.getEleve_ppnom()))
				&&(this.eleve_nom.contentEquals(eleve.getEleve_nom())));
	}
	
	public Long getEleve_id() {
		return eleve_id;
	}
	public void setEleve_id(Long eleve_id) {
		this.eleve_id = eleve_id;
	}
	public String getEleve_ppnom() {
		return eleve_ppnom;
	}
	public void setEleve_ppnom(String eleve_ppnom) {
		this.eleve_ppnom = eleve_ppnom;
	}
	public String getEleve_nom() {
		return eleve_nom;
	}
	public void setEleve_nom(String eleve_nom) {
		this.eleve_nom = eleve_nom;
	}
	public Promotion getEleve_promotion() {
		return eleve_promotion;
	}
	public void setEleve_promotion(Promotion eleve_promotion) {
		this.eleve_promotion = eleve_promotion;
	}
	public Date getEleve_date_debut() {
		return eleve_date_debut;
	}
	public void setEleve_date_debut(Date eleve_date_debut) {
		this.eleve_date_debut = eleve_date_debut;
	}
	public Date getEleve_date_fin() {
		return eleve_date_fin;
	}
	public void setEleve_date_fin(Date eleve_date_fin) {
		this.eleve_date_fin = eleve_date_fin;
	}
	public Ville getEleve_ville() {
		return eleve_ville;
	}
	public void setEleve_ville(Ville eleve_ville) {
		this.eleve_ville = eleve_ville;
	}
}
