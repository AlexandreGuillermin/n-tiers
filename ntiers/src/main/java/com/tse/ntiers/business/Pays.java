package com.tse.ntiers.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*POJO représentant la table pays*/
@Entity
@Table(name="pays")
public class Pays {
	
	@Id 
	@GeneratedValue
	private int pays_id;
	
	private String pays_nom;
	
	@OneToMany(mappedBy = "ville_pays", fetch = FetchType.LAZY)
	private Set<Ville> pays_ville;

	public int getPays_id() {
		return pays_id;
	}

	public void setPays_id(int pays_id) {
		this.pays_id = pays_id;
	}

	public String getPays_name() {
		return pays_nom;
	}

	public void setPays_name(String pays_nom) {
		this.pays_nom = pays_nom;
	}

	public Set<Ville> getPays_ville() {
		return pays_ville;
	}

	public void setPays_ville(Set<Ville> pays_ville) {
		this.pays_ville = pays_ville;
	}	

}
