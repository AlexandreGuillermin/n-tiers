package com.tse.ntiers.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/*POJO représentant la table ville*/
@Entity
@Table(name="ville")
public class Ville {
	
	@Id 
	@GeneratedValue
	private int ville_id;
	private String ville_nom;
	
	@OneToMany(mappedBy = "eleve_ville", fetch = FetchType.LAZY)
	private Set<Eleve> ville_eleve;
	
	@ManyToOne
	@JoinColumn(name = "ville_pays_id")
	private Pays ville_pays;

	public int getVille_id() {
		return ville_id;
	}

	public void setVille_id(int ville_id) {
		this.ville_id = ville_id;
	}

	public String getVille_nom() {
		return ville_nom;
	}

	public void setVille_nom(String ville_nom) {
		this.ville_nom = ville_nom;
	}

	public Set<Eleve> getVille_eleve() {
		return ville_eleve;
	}

	public void setVille_eleve(Set<Eleve> ville_eleve) {
		this.ville_eleve = ville_eleve;
	}

	public Pays getVille_pays() {
		return ville_pays;
	}

	public void setVille_pays(Pays ville_pays) {
		this.ville_pays = ville_pays;
	}
}
