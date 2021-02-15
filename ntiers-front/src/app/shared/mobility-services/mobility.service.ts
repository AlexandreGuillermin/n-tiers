import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
/*Objet représentant une mobilité */
export class MobilityServices {
  public ppnom: String;
  public nom: String;
  public ville: String;
  public pays: String;
  public promotion: String;
  public date_debut: Date;
  public date_fin: Date;

  constructor() { }

  public set(mobilityService: MobilityServices) {
    this.ppnom = mobilityService.ppnom;
    this.nom = mobilityService.nom;
    this.ville = mobilityService.ville;
    this.pays = mobilityService.pays;
    this.promotion = mobilityService.promotion;
    this.date_debut = mobilityService.date_debut;
    this.date_fin = mobilityService.date_fin;
  }
}
